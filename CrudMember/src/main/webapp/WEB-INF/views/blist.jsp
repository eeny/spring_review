<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�Խ��� ���</title>
</head>
<body>
	�� �Խñ� �� : ${bcnt }<br>
	
	<table border="1" cellspacing="0">
		<tr>
			<td>��ȣ</td><td>����</td><td>�ۼ���</td><td>�ۼ���</td><td>���</td>
		</tr>
		<c:forEach var="dto" items="${blist }">
		<tr>
			<td>${dto.idx }</td><td><a href="BoardRead.do?idx=${dto.idx }">${dto.title }</a></td><td>${dto.name }</td><td>${dto.regdate }</td><td><a href="BoardDelete.do?idx=${dto.idx }">����</a></td>
		</tr>
		</c:forEach>
	</table>
	
	<!-- ����¡ �������ϴµ�... -->
	
	
	<hr>
	<a href="main.do">��������</a>
</body>
</html>