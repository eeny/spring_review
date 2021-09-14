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
	<form action="MemberUpdateProc.do">
		<input type="hidden" name="idx" value="${read.idx }">
		번호 : ${read.idx }<br>
		아이디 : <input type="text" name="id" value="${read.id }"><br>
		비밀번호 : <input type="text" name="pw" value="${read.pw }"><br>
		이름 : <input type="text" name="name" value="${read.name }"><br>
		이메일 : <input type="text" name="email" value="${read.email }"><br>
		<input type="submit" value="정보수정"> <input type="button" value="뒤로" onclick="history.back()">
	</form>
</body>
</html>