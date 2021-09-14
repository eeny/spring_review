<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<a href="Insert">°ª ³Ö±â</a> <br><br><br>

	<c:forEach var="dto" items="${list }">
		${dto.idx }/<a href="Delete?idx=${dto.idx }">${dto.name }</a> <br>
	</c:forEach>
	
	
</body>
</html>
