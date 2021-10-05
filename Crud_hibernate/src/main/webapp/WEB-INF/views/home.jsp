<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
</head>
<body>

<form action="insertData">
	이름<input type="text" name="name"><br>
	제목<input type="text" name="title"><br>
	<input type="submit" value="입력">
</form>
<br>


읽어온 데이터
<p>
	<c:forEach var="dto" items="${data}">	
		<a href="deleteData?idx=${dto.idx}">삭제 : ${dto.idx}</a>		
		/${dto.name}/
		<a href="selectOne?idx=${dto.idx}">수정 : ${dto.title}</a><br>
	</c:forEach>
</p>





</body>
</html>
