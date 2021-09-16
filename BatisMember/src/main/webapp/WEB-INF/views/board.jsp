<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�� �б�</title>
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
			<td>���̵�</td><td>${bdata.id }</td>
		</tr>
		<tr>
			<td>�̸�</td><td>${bdata.name }</td>
		</tr>
		<tr>
			<td>����</td><td>${bdata.title }</td>
		</tr>
		<tr>
			<td>�ۼ���</td><td>${bdata.regdate }</td>
		</tr>
		<tr>
			<td>��ȸ��</td><td>${bdata.hit }</td>
		</tr>
		<tr>
			<td>����</td><td>${bdata.content }</td>
		</tr>
		<tr>
			<td>����(�̹���)</td><td>${bdata.img }</td>
		</tr>
	</table>
	<br>
	<input type="button" value="�ۼ���" onclick="location.href='ModifyBoard.do?idx=${bdata.idx}'">
	<input type="button" value="�ۻ���" onclick="location.href='DeleteBoard.do?idx=${bdata.idx}'">
	<br><br>
	<input type="button" value="��������" onclick="location.href='main.do'">
</body>
</html>