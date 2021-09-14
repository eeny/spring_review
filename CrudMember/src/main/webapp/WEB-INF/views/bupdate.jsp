<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 읽기</title>
</head>
<body>
	<form action="BoardUpdateProc.do" method="post">
		<table border="1" cellspacing="0">
			<tr>
				<td>글번호</td><td>${bread.idx }</td>
			</tr>
			<tr>
				<td>작성일</td><td>${bread.regdate }</td>
			</tr>
			<tr>
				<td>제목</td><td><input type="text" name="title" value="${bread.title }"></td>
			</tr>
			<tr>
				<td>작성자</td><td><input type="text" name="name" value="${bread.name }"></td>
			</tr>
			<tr>
				<td>내용</td><td><textarea rows="5" cols="25" name="contents">${bread.contents }</textarea></td>
			</tr>
		</table>
		<input type="hidden" name="idx" value="${bread.idx }">
		<input type="submit" value="게시글 수정"> <input type="button" value="취소" onclick="history.back()">
	</form>
</body>
</html>