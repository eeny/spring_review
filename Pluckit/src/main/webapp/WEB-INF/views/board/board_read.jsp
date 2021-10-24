<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<title>게시판</title>
<!-- CSS 파일 -->
<link rel="stylesheet" href="resources/css/board_read.css">
<!-- CKEditor -->
<script type="text/javascript" src="resources/js/ckeditor/ckeditor.js"></script>
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

					<form action="#" method="post" name="bdForm">
						<table class="noticeTable">
							<colgroup>
								<!--테이블 컬럼 너비 조절하는 태그-->
								<col width="15%" />
								<col width="35%" />
								<col width="15%" />
								<col width="35%" />
							</colgroup>
							<tr>
								<th>작성자</th>
								<td>${post.bm_writer }</td>
								<th>작성일</th>
								<td>${post.bm_regdate }</td>
							</tr>
							<tr>
								<th>제목</th>
								<td colspan="3">${post.bm_title }</td>
							</tr>
							<tr>
								<th>첨부파일</th>
								<td colspan="3">
									<a href="FileDownload.do?bmFile=${post.bm_file }&bmSFile=${post.bm_savedfile}" class="downFile" id="downFile">${post.bm_file }</a>
								</td>
							</tr>
							<tr>
								<td colspan="4">
									${post.bm_content }
									<c:if test="${fn:split(post.bm_file, '.')[1] eq  'jpg' || fn:split(post.bm_file, '.')[1] eq  'png' || fn:split(post.bm_file, '.')[1] eq  'gif' }">
										<div class="fileImg">
											<img name="img" src="resources/upload/${post.bm_savedfile }" onclick="window.open('resources/upload/${post.bm_savedfile }', '_blank')">
										</div>
									</c:if>
								</td>
							</tr>
						</table>

						<script>imgSize("img");</script>

						<div class="buttons">
							<!--글쓰기 버튼들 시작-->
							<a class="write">답글</a>
							<c:if test="${empInfo.emp_auth eq 5 || empInfo.emp_name eq post.bm_writer }">
								<a class="write" href="ModifyPost.do?deptName=${empInfo.deptName }&empAuth=${empInfo.emp_auth }&pageName=${pageName}&bmNum=${post.bm_num}">수정</a>
								<a class="write" onclick="DelConfirm('글을 삭제하시겠습니까?', '${empInfo.deptName }', '${empInfo.emp_auth }', '${pageName}', '${post.bm_num}')">삭제</a>
							</c:if>
							<a class="write cancel" onclick="history.back()">목록</a>
						</div>
						<!--글쓰기 버튼들 끝-->
					</form>

					<!-- 댓글 시작 -->
					<div class="replyWrap">
						<form id="replyForm" name="replyForm" method="post">
							<div class="replyBox">
								<input type="hidden" name="b_id" id="bId" value="${pageName }">
								<input type="hidden" name="bm_num" id="bmNum" value="${post.bm_num }">
								<input type="hidden" name="r_writer" id="rWriter" value="${empInfo.emp_name }">
								<textarea name="r_content" id="rContent"></textarea>
								<input type="button" value="댓글작성" onclick="writeReply()">
							</div>
						</form>

						<p class="replyCount"></p>

						<ul class="replyList">
							<li>
								<div>
									<img src="resources/img/user.png" alt="user">
								</div>
								<div class="replyContent">
									<p class="replyTop">
										<span>김철수 부장</span> <span>2021-10-22 11:55:33</span> <span> <a href="#">수정</a> <a href="#">삭제</a>
										</span>
									</p>
									<p class="replyBottom">내용입니다ㅏㅏㅏㅏㅏㅏ</p>
								</div>
							</li>
						</ul>
					</div>
					<!-- 댓글 끝 -->
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
		
		var delConfirm = function(msg, title, deptName, empAuth, pageName, bmNum) {
			swal(
					{
						title : title,
						text : msg,
						html : true,
						type : "warning",
						showCancelButton : true,
						confirmButtonClass : "btn-danger",
						confirmButtonText : "예",
						cancelButtonText : "아니오",
						closeOnConfirm : false,
						closeOnCancel : true
					}, function(isConfirm) {
						if (isConfirm) {
							location.href = "DeletePostProc.do?deptName=" + deptName + "&empAuth=" + empAuth + "&pageName=" + pageName + "&bmNum=" + bmNum;
						}
					});
		}

		function Alert(msg) {
			alert(msg, 'success');
		}
		function Confirm(msg) {
			confirm('', msg);
		}
		function DelConfirm(msg, deptName, empAuth, pageName, bmNum) {
			delConfirm('', msg, deptName, empAuth, pageName, bmNum);	
		}

		// 게시판 제목 길이 제한 (13자 넘으면 잘림)
		var titleArray = document.getElementsByClassName("maxTitle");
		for (var i = 0; i < titleArray.length; i++) {
			var shortTitle = titleArray[i].innerText.substr(0, 13);
			titleArray[i].innerText = shortTitle;
		}
		
		// 첨부 이미지 크기 제한
		function resize(img){
            var imgWidth = img.width;
            var maxWidth = 600;
            
            if (imgWidth > maxWidth) {
                img.classList.add('resizeImg');
            } else {
            	img.cla
            }
        }
		
		// 페이지 로딩시 댓글목록 불러오기
		$(function() {
			getReplyList();
		});
		
		// 댓글 등록하기
		function writeReply() {
			//var rForm = document.replyForm;
			var rContent = document.getElementById("rContent");
			var bmNum = document.getElementById("bmNum");
			var rWriter = document.getElementById("rWriter");
			var bId = document.getElementById("bId");
			
			var rData = {
					bm_num : bmNum.value,
					r_writer : rWriter.value,
					r_content : rContent.value,
					b_id : bId.value
			}
			//alert(JSON.stringify(rData));
			
			$.ajax({
				type: "POST",
				url: "WriteReplyProc.do",
				data: JSON.stringify(rData),
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success: function(data) {
					if (data.result == "success") {
						getReplyList();
						rContent.value = "";
					}
				},
				error: function(data) {
					alert("시스템 에러 발생!");
				}
			});
		}
		
		// 댓글 목록 가져오기
		function getReplyList() {
			var bmNum = document.getElementById("bmNum");
			var bId = document.getElementById("bId");
			var param = {
					b_id : bId.value,
					bm_num : bmNum.value
			}
			
			$.ajax({
				type: "POST",
				url: "GetReplyProc.do",
				data: JSON.stringify(param),
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success: function(data) {
					var str = "";
					var rCnt = data.length;
					alert(rCnt);
					
					/* if (rCnt > 0) { // 댓글이 존재하는 경우
						for (var i = 0; i < rCnt; i++) {
							str += "<li>";
							str += "<div>";
							str += "<img src='resources/img/user.png' alt='user'>";
							str += "</div>";
							str += "<div class='replyContent'>";
							str += "<p class='replyTop'>";
							str += "<span>" + data[i].r_writer + "</span> <span>" + data[i].r_regdate + "</span>";
							str += " <span><a href=''>수정</a> <a href=''>삭제</a></span>";
							str += "</p>";
							str += "<p class='replyBottom'>" + data[i].r_content + "</p>";
							str += "</div>";
							str += "</li>";
						}
					} else {
						str += "<li><strong>";
                      	str += "등록된 댓글이 없습니다.";
                        str += "</strong></li>";
					}
					
					$(".replyCount").html("댓글 (" + rCnt + ")");
					$(".replyList").html(str); */
				},
				error: function(data) {
					alert("시스템 에러 발생!");
				}
			});
		}
	</script>

</body>
</html>