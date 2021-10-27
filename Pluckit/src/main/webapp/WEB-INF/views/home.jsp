<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>PluckIT</title>
<link href="resources/css/home.css" rel="stylesheet">
</head>
<body>
	<img alt="logo" src="resources/img/logo.png">
	<br>
	메인홈페이지
	<br>
	<c:if test="${empInfo eq null }">
		<a href="Login.do">그룹웨어 로그인하기</a>
	</c:if>
	<c:if test="${empInfo ne null }">
		<a href="GroupWareMain.do">그룹웨어 메인으로</a>
		<br><br>
		<a href="LogoutProc.do">로그아웃</a>
	</c:if>
</body>
</html>