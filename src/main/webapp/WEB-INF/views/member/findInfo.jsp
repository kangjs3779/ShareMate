<%--
  Created by IntelliJ IDEA.
  User: kangjisu
  Date: 2023/08/06
  Time: 10:35 AM
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
<my:navBar></my:navBar>
<br>
<br>
<div class="container" style="width: 500px;">
    <div class="ui top attached tabular menu">
        <a class="item active" data-tab="findId">아이디 찾기</a>
        <a class="item" data-tab="findPw">비밀번호 찾기</a>
    </div>

    <%-- 아이디 찾기 --%>
    <div class="ui bottom attached tab segment active" data-tab="findId">
        <div id="findIdBox">
            <form class="ui form">
                <div class="field" id="emailBox">
                    <label>Email</label>
                    <input required type="email" placeholder="회원 가입 시 입력했던 이메일을 입력해주세요." id="emailInput">
                </div>
                <div class="field" id="nameBox">
                    <label>Name</label>
                    <input required type="text" placeholder="회원 가입 시 입력했던 이름을 입력해주세요." id="nameInput">
                </div>
                <button class="ui button" type="button" id="findIdBtn">아이디 찾기</button>
            </form>
        </div>
    </div>

    <%-- 비번 찾기 --%>
    <div class="ui bottom attached tab segment" data-tab="findPw">
        <div id="findPwBox">
            <form class="ui form">
                <div class="field" id="PwIdBox">
                    <label>ID</label>
                    <input type="text" id="PwIdInput" placeholder="아이디를 입력해주세요.">
                </div>
                <div class="field" id="pwEmailBox">
                    <label>Email</label>
                    <input type="text" id="PwEmailInput" placeholder="회원 가입 시 입력했던 이메일을 입력해주세요.">
                </div>
                <button class="ui button" type="button" id="findPwBtn">비번 찾기</button>
            </form>
        </div>
    </div>

</div>

<my:foot></my:foot>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.7.0/jquery.min.js" integrity="sha512-3gJwYpMe3QewGELv8k/BX9vcqhryRdzRMxVfq6ngyWXwo03GFEzjsUm8Q7RZcHPHksttq7/GFoxjCVUjkjvPdw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="/js/semantic/semantic.js"></script>
<script src="/js/findIdPw.js"></script>
<script>
    $('.menu .item')
        .tab()
    ;
</script>
</body>
</html>
