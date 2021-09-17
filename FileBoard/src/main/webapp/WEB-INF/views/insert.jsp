<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>파일 업로드 페이지</title>
</head>
<body>
	<form action="InsertBoardProc.do" method="post" enctype="multipart/form-data">
		내용 <input type="text" name="txt"> <br>
		파일 <input type="file" name="filename"> <br>
		<input type="submit" value="업로드">
	</form>
</body>
</html>