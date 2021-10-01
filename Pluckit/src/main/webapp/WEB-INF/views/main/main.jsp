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
    <title>PluckIT 그룹웨어</title>
    <!-- CSS 파일 -->
    <link rel="stylesheet" href="resources/css/main.css"> 
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
    <!-- Google Font -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@900&display=swap" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap" rel="stylesheet">
    <!-- jQuery -->
    <script src="http://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<!-- Datepicker -->
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <!-- toastr -->
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css" integrity="sha512-3pIirOrwegjM6erE5gPSwkUzO+3cTjpnV9lexlNZqvupR64iZBnOOTiiLPb9M36zpMScbmUNIcHUqKD47M719g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	<!-- Sweet Alert -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.js"></script>
    <link rel="stylesheet"href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
</head>
<body>
    <div id="wrap"> <!--전체 페이지 감싸는 div-->
        <nav> <!--사이드 바(gnbmenu) 시작-->
            <div class="gnb-align">
                <div class="logo">
                    <a href="GroupWareMain.do"><img src="resources/img/logo_transparent.png" alt="사이드바_로고"></a>
                </div>
                <ul class="gnb-menu">
                    <li><a href="board_payment_spend.html"><i class="far fa-edit"></i> 전자결재</a></li>
                    <li><a href="#"><i class="fas fa-cloud-download-alt"></i> 업무공유</a></li>
                    <li><a href="board_notice.html"><i class="fas fa-table"></i> 게시판</a></li>
                    <li><a href="Employee.do"><i class="far fa-building"></i> 오피스</a></li>
                    <li><a href="#"><i class="far fa-id-badge"></i> 근태관리</a></li>
                    <li><a href="#"><i class="fas fa-tasks"></i> 설문</a></li>
                    <li><a href="#"><i class="far fa-calendar-alt"></i> 일정관리</a></li>
                    <!-- <li><a href="#"><i class="fas fa-cogs"></i> 관리자메뉴</a></li> -->
                    <c:if test="${empInfo.emp_auth eq 5 }">
                    <li><a href="#"><i class="fas fa-cogs"></i> 관리자메뉴</a></li>
                    </c:if>
                </ul>
            </div>
        </nav> <!--사이드 바(gnbmenu) 끝-->

        <main>
            <div class="main-top"> <!--상단 메뉴 시작-->
                <div class="user-name">
                    <p><i class="fas fa-user-circle"></i>&nbsp;${empInfo.deptName }부 ${empInfo.emp_name } ${empInfo.rankName } [사번 : ${empInfo.emp_num } / 권한 : ${empInfo.emp_auth }] </p>
                </div>

                <div class="main-top-icon">
                    <div class="search">
                        <form action="" name=f class="f">
                            <input type="search" placeholder="직원 검색" name="search">
                            <button type="submit"><i class="fas fa-search"></i></button>
                        </form>
                    </div>
                    <i class="fas fa-bullhorn"></i>
                    <i class="fas fa-home" onclick="location.href='Home.do'"></i>
                    <i class="fas fa-power-off" onclick="Confirm('로그아웃 하시겠습니까?')"></i>
                </div>
            </div> <!--상단 메뉴 끝-->

            <div class="main-section"> <!-- 메인 페이지 시작 -->
                <section class="section-left">
                    <div class="myPage">
                        마이페이지
                    </div>
                    <div class="btn-G">
                        바로가기 버튼 모음 
                    </div>
                </section>

                <section class="section-right">
                    <div class="section-right-top">
                        <div class="section-right-top-left">
                            업무공유
                        </div>
                        <div class="section-right-top-right">
                            일정공유(달력)
                        </div>
                    </div>

                    <div class="section-right-bottom">
                        공지사항
                    </div>
                </section>
            </div> <!-- 메인 페이지 끝 -->
        </main>
    </div>
	
	<script type="text/javascript">
		// Sweet Alert 설정
	    var alert = function(msg, type) {
	        swal({
	            title : '',
	            text : msg,
	            type : type,
	            timer : 1500,
	            customClass : 'sweet-size',
	            showConfirmButton : false
	        });
	    }
	
	    var confirm = function(msg, title, resvNum) {
	        swal({
	            title : title,
	            text : msg,
	            type : "warning",
	            showCancelButton : true,
	            confirmButtonClass : "btn-danger",
	            confirmButtonText : "예",
	            cancelButtonText : "아니오",
	            closeOnConfirm : false,
	            closeOnCancel : true
	        }, function(isConfirm) {
	            if (isConfirm) {
	                //swal('', '로그아웃 하셨습니다', "success");
	                location.href = "LogoutProc.do";
	            }
	        });
	    }
	
	    function Alert(msg) {
	        alert(msg, 'success');
	    }
	    function Confirm(msg) {
	        confirm('', msg);
	    }
	</script>
</body>
</html>