<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>付款</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />

    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />

    <link rel="stylesheet" href="<c:url value="/_04_ShoppingCart/payment.css"/>" />
    <style>
      /* Chrome, Safari, Edge, Opera */
      input[type=number]::-webkit-inner-spin-button, 
      input[type=number]::-webkit-outer-spin-button { 
        -webkit-appearance: none; 
        margin: 0; 
      }

      /* Firefox */
      input[type=number] {
        -moz-appearance: textfield;
      }
    </style>
  </head>
  <body>
    <c:if test="${CLoginOK!=null}"></c:if>
 <!-- hamburger menu start-->
	<div class="offcanvas offcanvas-start d-flex flex-column"
		id="hamburgerMenu" style="width: 300px; padding: 10px" tabindex="-1">
		<div class="offcanvas-header">
			<button type="button" class="btn btn-close text-reset"
				data-bs-dismiss="offcanvas"></button>
		</div>
		<div class="offcanvas-body lh-base ">
			<div class="d-block atext" style="height: 70vh">
				<div>
					<i class="fas fa-user-circle mb-4" style="font-size: xxx-large"></i>
				</div>
				<h6 class="fw-normal">
					<a class="link-dark text-decoration-none"
						href="<c:url value="/_07_Others/c__07_others_acount/myAccount.jsp"/>"><i
						class="fas fa-user"></i>&nbsp;&nbsp;&nbsp;帳戶資訊</a>
				</h6>
				<h6 class="fw-normal">
					<a class="link-dark text-decoration-none"
						href="<c:url value="/_05_Order/c_05_order/myOrder.jsp"/>"><i
						class="fas fa-list-ul"></i>&nbsp;&nbsp;&nbsp;我的訂單</a>
				</h6>
				<h6 class="fw-normal">
					<a class="link-dark text-decoration-none"
						href="<c:url value="/_07_Others/c__07_others_favorite/favorite.jsp"/>"><i
						class="fas fa-heart"></i>&nbsp;&nbsp;&nbsp;我的最愛</a>
				</h6>
				<h6 class="fw-normal">
					<a class="link-dark text-decoration-none" href="#sharecode"
						data-bs-toggle="modal"><i class="fas fa-gift"></i>&nbsp;&nbsp;&nbsp;分享邀請碼</a>
				</h6>
				<h6 class="fw-normal">
					<a class="link-dark text-decoration-none"
						href="<c:url value="/_07_Others/c__07_others_healthReminder/healthReminder.jsp"/>"><i
						class="far fa-bell"></i>&nbsp;&nbsp;&nbsp;健康提醒</a>
				</h6>

				<h6 class="fw-normal">
					<a class="link-secondary text-decoration-none" href="http://localhost:8080/whattodrink/C_LogoutServlet">登出</a>
				</h6>
			</div>
			<div class="d-flex flex-column">
				<a href="<c:url value='/_00_Index/index.jsp'/>" class="logo"><img
					src="<c:url value="/images/logo.png"/>" alt="logo"
					class="small-logo" /></a> <a href=""
					class="text-dark text-decoration-none app_download"><span
					class="border border-1 rounded-1 p-1 border-dark"
					style="width: fit-content"><i
						class="fab fa-google-play me-1"></i>應用程式</span></a>
			</div>
		</div>
	</div>
	<!-- hamburger menu end-->

    <!-- Menu Start -->
    <nav class="navbar navbar-expand-sm navbar-light d-flex flex-column" style="margin-bottom: 50px;">
      <div class="container-fluid">
           <div class="col-sm-1"></div>
        <a class="navbar-brand" data-bs-toggle="offcanvas" href="#hamburgerMenu"
          ><span id="openbar" class="navbar-toggler-icon"></span
        ></a>
        <!-- lift menu -->
        <ul class="navbar-nav me-md-auto">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/_00_Index/index.jsp"/>"
              ><img id="logo" src="<c:url value="/images/logo.png"/>" alt=""
            /></a>
          </li>
        </ul>
        <ul class="navbar-nav mx-md-auto flex-row hide show7">
          <li class="nav-item show7-1">
            <a class="nav-link" href="<c:url value="/_04_ShoppingCart/shoppingcart.jsp"/>"
              ><i class="fas fa-shopping-cart fa-lg"></i
            ></a>
          </li>
        </ul>
        <!-- right menu -->
        <ul class="navbar-nav ms-ms-auto ms-md-auto hide7" id="menuright">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/_04_ShoppingCart/shoppingcart.jsp"/>"
              ><i class="fas fa-shopping-cart fa-lg"></i
            ></a>
          </li>
        </ul>
             <div class="col-sm-1"></div>
      </div>
    </nav>
    <!-- Menu End -->

    <!-- content start -->
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-1"></div>
          <div class="col-sm-6 my-5">
            <div class="bg-white overflow-auto p-5" 
            style="height: 420px;border-radius: 10px;box-shadow: 3px 3px 5px 6px #cccccc6b;">
              <h4 class="mb-3">付款方式</h4>
                <div class="form-check mb-2">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="payment"
                    id="creditcard"
                    value="信用卡"
                    checked
                    data-bs-toggle="modal"
                    data-bs-target="#visa"
                  />
                  <label class="form-check-label" for="creditcard">信用卡</label>
                </div>
                <div class="form-check mb-2">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="payment"
                    id="incash"
                    value="現金"
                  />
                  <label class="form-check-label" for="incash"
                    >現金</label
                  ><span class="text-danger ms-2" style="font-size: 14px;" id="storeTel">此選項需與店家聯繫</span>
                </div>
                <hr class="my-4" style="background-color: black" />
                <!-- 發票 -->
                <h4 class="mb-3">是否需要統編</h4>
                <div class="form-check mb-2">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="invoice"
                    id="invoice"
                    checked
                  />
                  <label class="form-check-label" for="invoice">紙本發票</label>
                </div>
                <div class="mb-2" style="padding: 0px 0px 0px 24px">
                  <p class="mb-1">統一編號</p>
                  <input
                    class="form-control form-control-sm"
                    style="width: min-content"
                    type="number"
                    oninput="if(value.length>8)value=value.slice(0,8)"
                    placeholder="請輸入8碼"
                    id="taxId"
                  />
                </div>
                <!-- 邀請碼優惠 -->
                <form  id="discount" class="d-none">
                  <hr class="my-4" style="background-color: black" />
                  <h4>邀請碼優惠</h4>
                  <span class="fs-6 text-danger">單筆訂單滿百才可使用</span>
                  <div class="form-check mt-3 mb-2">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="invitationDiscount"
                      value="no"
                      id="unused"
                      checked
                    />
                    <label class="form-check-label" for="unused">不使用</label>
                  </div>
                  <div class="form-check mb-2">
                    <input
                      class="form-check-input"
                      type="radio"
                      name="invitationDiscount"
                      value="yes"
                      id="50discount"
                    />
                    <label class="form-check-label" for="50discount"
                      >使用折抵50元</label
                    >
                  </div>
                  
                </form>
            </div>
          </div>  
          <div class="col-sm-4 my-5">
            <div class="bg-white overflow-auto d-flex flex-column justify-content-center" 
            style="height: 420px;border-radius: 10px;box-shadow: 3px 3px 5px 6px #cccccc6b;">
              <div class="mx-auto d-none" id="counting">
                <span class="fs-5"
                  >小計&nbsp;&dollar;<span class="mx-1" id="sub_total"></span></span>
                </br>
                <span class="fs-5"
                  >折扣&nbsp;&dollar;<span class="mx-1">50</span></span>
              </div>
              <hr class="my-4 mx-auto w-50 d-none" style="background-color: black" />
              <!-- total -->
              <div class="mx-auto mb-4">
                <span class="fw-bold fs-4">總計&nbsp;&dollar;<span class="mx-1" id="total"></span></span>
              </div>
              <button
                type="submit"
                class="btn btn-dark mx-auto"
                style="width: 200px"
              >
                下訂單
              </button>
            </div>
          </div>
        <div class="col-sm-1"></div>
      </div>
    </div>
    <!-- content end -->

    <!-- footer end -->
    <footer id="footer">
      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-1"></div>
          <div class="footer-content logo col-sm-5 m-0">
            <a href=""
              ><img src="<c:url value="/images/logo.png"/>" alt="logo" class="small-logo"
            /></a>
             <div class="app_download">
              <a href="" class="text-dark text-decoration-none"
                ><span class="border border-1 rounded-1 p-1 border-dark"
                  ><i class="fab fa-google-play me-1"></i>應用程式</span
                ></a
              >
            </div>
          </div>
          <div class="col-sm-1"></div>
          <div class="footer-content col-sm-2">
            <a href="" class="link-dark text-decoration-none">關於我們</a>
            <a
              href="<c:url value="/_01_Register/b_01_register/1_business_register_1.jsp"/>"
              class="link-dark text-decoration-none"
              >建立企業帳戶</a
            >
            <a
              href="<c:url value="/_02_Login/b_02_login/1_business_login.jsp"/>"
              class="link-dark text-decoration-none"
              >登入您的商店</a
            >
          </div>
          <div class="footer-content col-sm-2">
            <a href="mailto:whattodrink2021@whattodrink.com" class="link-dark text-decoration-none">取得協助</a>
            <a href="" class="link-dark text-decoration-none">附近的商家</a>
            <a href="" class="link-dark text-decoration-none">切換語言</a>
          </div>
          <div class="col-sm-1"></div>
        </div>
        <div class="row d-flex justify-content-center">
          <div class="footer-content col-sm-10"><hr /></div>
        </div>
        <div class="row">
          <div class="col-sm-1"></div>
          <div class="footer-content icons col-sm-5">
            <i class="fab fa-facebook"></i>
            <i class="fab fa-instagram"></i>
            <i class="fab fa-twitter"></i>
          </div>
          <div
            class="
              footer-content
              col-sm-5
              d-flex
              flex-direction-row
              align-items-end
            "
          >
            <span>&copy;2021 今天喝什麼 Inc.</span>
          </div>
          <div class="col-sm-1"></div>
        </div>
      </div>
    </footer>
    <!-- footer end -->

 <!-- Modal(sharecode) start-->
   <div class="modal fade" id="sharecode" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">分享你的邀請碼給好友吧</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body d-flex justify-content-center" style="padding-top: 30px;">
          <i class="fas fa-gift fa-lg"
            id="Invitationcode">&nbsp;&nbsp;&nbsp;&nbsp;${CLoginOK.invitation}&nbsp;</i>
          <button type="button" class="btn" style="position: relative;bottom: 9px;" data-bs-container="body" data-bs-toggle="popover" data-bs-placement="right" data-bs-content="已複製">
            <i class="far fa-copy fa-lg" onclick="copy()"></i>
          </button>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close">完成</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal(sharecode) end-->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="<c:url value="/_04_ShoppingCart/payment.js"/>"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
