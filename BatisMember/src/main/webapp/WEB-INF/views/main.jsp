<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>
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
	<h3>로그인</h3>
	<c:if test="${userInfo eq null }">
		<form action="Login.do" method="post">
			<table>
				<tr>
					<td class="dark">아이디</td><td><input type="text" name="id" required></td>
				</tr>
				<tr>
					<td class="dark">비밀번호</td><td><input type="password" name="pw" required></td>
				</tr>
			</table>
			<br>
			<input type="submit" value="로그인"> 
		</form>	
		<br>
		<input type="button" value="회원가입하기" onclick="location.href='Regist.do'">
		<input type="button" value="회원목록" onclick="location.href='AllMembers.do'">
	</c:if>
	<c:if test="${userInfo ne null }">
		<b>${userInfo.name }</b>님 접속중... <br>
		<input type="button" value="로그아웃" onclick="location.href='Logout.do'">
	</c:if>
	
	<h3>게시판</h3>
	<p>총 ${totalCount }개의 게시물이 있습니다.</p>
	<table>
		<tr class="dark center">
			<td>글번호</td><td>제목</td><td>작성자</td><td>작성일자</td><td>조회수</td>
		</tr>
	<c:forEach var="bdto" items="${bPageList }">
		<tr>
			<td>${bdto.idx }</td><td><a href="ReadBoard.do?idx=${bdto.idx }">${bdto.title }</a></td><td>${bdto.name }</td><td>${bdto.regdate }</td><td>${bdto.hit }</td>
		</tr>
	</c:forEach>
	</table>
	
	<c:forEach var="i" begin="1" end="${pagecount }" step="1">
		<a href="main.do?pageNum=${i }">[${i }]</a>
	</c:forEach>
	
	
	<br>
	<c:if test="${userInfo.name ne null }">
		<input type="button" value="글작성" onclick="location.href='WriteBoard.do'">
	</c:if>
</body>
</html>