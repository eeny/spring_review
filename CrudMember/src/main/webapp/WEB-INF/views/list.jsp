<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	총 회원수 : ${total }<br><br>
	<c:forEach var="dto" items="${list }">
		${dto.idx }/${dto.id }/${dto.pw }/<a href="MemberRead.do?idx=${dto.idx }">${dto.name }</a>/${dto.email }/<a href="MemberDelete.do?idx=${dto.idx}">삭제</a><br>
	</c:forEach>
	
	<a href="main.do">메인으로</a>
</body>
</html>