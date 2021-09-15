<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
</head>
<body>
	총 개수 : ${cnt } 개<br>

	<form action="SearchList.do">
		이름 검색 : <input type="text" name="name">
		<input type="submit" value="검색">
	</form>
	<hr>

	<%-- <c:forEach var="dto" items="${list }"> --%> 
	<!-- 페이징처리를 적용하지 않았을 때 & 검색 기능 적용할 때(검색페이징은 수정해야함...)-->
	<c:forEach var="dto" items="${pageList }">
		${dto.idx } / ${dto.name } / ${dto.id } / ${dto.pw } <br>
	</c:forEach>
	
	<c:forEach var="i" begin="1" end="${pageCount }" step="1">
		<a href="MemberList.do?pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	<hr>
	<input type="button" value="뒤로" onclick="history.back()">
</body>
</html>