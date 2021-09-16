<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="Regist.do">회원가입</a> <br>
	<a href="MemberList.do">회원목록</a> <br>
	<input type="button" value="게시판목록" onclick="location.href='BoardList.do'"> <br>
	<input type="button" value="게시글쓰기" onclick="location.href='BoardWrite.do'">
	<br><br>
	
	<!-- 원래는 세션이니까 ${sessionScope.userInfo} 이렇게 썼었다... -->
	<c:if test="${userInfo eq null }"> 
		<form action="Login.do" method="post">
			아이디 <input type="text" name="id"><br>
			비밀번호 <input type="text" name="pw"><br>
			<input type="submit" value="로그인">
		</form>
	</c:if>
	
	<c:if test="${userInfo ne null }">
		${userInfo.name } 님 반갑습니다!
		<a href="Logout.do">로그아웃</a>
	</c:if>	
</body>
</html>