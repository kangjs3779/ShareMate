<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="current"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/board/list">게시판</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/member/login">로그인</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/member/logout">로그아웃</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/member/join">회원가입</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="/member/myPage">마이페이지</a>
                </li>

            </ul>
            <form class="d-flex" role="search">
                <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success" type="submit">Search</button>
            </form>
        </div>
    </div>
</nav>
