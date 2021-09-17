<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���� ���ε� ��� ������</title>
<style type="text/css">
	.thumnail {
		width: 100px;
		height: 100px;
	}
</style>
</head>
<body>
	<c:forEach var="dto" items="${list }">
		<img src="resources/upload/${dto.filename }" class="thumnail"> / ${dto.idx } / ${dto.txt } / ${dto.filename } / <a href="FileDelete.do?idx=${dto.idx}">����</a> <br>
	</c:forEach>
	
	<hr>
	<input type="button" value="Ȩ����" onclick="location.href='main.do'">
</body>
</html>