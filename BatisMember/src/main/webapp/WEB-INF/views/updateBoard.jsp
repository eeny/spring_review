<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 수정하기</title>
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
	<h3>글수정</h3>
	<form action="UpdateBoard.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="idx" value="${bdata.idx }">
		<table>
			<tr>
				<td>아이디</td><td>${bdata.id }</td>
			</tr>
			<tr>
				<td>이름</td><td>${bdata.name }</td>
			</tr>
			<tr>
				<td>제목</td><td><input type="text" name="title" value="${bdata.title }"></td>
			</tr>
			<tr>
				<td>내용</td><td><textarea name="content">${bdata.content }</textarea></td>
			</tr>
			<tr>
				<td>파일(이미지)</td><td><input type="file" name="img"></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="글수정">
		<input type="button" value="뒤로가기" onclick="history.back()">
	</form>
</body>
</html>