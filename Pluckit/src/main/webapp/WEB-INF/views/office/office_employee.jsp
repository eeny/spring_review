<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.11.4/jquery-ui.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />

    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100;300;400;500;700;900&display=swap"
        rel="stylesheet">
    <title>오피스 - 직원목록</title>
    <style>
        *,
        html,
        body {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        /* 공통으로 작성해 놓은 CSS 시작*/
        body {
            font-family: 'Noto Sans KR', sans-serif;
            width: 1920px;
            height: 100%;
            background-color: #f4f7f7;
        }

        ul li {
            list-style: none;
        }

        a {
            text-decoration: none;
        }

        img {
            display: block;
            width: 100%;
        }

        /********************************************************************************/
        /* 공통으로 작성해 놓은 CSS 끝*/

        #wrap {
            /*전체를 감싸는 wrap*/
            width: 100%;
            display: flex;
            justify-content: space-between;
        }

        /*사이드 바 CSS 시작*/

        nav {
            width: 200px;
            height: 100%;
        }

        .gnb-align {
            width: 200px;
            height: 100%;
            background-color: #0091EA;
            position: fixed;
            top: 0;
            left: 0;
        }

        .logo {
            text-align: center;
            background-color: #0091EA;
        }

        .gnb-menu li {
            width: 100%;
            margin: 10px 0;
            padding: 12px;
            text-align: left;
            cursor: pointer;
        }

        .gnb-menu li:hover {
            background-color: #0382c2;
        }

        .gnb-menu li:first-child {
            margin-top: 0;
        }

        .gnb-menu li a {
            color: #efefef;
            font-size: 16px;
        }

        .gnb-menu li a i {
            margin-right: 10px;
            font-size: 20px;
        }

        /********************************************************************************/
        /*사이드 바 CSS 끝*/

        main {
            /*사이드 바 제외 main css*/
            width: 1720px;
            height: 100%;
            background-color: #f4f7f7;
        }

        /*상단 메뉴 CSS 시작*/
        .main-top {
            height: 50px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #ccc;
        }

        .user-name p {
            color: #333;
            font-size: 20px;
            font-weight: 600;
            margin-left: 30px;
        }

        .main-top-icon {
            display: flex;
        }

        .main-top-icon i {
            font-size: 28px;
            margin-right: 30px;
            padding-top: 6px;
            color: #333;

            cursor: pointer;
        }

        .search {
            border: 3px solid #0091EA;
            padding: 5px;
            margin-right: 30px;
            width: 250px;
            color: #333;
        }

        .f {
            display: flex;
            justify-content: space-between;
        }

        .f>input[type=search] {
            background-color: transparent;
            border: none;
        }

        input::-webkit-input-placeholder {
            color: #333;
            font-size: 12px;
        }

        .f>button {
            background-color: transparent;
            border: none;
        }

        .f>button>i {
            font-size: 20px;
            margin-right: 0;
            padding-top: 0;
            padding: 0 10px;
        }

        /********************************************************************************/
        /*상단 메뉴 CSS 끝*/

        .main-section {
            display: flex;
            justify-content: space-between;
        }

        /*************** 여기서부터 추가했음 ******************/
        /*왼쪽 세부 메뉴*/
        .main-left {
            width: 200px;
            height: 100%;
            background-color: white;
            border-right: 1px solid #ccc;
            position: fixed;
        }

        .main-left ul {
            width: 100%;
            height: 100%;
        }

        .main-left li {
            border-bottom: 1px solid #ccc;
        }

        .main-left a {
            height: 45px;
            display: block;
            color: #333;
            font-size: 14px;
            text-align: left;
            line-height: 45px;
            padding-left: 10px;
        }

        .main-left li:first-child a {
            color: white;
            font-weight: bold;
            background-color: #0382c2;
        }

        /*오른쪽 본문 부분*/
        .main-right {
            width: 1520px;
            height: 100%;
            padding: 15px 30px;
        }

        .main-right h1 {
            /*게시판 제목 - 공지사항*/
            color: #333;
            font-size: 18px;
            margin-bottom: 30px;
        }

        /*검색 box css 수정 시작*/

        .search-box {
            height: 30px;
            background-color: #fff;
            border-collapse: collapse;
            border: 1px solid #ccc;
        }

        .select-kind {
            height: 30px;
            border: none;
            border-right: 1px solid #ccc;
            color: #333;
        }

        .search-box  input[type=text] {
            height: 30px;
            border: none;
            padding: 0 10px;
        }

        .search-box button {
            border: none;
            color: #333; 
            width: 30px;
            height: 30px;
            
            cursor: pointer;
        }


        /*검색 box css 수정 끝*/

        table.noticeTable {
            /*테이블*/
            width: 100%;
            margin-top: 30px;
            border: 1px solid #ccc;
            border-collapse: collapse;
            color: #333;
            font-size: 12px;
            text-align: center;
            background-color: white;
            table-layout: fixed;
            /* 테이블 너비 고정 */
            word-break: break-all;
            /* 텍스트 자동 줄바꿈 */
        }

        .noticeTable th {
            height: 42px;
            background-color: blue;
            color: white;
        }

        .noticeTable tbody tr:hover {
            /* 한줄씩 hover 효과 */
            background-color: #f4f7f7;
        }

        .noticeTable td {
            height: 32px;
            border-bottom: 1px solid #ccc;
        }

        .noticeTable td:nth-child(2) {
            /* 제목 컬럼 */
            text-align: center;
        }

        .paging {
            /*페이징*/
            text-align: center;
        }

        .paging span {
            width: 25px;
            height: 25px;
            display: inline-block;
            margin: 30px 3px;
            border: 1px solid #ccc;
        }

        .paging a {
            font-size: 12px;
            line-height: 22px;
            /* 수직 중앙 정렬 */
            color: #333;
            display: block;
        }

        .buttons {
            /*버튼들 - 글쓰기*/
            text-align: right;
        }

        a.write {
            padding: 7px 15px;
            border-radius: 3px 3px 3px 3px;
            background-color: #0382c2;
            color: white;
            font-size: 14px;
            font-weight: bold;
            cursor: pointer;
        }
    </style>
