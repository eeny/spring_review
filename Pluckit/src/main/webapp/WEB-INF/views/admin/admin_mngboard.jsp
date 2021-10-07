<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<title>관리자메뉴 - 게시판 관리</title>
<!-- CSS 파일 -->
<link rel="stylesheet" href="resources/css/admin_mngboard.css">
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
						<a href="board_notice.html">
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
							<a href="#">게시판관리</a>
						</li>
						<li>
							<a href="#">인사관리</a>
						</li>
						<li>
							<a href="#">회사정보관리</a>
						</li>
					</ul>
				</section>
				<!--왼쪽 세부 메뉴 끝-->

				<div></div>
				<!--왼쪽 세부 메뉴 가짜 공간-->

				<section class="main-right">
					<!--게시판 본문 시작-->
					<h1>
						<i class="far fa-file-alt"></i> 게시판관리 <i class="fas fa-angle-right"></i> 게시판 생성
					</h1>

					<form action="MakeBoard.do" name="mbForm">
						<table class="makeBoardTable">
							<colgroup>
								<!--테이블 컬럼 너비 조절하는 태그-->
								<col width="12.5%" />
								<col width="12.5%" />
								<col width="12.5%" />
								<col width="12.5%" />
								<col width="12.5%" />
								<col width="12.5%" />
								<col width="12.5%" />
								<col width="12.5%" />
							</colgroup>

							<tr>
								<th>게시판 코드</th>
								<td colspan="3">
									<input type="text" name="b_id" id="b_id" placeholder="영어로 입력" oninput="chkEng(this)" onblur="chkBId(this)">
								</td>
								<th>담당부서</th>
								<td colspan="3">
									<select name="dept_id" id="dept_id">
										<option value="100" selected>인사</option>
										<option value="200">영업</option>
										<option value="300">마케팅</option>
										<option value="400">총무회계</option>
										<option value="500">기술지원</option>
										<option value="600">전략기획</option>
									</select>
								</td>
							</tr>

							<tr>
								<th>게시판 제목</th>
								<td colspan="3">
									<input type="text" name="b_title" id="b_title">
								</td>
								<th>게시판 생성자</th>
								<td colspan="3">
									<input type="text" name="b_writer" id="b_writer" value="${empInfo.emp_name }" readonly>
								</td>
							</tr>

							<tr>
								<th>읽기권한</th>
								<td>
									<select name="b_readAuth" id="b_readAuth">
										<option value="0" selected>모두</option>
										<option value="1">직원</option>
									</select>
								</td>
								<th>쓰기권한</th>
								<td>
									<select name="b_writeAuth" id="b_writeAuth">
										<option value="0" selected>모두</option>
										<option value="1">직원</option>
									</select>
								</td>
								<th>댓글권한</th>
								<td>
									<select name="b_replyAuth" id="b_replyAuth">
										<option value="0" selected>모두</option>
										<option value="1">직원</option>
									</select>
								</td>
								<th>다운권한</th>
								<td>
									<select name="b_downAuth" id="b_downAuth">
										<option value="0" selected>모두</option>
										<option value="1">직원</option>
									</select>
								</td>
							</tr>
						</table>

						<!--글쓰기 버튼들 시작-->
						<div class="buttons">
							<input type="button" value="게시판생성" onclick="makeBoard()">
							<input type="reset" value="취소">
						</div>
						<!--글쓰기 버튼들 끝-->
					</form>

					<h1>
						<i class="far fa-file-alt"></i> 게시판 관리 <i class="fas fa-angle-right"></i> 게시판 목록
					</h1>

					<!--검색 box 추가-->
					<form action="#">
						<table class="search-box">
							<tr>
								<td>
									<select name="select" class="select-kind">
										<option value="b_id">게시판 코드</option>
										<option value="dept_id">담당부서</option>
										<option value="b_title">게시판 제목</option>
									</select>
									<input type="text" name="search" placeholder="검색어를 입력하세요">
									<button type="submit">
										<i class="fas fa-search"></i>
									</button>
								</td>
							</tr>
						</table>
					</form>

					<table class="boardListTable">
						<colgroup>
							<!--테이블 컬럼 너비 조절하는 태그-->
							<col width="10%" />
							<col width="10%" />
							<col width="30%" />
							<col width="5%" />
							<col width="5%" />
							<col width="5%" />
							<col width="5%" />
							<col width="10%" />
							<col width="10%" />
							<col width="10%" />
						</colgroup>

						<tr>
							<th>게시판 코드</th>
							<th>담당부서</th>
							<th>게시판 제목</th>
							<th>읽기권한</th>
							<th>쓰기권한</th>
							<th>댓글권한</th>
							<th>다운권한</th>
							<th>생성자</th>
							<th>최종수정일</th>
							<th>비고</th>
						</tr>

						<c:forEach var="boardDto" items="${boardList }">
							<tr>
								<td>${boardDto. b_id}</td>
								<td>${boardDto. deptName}</td>
								<td>${boardDto. b_title}</td>
								<td>${boardDto. b_readAuth}</td>
								<td>${boardDto. b_writeAuth}</td>
								<td>${boardDto. b_replyAuth}</td>
								<td>${boardDto. b_downAuth}</td>
								<td>${boardDto. b_writer}</td>
								<td>${boardDto. b_regdate}</td>
								<td>
									<button class="mod" onclick="getBoardInfo('${boardDto.b_id}')">수정</button>
									&nbsp;&nbsp;
									<button class="del" onclick="ConfirmDelBoard('${boardDto. b_id}')">삭제</button>
								</td>
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
			</div>
			<!-- 메인 페이지 끝 -->
		</main>

		<!-- 모달창 시작 -->
		<div class="pop">
			<h1>
				<i class="far fa-file-alt"></i> 게시판 수정
			</h1>

			<form action="ModifyBoardProc.do" name="modForm" method="post">
				<table class="popBoardTable">
					<colgroup>
						<!--테이블 컬럼 너비 조절하는 태그-->
						<col width="12.5%" />
						<col width="12.5%" />
						<col width="12.5%" />
						<col width="12.5%" />
						<col width="12.5%" />
						<col width="12.5%" />
						<col width="12.5%" />
						<col width="12.5%" />
					</colgroup>

					<tr>
						<th>게시판 코드</th>
						<td colspan="3">
							<input type="text" name="b_id" id="mod_b_id" readonly>
						</td>
						<th>담당부서</th>
						<td colspan="3">
							<select name="dept_id" id="mod_dept_id">
								<option value="100" selected>인사</option>
								<option value="200">영업</option>
								<option value="300">마케팅</option>
								<option value="400">총무회계</option>
								<option value="500">기술지원</option>
								<option value="600">전략기획</option>
							</select>
						</td>
					</tr>

					<tr>
						<th>게시판 제목</th>
						<td colspan="3">
							<input type="text" name="b_title" id="mod_b_title">
						</td>
						<th>게시판 생성자</th>
						<td colspan="3">
							<input type="text" name="b_writer" id="mod_b_writer" readonly>
						</td>
					</tr>

					<tr>
						<th>읽기권한</th>
						<td>
							<select name="b_readAuth" id="mod_b_readAuth">
								<option value="0" selected>모두</option>
								<option value="1">직원</option>
							</select>
						</td>
						<th>쓰기권한</th>
						<td>
							<select name="b_writeAuth" id="mod_b_writeAuth">
								<option value="0" selected>모두</option>
								<option value="1">직원</option>
							</select>
						</td>
						<th>댓글권한</th>
						<td>
							<select name="b_replyAuth" id="mod_b_replyAuth">
								<option value="0" selected>모두</option>
								<option value="1">직원</option>
							</select>
						</td>
						<th>다운권한</th>
						<td>
							<select name="b_downAuth" id="mod_b_downAuth">
								<option value="0" selected>모두</option>
								<option value="1">직원</option>
							</select>
						</td>
					</tr>
				</table>
				<div class="buttons">
					<input type="button" value="게시판수정" onclick="modifyBoard()">
					<input type="button" class="closePop" value="취소">
				</div>
			</form>
		</div>

		<div id="layer"></div>
		<!-- 모달 배경 레이어 -->
		<!-- 모달창 끝 -->
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

		var delConfirm = function(msg, title, param) {
			swal({
				title : title,
				text : "<span style='font-size:25px;font-weight:600;color:#575757;'>"+msg+"</span>",
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
					//swal('', '로그아웃 하셨습니다', "success");
					location.href = "DeleteBoardProc.do?b_id=" + param;
				}
			});
		}

		function Alert(msg) {
			alert(msg, 'success');
		}
		function Confirm(msg) {
			confirm('', msg);
		}
		function DelConfirm(msg, bCode) {
			delConfirm(msg, '', bCode);
		}

		// 게시판 코드 영어만 입력 가능하게 제한
		function chkEng(e) {
			e.value = e.value.replace(/[^A-Za-z]/ig, '');
		}

		// 게시판 코드 중복 체크
		function chkBId(e) {
			var bCode = {
				b_id : e.value
			}

			$.ajax({
				type : "post",
				url : "IsBIdExist.do",
				data : JSON.stringify(bCode),
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					if (data.result > 0) {
						toastr.warning("이미 사용중인 게시판코드 입니다.");
						e.value = "";
						e.focus();
					}
				},
				error : function(data) {
					alert("시스템 에러 발생!");
				}
			});
		}

		// 게시판 등록하기
		var mbForm = document.mbForm;

		var bId = document.getElementById("b_id");
		var bTitle = document.getElementById("b_title");

		function makeBoard() {
			if (bId.value.trim().length <= 0) {
				toastr.warning("게시판 코드를 입력해 주세요");
				return false;
			} else if (bTitle.value.trim().length <= 0) {
				toastr.warning("게시판 제목을 입력해 주세요");
				return false;
			} else {
				//toastr.success("게시판이 생성 되었습니다");
				mbForm.submit();
			}
		}

		// 모달창 사라지기
		$(function() {
			$("#layer").click(function() {
				$(".pop").css("display", "none");
				$("#layer").fadeOut(100);
			});

			$(".closePop").click(function() {
				$(".pop").css("display", "none");
				$("#layer").fadeOut(100);
			});
		});

		// 모달창 띄우기 & 수정할 게시판 데이터 가져오기
		function getBoardInfo(bcode) {
			$(".mod").click(function() {
				$("#layer").fadeIn(300, function() {
					$(".pop").fadeIn(200);
				});
			});

			var bId = {
				b_id : bcode
			}

			$.ajax({
				type : "post",
				url : "GetBoardInfo.do",
				data : JSON.stringify(bId),
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					$("#mod_b_id").val(data.b_id);
					$("#mod_dept_id").val(data.dept_id);
					$("#mod_b_title").val(data.b_title);
					$("#mod_b_writer").val(data.b_writer);
					$("#mod_b_readAuth").val(data.b_readAuth);
					$("#mod_b_writeAuth").val(data.b_writeAuth);
					$("#mod_b_replyAuth").val(data.b_replyAuth);
					$("#mod_b_downAuth").val(data.b_downAuth);
				},
				error : function(data) {
					alert("시스템 에러 발생!");
				}
			});
		}

		// 게시판 정보 수정하기
		var modForm = document.modForm;
		var modBTitle = document.getElementById("mod_b_title");

		function modifyBoard() {
			if (modBTitle.value.trim().length <= 0) {
				toastr.warning("게시판 제목을 입력해 주세요");
				return false;
			} else {
				//toastr.success("게시판이 수정 되었습니다");
				modForm.submit();
			}
		}

		// 삭제할 게시판 내에 게시글 존재 유무 확인
		function ConfirmDelBoard(bcode) {
			var bId = {
				b_id : bcode
			}

			$.ajax({
				type : "post",
				url : "IsBoardDataExist.do",
				data : JSON.stringify(bId),
				contentType : "application/json; charset=utf-8",
				dataType : "json",
				success : function(data) {
					if (data.result > 0) { // 해당 게시판에 데이터가 존재하는 경우
						DelConfirm("해당 게시판에 게시글이 존재합니다.<br>게시판을 삭제하면 복구할 수 없습니다.<br>게시판을 삭제하시겠습니까?", bcode);
					} else { // 게시판에 데이터가 없는 경우
						DelConfirm("게시판을 삭제하면 복구할 수 없습니다.<br>게시판을 삭제하시겠습니까?",bcode);
					}
				},
				error : function(data) {
					alert("시스템 에러 발생!");
				}
			});
		}
	</script>
</body>

</html>