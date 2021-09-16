<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<style type="text/css">
	table {
		border: 1px solid black;
		border-collapse: collapse;
		border-spacing: 0px;
	}
	td {
		border: 1px solid black;
	}
	.chk {
		font-weight: bold;
		color: red;
	}
</style>
</head>
<body>
	<h3>회원가입</h3>
	<form action="MemberInsert.do">
		<table>
			<tr>
				<td>아이디</td><td><input type="text" name="id" id="id" required></td>
			</tr>
			<tr>
				<td colspan="2" class="chk" id="chk"></td>
			</tr>
			<tr>
				<td>비밀번호</td><td><input type="password" name="pw" required></td>
			</tr>
			<tr>
				<td>이름</td><td><input type="text" name="name" required></td>
			</tr>
			<tr>
				<td>이메일</td><td><input type="text" name="email"></td>
			</tr>
			<tr>
				<td>전화번호('-'포함)</td><td><input type="text" name="phone"></td>
			</tr>
			<tr>
				<td>생일</td><td><input type="date" name="birth"></td>
			</tr>
		</table>
		<input type="submit" value="가입하기">
		<input type="button" value="메인으로" onclick="location.href='main.do'">
	</form>
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript">
		$(function() {
			$("#id").keyup(function() {
				var chkId = {
						id: $("#id").val()
				}
				//alert(JSON.stringify(chkId));
				
				$.ajax({
					type: "post",
					url: "isIdExist.do",
					data: JSON.stringify(chkId),
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					success: function(args) {
						$(".chk").text(args.result);
					},
					error: function(args) {
						$(".chk").html(args.responseText + "에러!!");
					}
				});
			});
		});
	</script>
</body>
</html>