</head>

<body>
    <div id="wrap">
        <!--전체 페이지 감싸는 div-->
        <nav>
            <!--사이드 바(gnbmenu) 시작-->
            <div class="gnb-align">
                <div class="logo">
                    <a href="#"><img src="logo_transparent.png" alt="사이드바_로고"></a>
                </div>
                <ul class="gnb-menu">
                    <li><a href="board_payment_spend.html"><i class="far fa-edit"></i> 전자결재</a></li>
                    <li><a href="board_share_receive.html"><i class="fas fa-cloud-download-alt"></i> 업무공유</a></li>
                    <li><a href="board_notice.html"><i class="fas fa-table"></i> 게시판</a></li>
                    <li><a href="board_office_employee.html"><i class="far fa-building"></i> 오피스</a></li>
                    <li><a href="#"><i class="far fa-id-badge"></i> 근태관리</a></li>
                    <li><a href="#"><i class="fas fa-tasks"></i> 설문</a></li>
                    <li><a href="#"><i class="far fa-calendar-alt"></i> 일정관리</a></li>
                    <li><a href="#"><i class="fas fa-cogs"></i> 관리자메뉴</a></li>
                </ul>
            </div>
        </nav>
        <!--사이드 바(gnbmenu) 끝-->

        <main>
            <div class="main-top">
                <!--상단 메뉴 시작-->
                <div class="user-name">
                    <p>김뫄뫄 사원</p>
                </div>

                <div class="main-top-icon">
                    <div class="search">
                        <form action="" name=f class="f">
                            <input type="search" placeholder="직원 검색" name="search">
                            <button type="submit"><i class="fas fa-search"></i></button>
                        </form>
                    </div>
                    <i class="fas fa-bullhorn"></i>
                    <i class="fas fa-home"></i>
                    <i class="far fa-user-circle"></i>
                </div>
            </div>
            <!--상단 메뉴 끝-->

            <div class="main-section">
                <!-- 메인 페이지 시작 -->
                <section class="main-left">
                    <!--왼쪽 세부 메뉴 시작-->
                    <ul>
                        <li><a href="#">직원목록</a></li>
                        <li><a href="#">거래처관리</a></li>
                        <li><a href="#">주소록</a></li>
                    </ul>
                </section>
                <!--왼쪽 세부 메뉴 끝-->

                <div></div>
                <!--왼쪽 세부 메뉴 가짜 공간-->

                <section class="main-right">
                    <!--게시판 본문 시작-->
                    <h1><i class="far fa-file-alt"></i> 직원목록</h1>

                    <!--검색 box 추가-->

                    <form action="SerchProc.do">
                        <table class="search-box">
                            <tr>
                                <td>
                                    <select name="select" class="select-kind">
                                        <option value="emp_name">이름</option>
                                        <option value="emp_num">사번</option>
                                        <option value="rank_id">직위</option>
                                        <option value="dept_id">소속</option>
                                    </select>
                                    <input type="text" placeholder="검색어를 입력하세요" name="search">
                                    <button type="submit"><i class="fas fa-search"></i></button>
                                </td>
                            </tr>
                        </table>
                    </form>

                    <table class="noticeTable">
                        <colgroup>
                            <!--테이블 컬럼 너비 조절하는 태그-->
                            <col width="15%" />
                            <col width="15%" />
                            <col width="20%" />
                            <col width="20%" />
                            <col width="15%" />
                            <col width="15%" />
                        </colgroup>

                        <tr>                            
                            <th>사번</th>
                            <th>이름</th>
                            <th>전화번호</th>
                            <th>이메일</th>
                            <th>소속</th>
                            <th>직위</th>
                        </tr>
                        
                        <c:forEach var="employee" items="${employeeList}">
                        	<tr>
                        		<td>${employee.emp_num}</td>
                        		<td>${employee.emp_name}</td>
                        		<td>${employee.emp_tel}</td>
                        		<td>${employee.emp_email}</td>
                        		<td>${employee.dept_id}</td>
                        		<td>${employee.rank_id}</td>
                        	</tr>                      
                        </c:forEach>
                        
                    </table>
                    
                    <div class="paging">
                        <!--페이징 시작-->
                        <span><a href="#">1</a></span><span><a href="#">2</a></span><span><a href="#">3</a></span>
                    </div>
                    <!--페이징 끝-->
                    
                </section>
                <!--게시판 본문 끝-->
            </div> <!-- 메인 페이지 끝 -->
        </main>
    </div>

</body>

</html>