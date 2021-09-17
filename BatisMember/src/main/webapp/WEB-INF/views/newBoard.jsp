<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새글쓰기</title>
<style type="text/css">
	table {
		border: 1px solid black;
		border-collapse: collapse;
		border-spacing: 0px;
	}
	td {
		border: 1px solid black;
	}
	textarea {
		width: 200px;
	}
</style>
</head>
<body>
	<h3>새글쓰기</h3>
	<form action="BoardInsert.do" method="post">
		<table>
			<tr>
				<td>아이디</td><td><input type="text" name="id" value="${userInfo.id }" readonly></td>
			</tr>
			<tr>
				<td>이름</td><td><input type="text" name="name" value="${userInfo.name }" readonly></td>
			</tr>
			<tr>
				<td>제목</td><td><input type="text" name="title" required></td>
			</tr>
			<tr>
				<td>내용</td><td><textarea name="content"></textarea></td>
			</tr>
			<tr>
				<td>파일(이미지)</td><td><input type="file" name="img"></td>
			</tr>
		</table>
		<input type="submit" value="글등록">
	</form>
	<br>
	<input type="button" value="메인으로" onclick="location.href='main.do'">
</body>
</html>