<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<a href="Regist.do">회원가입</a> <br>
	<a href="MemberList.do">회원목록</a> <br>
	<input type="button" value="게시판목록" onclick="location.href='BoardList.do'"> <br>
	<input type="button" value="게시글쓰기" onclick="location.href='BoardWrite.do'">	
</body>
</html>