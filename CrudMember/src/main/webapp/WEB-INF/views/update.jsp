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
		��ȣ : ${read.idx }<br>
		���̵� : <input type="text" name="id" value="${read.id }"><br>
		��й�ȣ : <input type="text" name="pw" value="${read.pw }"><br>
		�̸� : <input type="text" name="name" value="${read.name }"><br>
		�̸��� : <input type="text" name="email" value="${read.email }"><br>
		<input type="submit" value="��������"> <input type="button" value="�ڷ�" onclick="history.back()">
	</form>
</body>
</html>