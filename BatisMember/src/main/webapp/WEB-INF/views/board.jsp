<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>글 읽기</title>
<style type="text/css">
	table {
		border: 1px solid black;
		border-collapse: collapse;
		border-spacing: 0px;
	}
	td {
		border: 1px solid black;
	}
</style>
</head>
<body>
	<table>
		<tr>
			<td>아이디</td><td>${bdata.id }</td>
		</tr>
		<tr>
			<td>이름</td><td>${bdata.name }</td>
		</tr>
		<tr>
			<td>제목</td><td>${bdata.title }</td>
		</tr>
		<tr>
			<td>작성일</td><td>${bdata.regdate }</td>
		</tr>
		<tr>
			<td>조회수</td><td>${bdata.hit }</td>
		</tr>
		<tr>
			<td>내용</td><td>${bdata.content }</td>
		</tr>
		<tr>
			<td>파일(이미지)</td><td>${bdata.img }</td>
		</tr>
	</table>
	<br>
	<input type="button" value="글수정" onclick="location.href='ModifyBoard.do?idx=${bdata.idx}'">
	<input type="button" value="글삭제" onclick="location.href='DeleteBoard.do?idx=${bdata.idx}'">
	<br><br>
	<input type="button" value="메인으로" onclick="location.href='main.do'">
</body>
</html>