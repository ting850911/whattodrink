<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>我的帳戶</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<link rel="stylesheet" type="text/css"
	href="<c:url value='/_07_Others/c__07_others_acount/myAccountCoupon.css'/> ">
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.js"></script>
</head>

<body>

	<jsp:include page="/_08_Fragment/top.jsp" />

	<!-- 內容開始 -->

	<div class="container-fluid" style="min-height: 70vh;">

		<div class="row">
			<div class="col-sm-1"></div>
			<!-- 側選欄start -->
			<div class="col-sm-2 side1 mx-auto">
				<a
					href="<c:url value="/_07_Others/c__07_others_acount/myAccount.jsp"/>">
					<h5>帳戶設定</h5>
				</a> <a
					href="<c:url value="/InvitationEnteringServlet"/>">
					<h5>優惠券</h5>
				</a>
			</div>
			<!-- 側選欄end -->

			<!-- 本體start -->


			<div class="col-sm-6 mx-auto d-flex justify-content-center">
				<div class="box d-flex flex-column">
					<div style="margin-top: 25px; text-align: center;">
						<c:choose>
							<c:when test="${CLoginOK.invited_by==null}">
								<form method="post"
									action="<c:url value="/InvitationEnteringServlet"/>"
									id="acountform">
									<h5 style="margin-right: 10px;">請輸入好友邀請碼</h5>
									<input type="text" name="invitationCode" >
									<button type="submit" style="width: 200px;"
										class="btn btn-dark my-2" id="save">提交邀請碼</button>
								</form>
							</c:when>

							<c:otherwise>
								<h5 style="margin-right: 10px;">已使用好友邀請碼</h5>
								<input type="text" name="Invitationcode" 
									value="${CLoginOK.invited_by}" disabled="disabled">
							</c:otherwise>
						</c:choose>


						<h6 style="margin: 10px">${invitationCode}</h6>
						<c:remove var="invitationCode" scope="session" />
					</div>

					<c:if test="${CLoginOK.invitationCount>0}">
						<c:forEach begin="1" end="${CLoginOK.invitationCount}" >
							<div class="bb" id="bb">
								<div style="text-align: center;">
									<h5>邀請碼折扣</h5>
									<p>50元</p>
								</div>
							</div>
						</c:forEach>

					</c:if>




				</div>
			</div>
			<!-- 本體end -->
			<div class="col-sm-3"></div>
		</div>

	</div>

	<!-- 內容結束 -->






	<!-- footer end -->
	<footer id="footer">
		<div class="container-fluid">
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="footer-content logo col-sm-5 m-0">
					<a href=""><img src="<c:url value="/images/logo.png"/>"
						alt="logo" class="small-logo" /></a>
					<div class="app_download">
						<a href="" class="text-dark text-decoration-none"><span
							class="border border-1 rounded-1 p-1 border-dark"><i
								class="fab fa-google-play me-1"></i>應用程式</span></a>
					</div>
				</div>
				<div class="col-sm-1"></div>
				<div class="footer-content col-sm-2">
					<a href="" class="link-dark text-decoration-none">關於我們</a> <a
						href="<c:url value="/_01_Register/b_01_register/1_business_register_1.jsp"/>"
						class="link-dark text-decoration-none">建立企業帳戶</a> <a
						href="<c:url value="/_02_Login/b_02_login/1_business_login.jsp"/>"
						class="link-dark text-decoration-none">登入您的商店</a>
				</div>
				<div class="footer-content col-sm-2">
					<a href="mailto:whattodrink2021@whattodrink.com"
						class="link-dark text-decoration-none">取得協助</a> <a href=""
						class="link-dark text-decoration-none">附近的商家</a> <a href=""
						class="link-dark text-decoration-none">切換語言</a>
				</div>
				<div class="col-sm-1"></div>
			</div>
			<div class="row d-flex justify-content-center">
				<div class="footer-content col-sm-10">
					<hr />
				</div>
			</div>
			<div class="row">
				<div class="col-sm-1"></div>
				<div class="footer-content icons col-sm-5">
					<i class="fab fa-facebook"></i> <i class="fab fa-instagram"></i> <i
						class="fab fa-twitter"></i>
				</div>
				<div
					class="
              footer-content
              col-sm-5
              d-flex
              flex-direction-row
              align-items-end
            ">
					<span>&copy;2021 今天喝什麼 Inc.</span>
				</div>
				<div class="col-sm-1"></div>
			</div>
		</div>
	</footer>
	<!-- footer end -->


	<!-- Modal(sharecode) start-->
	<div class="modal fade" id="sharecode" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">分享你的邀請碼給好友吧</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body d-flex justify-content-center"
					style="padding-top: 30px;">
					<i class="fas fa-gift fa-lg" id="Invitationcode">&nbsp;&nbsp;&nbsp;&nbsp;${CLoginOK.invitation}&nbsp;</i>
					<button type="button" class="btn"
						style="position: relative; bottom: 9px;" data-bs-container="body"
						data-bs-toggle="popover" data-bs-placement="right"
						data-bs-content="已複製">
						<i class="far fa-copy fa-lg" onclick="copy()"></i>
					</button>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn" data-bs-dismiss="modal"
						aria-label="Close">完成</button>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal(sharecode) end-->

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>

	<script type="text/javascript">
	
	//複製邀請碼右方彈跳文字
	var popoverTriggerList = [].slice.call(
	  document.querySelectorAll('[data-bs-toggle="popover"]')
	);
	var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
	  return new bootstrap.Popover(popoverTriggerEl);
	});

	</script>

</body>

</html>