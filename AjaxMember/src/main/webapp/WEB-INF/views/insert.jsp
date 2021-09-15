<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<form action="InsertProc.do" method="post">
		ID : <input type="text" name="id" id="id"> <br>
		<div id="result" style="color: red"></div>
		PW : <input type="text" name="pw"> <br>
		Name : <input type="text" name="name"> <br>
		<input type="submit" value="가입"> <input type="button" value="뒤로" onclick="history.back()">
	</form>
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script type="text/javascript"> 
		// 서블릿으로 Ajax를 사용하던 방식
		/* $(document).ready(function() {
			$("#id").keyup(function() {
				var params = "id=" + $("#id").val();
				$.ajax({
					type: "post",
					url: "isExist.do",
					data: params,
					dataType: "json",
					success: function (data) {
						$("#result").text(data.res);
					},
					error: function(data) {
						$("#result").html(data.responseText + "에러!!!");
					}
				});
			});
		}); */
		
		// 스프링 방식의 Ajax
		$(document).ready(function() {
			$("#id").keyup(function() {
				var idData = {
						id: $("#id").val()
				} // json형태로 넘길 값의 형태를 만든다!
				
				//alert(JSON.stringify(idData)); // 테스트 코드 
				// JSON.stringify()란?
				// json data를 String 타입으로 변경({"id":"a"} 이런 형태)하는 스크립트 메서드
				
				$.ajax({
					type: "post",
					url: "isIdExist.do",
					data: JSON.stringify(idData),
					contentType: "application/json; charset=utf-8",
					dataType: "json",
					success: function(args) {
						$("#result").text(args.res);
					},
					error: function(args) {
						$("#result").html(args.responseText + "에러!!!");
					}
				});
			});
		});
	</script>
</body>
</html>