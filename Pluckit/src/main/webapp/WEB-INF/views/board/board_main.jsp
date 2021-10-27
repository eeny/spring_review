<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<title>게시판</title>
<!-- CSS 파일 -->
<link rel="stylesheet" href="resources/css/board_main.css">
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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/sweetalert/1.1.3/sweetalert.min.css" />
</head>

<body>
	<div id="wrap">
		<!--전체 페이지 감싸는 div-->
		<nav>
			<!--사이드 바(gnbmenu) 시작-->
			<div class="gnb-align">
				<div class="logo">
					<a href="GroupWareMain.do">
						<img src="resources/img/logo_transparent.png" alt="사이드바_로고">
					</a>
				</div>
				<ul class="gnb-menu">
					<li>
						<a href="board_payment_spend.html">
							<i class="far fa-edit"></i> 전자결재
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fas fa-cloud-download-alt"></i> 업무공유
						</a>
					</li>
					<li>
						<a href="Board.do?deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=notice">
							<i class="fas fa-table"></i> 게시판
						</a>
					</li>
					<li>
						<a href="Employee.do">
							<i class="far fa-building"></i> 오피스
						</a>
					</li>
					<li>
						<a href="#">
							<i class="far fa-id-badge"></i> 근태관리
						</a>
					</li>
					<li>
						<a href="#">
							<i class="fas fa-tasks"></i> 설문
						</a>
					</li>
					<li>
						<a href="#">
							<i class="far fa-calendar-alt"></i> 일정관리
						</a>
					</li>
					<!-- <li><a href="#"><i class="fas fa-cogs"></i> 관리자메뉴</a></li> -->
					<c:if test="${empInfo.emp_auth eq 5 }">
						<li>
							<a href="Admin.do">
								<i class="fas fa-cogs"></i> 관리자메뉴
							</a>
						</li>
					</c:if>
				</ul>
			</div>
		</nav>
		<!--사이드 바(gnbmenu) 끝-->

		<main>
			<div class="main-top">
				<!--상단 메뉴 시작-->
				<div class="user-name">
					<p>
						<i class="fas fa-user-circle"></i>&nbsp;${empInfo.deptName }부 ${empInfo.emp_name } ${empInfo.rankName } [사번 : ${empInfo.emp_num } / 권한 : ${empInfo.emp_auth }]
					</p>
				</div>

				<div class="main-top-icon">
					<div class="search">
						<form action="" name=f class="f">
							<input type="search" placeholder="직원 검색" name="search">
							<button type="submit">
								<i class="fas fa-search"></i>
							</button>
						</form>
					</div>
					<i class="fas fa-bullhorn"></i> <i class="fas fa-home" onclick="location.href='Home.do'"></i> <i class="fas fa-power-off" onclick="Confirm('로그아웃 하시겠습니까?')"></i>
				</div>
			</div>
			<!--상단 메뉴 끝-->

			<div class="main-section">
				<!-- 메인 페이지 시작 -->
				<section class="main-left">
					<!--왼쪽 세부 메뉴 시작-->
					<ul>
						<li>
							<a href="Board.do?deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=notice" class="maxTitle ${pageName eq 'notice' ? 'clickBtn' : '' }">공지사항</a>
						</li>
						<c:forEach var="bdto" items="${menuList }">
							<li>
								<a href="Board.do?deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=${bdto.b_id}" class="maxTitle ${pageName eq bdto.b_id ? 'clickBtn' : '' }">${bdto.b_title }</a>
							</li>							
						</c:forEach>
					</ul>
				</section>
				<!--왼쪽 세부 메뉴 끝-->

				<div></div>
				<!--왼쪽 세부 메뉴 가짜 공간-->

				<section class="main-right">
					<!--게시판 본문 시작-->
					<h1>
						<i class="far fa-file-alt"></i> ${pageTitle }
					</h1>

					<!--검색 box 추가-->

					<form action="#">
						<table class="search-box">
							<tr>
								<td>
									<select name="select" class="select-kind">
										<option value="bm_title">제목</option>
										<option value="bm_writer">작성자</option>
									</select>
									<input type="text" placeholder="검색어를 입력하세요">
									<button type="submit">
										<i class="fas fa-search"></i>
									</button>
								</td>
							</tr>
						</table>
					</form>

					<table class="noticeTable">
						<colgroup>
							<!--테이블 컬럼 너비 조절하는 태그-->
							<col width="5%" />
							<col width="52%" />
							<col width="10%" />
							<col width="15%" />
							<col width="9%" />
							<col width="9%" />
						</colgroup>
						<thead>
							<tr>
								<th>No.</th>
								<th>제목</th>
								<th>작성자</th>
								<th>작성일</th>
								<th>조회수</th>
								<th>파일</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach var="bdto" items="${boardList }" varStatus="vs">
							<tr>
								<td>${vs.count}</td>
								<td>
									<c:if test="${bdto.bm_grpdepth > 0 }">
										<c:forEach var="i" begin="0" end="${bdto.bm_grpdepth }">
											&nbsp;&nbsp;&nbsp;&nbsp;
										</c:forEach>
										<img src="resources/img/answer.png" alt="answer" class="answer">&nbsp;&nbsp;
									</c:if>
									<a href="ReadPost.do?deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=${pageName}&bmNum=${bdto.bm_num}">${bdto.bm_title }</a>
								</td>
								<td>${bdto.bm_writer }</td>
								<td>${bdto.bm_regdate }</td>
								<td>${bdto.bm_hit }</td>
								<td>
									<c:choose>
										<c:when test="${fn:split(bdto.bm_file, '.')[1] eq  'jpg' || fn:split(bdto.bm_file, '.')[1] eq  'png' || fn:split(bdto.bm_file, '.')[1] eq  'gif'}">
											<i class="fas fa-file-image"></i>
										</c:when>
										<c:when test="${fn:split(bdto.bm_file, '.')[1] eq  'pptx'}">
											<i class="fas fa-file-powerpoint"></i>
										</c:when>
										<c:when test="${fn:split(bdto.bm_file, '.')[1] eq  'docx'}">
											<i class="fas fa-file-word"></i>
										</c:when>
										<c:when test="${fn:split(bdto.bm_file, '.')[1] eq  'xlsx'}">
											<i class="fas fa-file-excel"></i>
										</c:when>
										<c:when test="${fn:split(bdto.bm_file, '.')[1] eq  'pdf'}">
											<i class="fas fa-file-pdf"></i>
										</c:when>
										<c:when test="${fn:split(bdto.bm_file, '.')[1] eq  'hwp' || fn:split(bdto.bm_file, '.')[1] eq  'txt'}">
											<i class="fas fa-file-alt"></i>
										</c:when>
										<c:when test="${fn:split(bdto.bm_file, '.')[1] eq  'zip' || fn:split(bdto.bm_file, '.')[1] eq  'war'}">
											<i class="fas fa-file-archive"></i>
										</c:when>
										<c:when test="${fn:split(bdto.bm_file, '.')[1] eq  'exe' || fn:split(bdto.bm_file, '.')[1] eq  'html' || fn:split(bdto.bm_file, '.')[1] eq  'jar'}">
											<i class="fas fa-file"></i>
										</c:when>
										<c:otherwise>
										</c:otherwise>
									</c:choose>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					<div class="paging">
						<!--페이징 시작-->
						<c:if test="${paging.pageNum > 1 }">
							<span><a href="Board.do?pageNum=1&deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=${pageName}">
									<i class="fas fa-angle-double-left"></i>
								</a></span>
							<span><a href="Board.do?pageNum=${paging.pageNum-1 }&deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=${pageName}">
									<i class="fas fa-angle-left"></i>
								</a></span>
						</c:if>
						
						<c:if test="${paging.totalCount < 1 }">
							<span class="nowPage"><a class="nowPage">1</a></span>
						</c:if>
						
						<c:if test="${paging.totalCount > 0 }">
							<c:forEach var="i" begin="${paging. startPage}" end="${paging.endPage }" step="1">
								<c:if test="${paging.pageNum eq i }">
									<span class="nowPage"><a href="Board.do?pageNum=${i }&deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=${pageName}" class="nowPage">${i }</a></span>
								</c:if>
								<c:if test="${paging.pageNum ne i }">
									<span><a href="Board.do?pageNum=${i }&deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=${pageName}">${i }</a></span>
								</c:if>
							</c:forEach>							
						</c:if>
						
						<c:if test="${paging.pageNum < paging.totalPage }">
							<span><a href="Board.do?pageNum=${paging.pageNum+1 }&deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=${pageName}">
									<i class="fas fa-angle-right"></i>
								</a></span>
							<span><a href="Board.do?pageNum=${paging.totalPage }&deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=${pageName}">
									<i class="fas fa-angle-double-right"></i>
								</a></span>
						</c:if>
					</div>
					<!--페이징 끝-->
					<div class="buttons">
						<!--글쓰기 버튼들 시작-->
						<a href="WritePost.do?deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth}&pageName=${pageName }" class="write">글쓰기</a>
					</div>
					<!--글쓰기 버튼들 끝-->
				</section>
				<!--게시판 본문 끝-->
			</div>
			<!-- 메인 페이지 끝 -->
		</main>
	</div>

	<script>
		// toastr 설정
		toastr.options = {
			"closeButton" : false,
			"debug" : false,
			"newestOnTop" : false,
			"progressBar" : false,
			"positionClass" : "toast-top-right",
			"preventDuplicates" : false,
			"onclick" : null,
			"showDuration" : "300",
			"hideDuration" : "1000",
			"timeOut" : "1000",
			"extendedTimeOut" : "1000",
			"showEasing" : "swing",
			"hideEasing" : "linear",
			"showMethod" : "fadeIn",
			"hideMethod" : "fadeOut"
		}

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

		// 게시판 제목 길이 제한 (13자 넘으면 잘림)
		var titleArray = document.getElementsByClassName("maxTitle");
		for (var i = 0; i < titleArray.length; i++) {
			var shortTitle = titleArray[i].innerText.substr(0, 13);
			titleArray[i].innerText = shortTitle;
		}
	</script>

</body>
</html>