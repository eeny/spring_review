<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>ȸ�����</title>
<style type="text/css">
	table {
		border: 1px solid black;
		border-collapse: collapse;
		border-spacing: 0px;
	}
	td {
		border: 1px solid black;
	}
	.dark {
		background-color: #ccc;
	}
	.center {
		text-align: center;
	}
</style>
</head>
<body>
	<h3>ȸ�� ���</h3>
	<table>
		<tr class="dark center">
			<td>���̵�</td><td>��й�ȣ</td><td>�̸�</td><td>�̸���</td><td>��ȭ��ȣ</td><td>����</td><td>������</td><td>���</td>
		</tr>
	<c:forEach var="mdto" items="${mlist }">
		<tr>
			<td>${mdto.id }</td><td>${mdto.pw }</td><td>${mdto.name }</td><td>${mdto.email }</td><td>${mdto.phone }</td><td>${mdto.birth }</td><td>${mdto.regdate }</td><td>${mdto.level }</td>
		</tr>
	</c:forEach>	
	</table>
	<br>
	<input type="button" value="��������" onclick="location.href='main.do'">
</body>
</html>