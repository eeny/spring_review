<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>파일 업로드 목록 페이지</title>
<style type="text/css">
	.thumnail {
		width: 100px;
		height: 100px;
	}
</style>
</head>
<body>
	<c:forEach var="dto" items="${list }">
		<img src="resources/upload/${dto.filename }" class="thumnail"> / ${dto.idx } / ${dto.txt } / ${dto.filename } / <a href="FileDelete.do?idx=${dto.idx}">삭제</a> <br>
	</c:forEach>
	
	<hr>
	<input type="button" value="홈으로" onclick="location.href='main.do'">
</body>
</html>