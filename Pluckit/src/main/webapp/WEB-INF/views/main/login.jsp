<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>로그인</title>
    <link rel="stylesheet" href="resources/css/login.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
    <div id="wrap">
        <div class="loginBox">
            <form action="">
                <!-- 로고이미지 & 회사명 -->
                <div class="logo">
                    <img src="resources/img/logo.png" alt="로고">
                    <p>PluckIT</p>
                </div>

                <!-- input태그들 -->
                <div class="inputboxes">
                    <div class="alert"><i class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;사용자 정보가 올바르지 않습니다.</div>
                    <input type="text" class="id" placeholder="사원번호">
                    <input type="password" class="pw" placeholder="비밀번호">
                    <input type="button" class="submit" value="로그인">
                </div>

                <!-- 체크박스 & 가입하기 링크 -->
                <div class="signup">
                    <div><input type="checkbox" id="chk">&nbsp;&nbsp;<label for="chk">계정 저장</label></div>
                    <div>계정이 없으신가요? <a href="Signup.do">가입하기</a></div>
                </div>
            </form>
        </div>
    </div>
</body>
</html>