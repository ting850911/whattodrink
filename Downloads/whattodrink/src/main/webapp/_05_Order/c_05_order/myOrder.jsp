<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>我的訂單</title>
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

    <link rel="stylesheet" href="<c:url value="/_05_Order/c_05_order/myOrder.css" />" />
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <style>
      .text-secondary {
        margin: unset;
      }
      .no_margin {
        margin: unset;
      }
      h5 {
        overflow: hidden;
        white-space: nowrap;
        text-overflow: ellipsis;
      }
      .my-btn-width {
        width: 200px;
      }
      /* @media screen and (min-width: 576px) {
        .my-btn-width {
          width: 57%;
        }
      } */
      @media screen and (min-width: 768px) {
        .my-btn-width {
          width: 100%;
        }
      }
    </style>
  </head>

  <body>
  
   <jsp:include page="/_08_Fragment/top.jsp" />
  
    <!-- content start -->
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-10 mx-auto">
          <!-- waiting order -->
          <h4 class="mb-4 mt-5 d-none" id="newOrd">目前的訂單</h4>
          <!-- past order -->
          <h4 class="mb-4 mt-5" id="oldOrd">過去的訂單</h4>
        </div>
      </div>
    </div>
    <!-- content end -->

    <!-- details Modal -->
    <div
      class="modal fade"
      data-bs-backdrop="static"
      data-bs-keyboard="false"
      id="details"
      tabindex="-1"
    >
      <div class="modal-dialog modal-lg modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">訂單明細</h5>
            <button
              type="button"
              class="btn-close"
              id="detailsClose"
              data-bs-dismiss="modal"
            ></button>
          </div>
          <div class="modal-body px-4">
            <span
              class="text-secondary"
              style="font-size: small"
              id="ordTime"
            ></span>
            <p class="fw-bold no_margin">感謝您的訂購</p>
            <table class="table">
              <thead style="font-weight: unset">
                <tr>
                  <th>杯數</th>
                  <th>名稱</th>
                  <th>容量</th>
                  <th>甜度</th>
                  <th>溫度</th>
                  <th>小貼紙</th>
                  <th>備註</th>
                </tr>
              </thead>
              <tbody id="details2"></tbody>
            </table>
            <div
              class="fw-bold d-flex mb-2"
              style="justify-content: space-between"
            >
              <span>總計</span>
              <span id="total">&dollar;</span>
            </div>
            <div
              class="fw-bold d-flex mb-2"
              style="justify-content: space-between"
            >
              <span>付款方式</span>
              <span id="payment">信用卡</span>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- number Modal -->
    <div
      class="modal fade"
      data-bs-backdrop="static"
      data-bs-keyboard="false"
      id="number"
      tabindex="-1"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">號碼牌</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              id="numClose"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <h4 class="d-flex justify-content-center" id="showNum">
              取餐時請出示號碼牌
            </h4>
          </div>
        </div>
      </div>
    </div>

    <!-- review Modal -->
    <div
      class="modal fade"
      data-bs-backdrop="static"
      data-bs-keyboard="false"
      id="review"
      tabindex="-1"
    >
      <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">過去的評價</h5>
            <button
              type="button"
              class="btn-close"
              id="reviewClose"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <div class="row">
              <div class="col-sm-10 mx-auto" id="review2"></div>
            </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Modal(sharecode) start-->
    <div
    class="modal fade"
    id="sharecode"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
    >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">
            分享你的邀請碼給好友吧
          </h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div
          class="modal-body d-flex justify-content-center"
          style="padding-top: 30px"
        >
          <i class="fas fa-gift fa-lg" id="Invitationcode"
            >&nbsp;&nbsp;&nbsp;&nbsp;${CLoginOK.invitation}&nbsp;</i
          >
          <button
            type="button"
            class="btn"
            style="position: relative; bottom: 9px"
            data-bs-container="body"
            data-bs-toggle="popover"
            data-bs-placement="right"
            data-bs-content="已複製"
          >
            <i class="far fa-copy fa-lg" onclick="copy()"></i>
          </button>
        </div>
        <div class="modal-footer">
          <button
            type="button"
            class="btn"
            data-bs-dismiss="modal"
            aria-label="Close"
          >
            完成
          </button>
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
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script src="<c:url value="/_05_Order/c_05_order/myOrder.js" />"></script>

     
     
      <script>
      $.ajax({
        url: "http://localhost:8080/whattodrink/C_myOrderDelete",
        type: "GET",
        dataType: "JSON",
        async:false,
        success: function (result) {
        	console.log(result);
      
          var timoutId = setInterval(function () {
        	 
        	  
            if (result.orderStatus == "取消") {
             	   alert(`訂單編號：\${result.orderId}，因為店家考量已經取消，請重新訂購`);

              console.log(timoutId);
              clearInterval(timoutId);
              // window.location.assign("");
            }
          }, 1000);
        },
      });
      
      
      
      
      
      
      
      //複製號碼牌
      function copy() {
        var content = "取餐時間" + $("#t").text() + "取餐編號" + $("#n").text();
        navigator.clipboard
          .writeText(content)
          .then(() => {
            console.log("Text copied to clipboard...");
          })
          .catch((err) => {
            console.log("Something went wrong", err);
          });
      }
    </script>
  </body>
</html>
