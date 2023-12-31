package com.example.shareMate.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

@Configuration
@EnableMethodSecurity
public class customConfig {
    @Value("${spring.mail.username}")
    private String username;
    @Value("${spring.mail.password}")
    private String passward;
    @Value("${aws.accessKeyId}")
    private String accessKeyId;
    @Value("${aws.secretAccessKey}")
    private String secretAccessKey;
    @Value("${aws.bucketUrl}")
    private String bucketUrl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable());

        http.formLogin()
                .loginPage("/member/login")
                .defaultSuccessUrl("/board/home", true)
                .failureHandler((request, response, exception) -> {
                    // 실패 시 처리할 로직 구현
                    String errorMessage = "로그인 실패하였습니다.";
                    request.setAttribute("message", errorMessage);
                    String redirectUrl = "/member/login?message=" + URLEncoder.encode(errorMessage, StandardCharsets.UTF_8);
                    response.sendRedirect(redirectUrl);
                });

        http.logout().logoutUrl("/member/logout");

        return http.build();
    }

    @Bean
    public JavaMailSender mailSender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);
        properties.put("mail.debug", true);

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(username);
        mailSender.setPassword(passward);
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
    }

    @Bean
    public S3Client s3client() {

        AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKeyId, secretAccessKey);
        AwsCredentialsProvider provider = StaticCredentialsProvider.create(credentials);

        S3Client s3client = S3Client.builder()
                .credentialsProvider(provider)
                .region(Region.AP_NORTHEAST_2)
                .build();

        return s3client;
    }
}
