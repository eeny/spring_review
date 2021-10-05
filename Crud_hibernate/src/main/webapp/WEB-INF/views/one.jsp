<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<form action="updateData" method="post">
		번호 : ${data.idx}<br>
		<input type="text" name="name" value="${data.name}"><br>
		<input type="text" name="title" value="${data.title}"><br>
		<input type="hidden" name="idx" value="${data.idx}">
		<input type="submit" value="수정">
		</form>	
	<br>
	<a href="selectAll">목록으로</a>
</body>
</html>