<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>게시판 목록</title>
</head>
<body>
	총 게시글 수 : ${bcnt }<br>
	
	<table border="1" cellspacing="0">
		<tr>
			<td>번호</td><td>제목</td><td>작성자</td><td>작성일</td><td>비고</td>
		</tr>
		<c:forEach var="dto" items="${blist }">
		<tr>
			<td>${dto.idx }</td><td><a href="BoardRead.do?idx=${dto.idx }">${dto.title }</a></td><td>${dto.name }</td><td>${dto.regdate }</td><td><a href="BoardDelete.do?idx=${dto.idx }">삭제</a></td>
		</tr>
		</c:forEach>
	</table>
	
	<!-- 페이징 만들어야하는데... -->
	
	
	<hr>
	<a href="main.do">메인으로</a>
</body>
</html>