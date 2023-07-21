package com.example.shareMate.controller;

import com.example.shareMate.domain.Member;
import com.example.shareMate.service.MailSendService;
import com.example.shareMate.service.MemberService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.websocket.OnClose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;


@Controller
@RequestMapping("member")
public class MemberController {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MailSendService mailSendService;

    @GetMapping("login")
    public void loginForm() {
        //로그인 포워드
    }

    @GetMapping("signup")
    public void joinForm() {
        //join form 포워드
    }

    @PostMapping("signup")
    public String joinProcess(Member member, RedirectAttributes rttr) {
        //회원가입 과정
        boolean ok = memberService.addMember(member);

        if (ok) {
            rttr.addFlashAttribute("message", "회원가입이 되었습니다.");
            rttr.addFlashAttribute("status", "success");

            return "redirect:/member/login";
        } else {
            rttr.addFlashAttribute("message", "회원가입이 실패하였습니다.");
            rttr.addFlashAttribute("status", "fail");

            return "redirect:/member/join";
        }

    }

    @GetMapping("myPage")
    @PreAuthorize("isAuthenticated()")
    public void myPageForm(Authentication authentication, Model model) {
        //마이페이지 포워드
        Member member = memberService.selectMemberByUsername(authentication.getName());

        model.addAttribute("member", member);
    }

    @GetMapping("modify")
    @PreAuthorize("isAuthenticated()")
    public void modifyForm(Authentication authentication, Model model) {
        //수정페이지 포워드
        Member member = memberService.selectMemberByUsername(authentication.getName());

        model.addAttribute("member", member);
    }

    @PostMapping("modify")
    public String modifyProcess(Member member, RedirectAttributes rttr) {
        //수정페이지 과정
        boolean ok = memberService.modifyMemberByUsername(member);

        if (ok) {
            rttr.addFlashAttribute("message", "회원정보가 수정되었습니다.");
            rttr.addFlashAttribute("status", "success");
        } else {
            rttr.addFlashAttribute("message", "회원정보를 수정하지 못했습니다.");
            rttr.addFlashAttribute("status", "fail");
        }

        return "redirect:/member/myPage";
    }

    @PostMapping("delete")
    public String deleteProcess(
            Member member,
            RedirectAttributes rttr,
            HttpServletRequest request) throws Exception {

        boolean ok = memberService.deleteMemberByUsername(member);

        if (ok) {
            rttr.addFlashAttribute("message", "회원 탈퇴가 완료되었습니다.");
            rttr.addFlashAttribute("status", "success");
            //로그아웃
            request.logout();

            return "redirect:/member/login";
        } else {
            rttr.addFlashAttribute("message", "회원 탈퇴가 실패하였습니다.");
            rttr.addFlashAttribute("status", "fail");

            return "redirect:/member/myPage";
        }
    }

    @GetMapping("checkUsername")
    @ResponseBody
    public Map<String, Object> checkUserName(@RequestParam("username") String username) {
        //아이디 중복확인
        return memberService.checkUserName(username);
    }

    @GetMapping("checkEmail")
    @ResponseBody
    public Map<String, Object> checkEmail(@RequestParam("email") String email) {
        return memberService.checkEmail(email);
    }

    @GetMapping("veriCode")
    @ResponseBody
    public String veriCode(@RequestParam("email") String email) {
        System.out.println(email);
        return mailSendService.joinEmail(email);
    }
}
