<%--
  Created by IntelliJ IDEA.
  User: kangjisu
  Date: 2023/07/13
  Time: 12:25 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://getbootstrap.com/docs/5.3/assets/css/docs.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="/js/semantic/semantic.min.css">
    <title>Title</title>
</head>
<body>
<my:navBar current="login"/>

<div class="container w-25">
    <br><br>
    <h1 class="text-center">LOGIN</h1>
    <my:alert status="${status}"/>
    <form id="loginForm" method="post">
        <div class="mb-3">
            <label for="usernameInput" class="form-label">ID</label>
            <input type="text" name="username" class="form-control" id="usernameInput">
        </div>
        <div class="mb-3">
            <label for="pwInput" class="form-label">Password</label>
            <input type="password" name="password" class="form-control" id="pwInput">
        </div>
    </form>

    <div class="d-flex justify-content-between">
        <div>
            <button class="btn btn-outline-secondary" type="submit" form="loginForm" id="liveToastBtn">로그인</button>
            <a class="btn btn-outline-secondary" href="/member/signup">회원가입</a>
        </div>
        <div class="ml-auto">
            <a class="link-secondary link-offset-2 link-underline-opacity-25 link-underline-opacity-100-hover" href="/member/findInfo">아이디/비밀번호 찾기</a>
        </div>
    </div>

</div>

<my:foot></my:foot>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="/js/semantic/semantic.min.js"></script>
</body>
</html>
