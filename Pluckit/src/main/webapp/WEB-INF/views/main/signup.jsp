<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>회원가입</title>
    <link rel="stylesheet" href="resources/css/signup.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>
    <div id="wrap">
        <div class="signupBox">
            <form action="SignupProc.do" method="post" name="signupForm">
                <p>Sign Up</p>

                <!-- 유효성 검사 div -->
                <div class="alert"><i class="fas fa-exclamation-circle"></i>&nbsp;&nbsp;사용자 정보가 올바르지 않습니다.</div>
                
                <!-- 정보 입력 table -->
                <table>
                    <tr>
                        <th>소속</th>
                        <td>
                            <select name="dept_id" id="dept">
                                <option value="100">인사</option>
                                <option value="200">영업</option>
                                <option value="300">마케팅</option>
                                <option value="400">총무회계</option>
                                <option value="500">기술지원</option>
                                <option value="600">전략기획</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>직급</th>
                        <td>
                            <select name="rank_id" id="rank">
                                <option value="8">사장</option>
                                <option value="7">전무</option>
                                <option value="6">이사</option>
                                <option value="5">부장</option>
                                <option value="4">차장</option>
                                <option value="3">과장</option>
                                <option value="2">대리</option>
                                <option value="1" selected>사원</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <th>입사일</th>
                        <td>
                            <input type="date" name="emp_hire" id="">
                        </td>
                    </tr>
                    <tr>
                        <th>사원번호</th>
                        <td>
                            <input type="text" name="emp_id" id="">
                        </td>
                    </tr>
                    <tr>
                        <th>사원명</th>
                        <td>
                            <input type="text" name="emp_name" id="">
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호</th>
                        <td>
                            <input type="password" name="emp_pw" id="">
                        </td>
                    </tr>
                    <tr>
                        <th>비밀번호 확인</th>
                        <td>
                            <input type="password" id="">
                        </td>
                    </tr>
                </table>

                <input type="button" class="submit" value="가입하기" onclick="signup()">
                
                <div class="login">이미 계정이 있으신가요? <a href="Login.do">로그인</a></div>
            </form>
        </div>
    </div>
    
    <script type="text/javascript">
    	function signup() {
			var signupForm = document.signupForm;
			
			signupForm.submit();
		}
    </script>
</body>
</html>