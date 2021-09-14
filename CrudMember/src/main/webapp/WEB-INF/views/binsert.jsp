<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 추가</title>
</head>
<body>
	<form action="BoardInsertProc.do" method="post">
		<table border="1" cellspacing="0">
			<tr>
				<td>제목</td><td><input type="text" name="title"></td>
			</tr>
			<tr>
				<td>작성자</td><td><input type="text" name="name"></td>
			</tr>
			<tr>
				<td>내용</td><td><textarea rows="5" cols="25" name="contents"></textarea></td>
			</tr>
		</table>
		<input type="submit" value="게시글 추가"> <input type="button" value="취소" onclick="history.back()">
	</form>
</body>
</html>