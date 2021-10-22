<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>健康提醒</title>
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

    <link rel="stylesheet" href="<c:url value="/_07_Others/c__07_others_healthReminder/healthReminder.css"/>" />
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </head>
  <style>
    /* remove number input spinner */
    /* Chrome, Safari, Edge, Opera */
    input[type="number"]::-webkit-inner-spin-button,
    input[type="number"]::-webkit-outer-spin-button {
      -webkit-appearance: none;
      margin: 0;
    }

    /* Firefox */
    input[type="number"] {
      -moz-appearance: textfield;
    }
  </style>
  <body>

 <jsp:include page="/_08_Fragment/top.jsp" />

    <!-- 開始 -->
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-5">
          <h4 class="mb-4 mt-5">健康提醒</h4>
          <p>歡迎使用健康提醒功能</p>
          <form id="weight_value">
            <span
              >請輸入您的體重<input
                class="mx-2"
                style="
                  display: unset;
                  height: 20px;
                  width: 65px;
                  border: unset;
                  border-bottom: black 1px solid;
                "
                type="number"
                step="0.1"
                min="30"
                max="150"
                id="weight"
                value="${CLoginOK.weight}" 
              />kg</span
            >
          </form>
          <p class="mt-3">此功能將自動換算為所需運動量</p>
          <hr style="background-color: black; width: 80%" />
          <h5 class="my-3">
            今日攝取熱量<span
              class="mx-1 fw-bold text-success"
              style="text-decoration: underline black"
              id="cal"
            ></span
            >大卡
          </h5>
          <div id="content"></div>
        </div>
        <!-- 熱量消耗 -->
        <div class="col-sm-5">
          <h4 class="mb-4 mt-5">熱量消耗</h4>
          <div class="row">
            <div class="col-sm-12 d-flex flex-direction-row">
              <div class="d-flex me-5" style="max-width: 150px">
                <figure class="figure">
                  <img
                    class="ratio ratio-4x3"
                    src="<c:url value="/images/running.jpeg"/>"
                  />
                </figure>
              </div>
              <div>
                <span>跑步：</span
                ><span
                  class="mx-1"
                  style="text-decoration: underline"
                  id="run_time"
                ></span
                ><span>分鐘</span>
                <hr />
                <span>消耗：</span
                ><span
                  class="mx-1"
                  style="text-decoration: underline"
                  id="run_cal"
                ></span
                ><span>大卡</span>
              </div>
            </div>
          </div>
          <div class="row my-2">
            <div class="col-sm-12 d-flex flex-direction-row">
              <div class="d-flex me-5" style="max-width: 150px">
                <figure class="figure">
                  <img
                    class="ratio ratio-4x3"
                    src="<c:url value="/images/fitness.jpeg"/>"
                  />
                </figure>
              </div>
              <div>
                <span>腳踏車：</span
                ><span
                  class="mx-1"
                  style="text-decoration: underline"
                  id="bike_time"
                ></span
                ><span>分鐘</span>
                <hr />
                <span>消耗：</span
                ><span
                  class="mx-1"
                  style="text-decoration: underline"
                  id="bike_cal"
                ></span
                ><span>大卡</span>
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-sm-12 d-flex flex-direction-row">
              <div class="d-flex me-5" style="max-width: 150px">
                <figure class="figure">
                  <img
                    class="ratio ratio-4x3"
                    src="<c:url value="/images/cyclists.jpeg"/>"
                  />
                </figure>
              </div>
              <div>
                <span>快走：</span
                ><span
                  class="mx-1"
                  style="text-decoration: underline"
                  id="walk_time"
                ></span
                ><span>分鐘</span>
                <hr />
                <span>消耗：</span
                ><span
                  class="mx-1"
                  style="text-decoration: underline"
                  id="walk_cal"
                ></span
                ><span>大卡</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <!-- 結束 -->

   
   
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

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script src="<c:url value="/_07_Others/c__07_others_healthReminder/healthReminder11.js"/>"></script>
   <script type="text/javascript">
 //複製邀請碼右方彈跳文字
	var popoverTriggerList = [].slice.call(
		document.querySelectorAll('[data-bs-toggle="popover"]')
	);
	var popoverList = popoverTriggerList.map(function(popoverTriggerEl) {
		return new bootstrap.Popover(popoverTriggerEl);
	});

   </script>
   
  </body>
</html>
