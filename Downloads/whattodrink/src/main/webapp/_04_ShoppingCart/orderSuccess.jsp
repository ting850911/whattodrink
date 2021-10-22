<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>訂購成功</title>
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
    <script
      src="https://kit.fontawesome.com/0d9d19539c.js"
      crossorigin="anonymous"
    ></script>

   <link rel="stylesheet" href="<c:url value='/_04_ShoppingCart/orderSuccess1.css'/>">
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
  </head>

  <body>
  
 <jsp:include page="/_08_Fragment/top.jsp" />

    <!-- 開始 -->
    <div class="container-fluid" style="min-height: 70vh;">
      <div class="row">
        <!-- start -->
        <div class="col-sm-10 mx-auto d-flex flex-wrap justify-content-center">
          <!-- Start -->
          <!-- title -->
           <div class="row" style="margin-top: 30px;display: flex">
              <h4><i class="fas fa-check"></i> 感謝您的訂購</h4>
            </div>

         <div class="row" style="margin-top: 10px;text-align: center;" >
            <!-- have a tag -->
            <h6>
              已將您的訂單提交給店家，請隨時注意您的<a href="<c:url value="/_05_Order/c_05_order/myOrder.jsp"/>">訂單狀態</a
              >，以確認店家是否接單。
             </h6>
            <!-- have a tag -->

            <!-- SPAN shopName -->
            <h6>
              以下是你在<span class="fw-bold shopName"
                >${orderBean.companyBean.company_name}・${orderBean.companyBean.trade_name}</span
              >
              訂購的電子明細。
            </h6>
            <!-- SPAN shopName -->
          </div>
          <!-- title -->
          <!-- BODY -->
          <div
            style="
             background-color: #ffffff;
              border: black 2px solid;
              padding: 20px;
              margin: 20px;
              max-width: 450px;
            "
          >
            <div class="row mb-3 orderDetail">
              <div class="d-flex" style="justify-content: space-between">
                <!-- SPAN time -->
                <span class="time" style="color: gray; font-size: 0.5rem"
                  >${date}<br>訂單編號${orderBean.order_id}</span
                >
               <c:if test='${orderBean.taxId!=""}'>
               <span class="taxId" style="color: gray; font-size: 0.5rem"
                  >統一編號${orderBean.taxId}</span
                >
                </c:if>
                
                
                
                <!-- SPAN time -->
              </div>
            </div>
            <div class="row mb-2">
              <div>
                <!-- SPAN shopName -->
                 <span class="fw-bold shopName" style="width: 400px;margin-bottom: 10px;">${orderBean.companyBean.company_name}・${orderBean.companyBean.trade_name}</span
                >
                <!-- SPAN shopName -->
              </div>
           <c:forEach var="dd" items="${ShoppingCart.shoppingCart}" varStatus="str">
              <!-- 以下是訂購的商品數量與商品名稱與價錢 ALL SPAN TAG-->
              <div
                class="d-flex justify-content-between"
                style="align-items: center"
              >
                <span class="productNumber" style="width: 35px">${dd.value.quantity}</span>
                <span class="productName" style="width: 300px;font-size: 12px"
                  >${dd.value.drinkBean.product_name}&nbsp; ${dd.value.capacity}&nbsp;${dd.value.tempLevelBean.temp_level}&nbsp;${dd.value.sugarLevelBean.sugar_level}&nbsp;

					<c:forEach var="cc" items="${ShoppingCart.itemToppings[dd.key]}">
                       				 ${cc}
                         </c:forEach>
                        &nbsp; 
                        <c:if test='${dd.value.message!=""}'>
                        <span style="color:#F5C6AA;">傳情小貼紙：</span>${dd.value.message}&nbsp;
                        </c:if>
                        <c:if test='${dd.value.note!=""}'>
                        <span style="color:#F5C6AA;">備註：</span>${dd.value.note}&nbsp;
                        </c:if></span>
                <span class="productPricetotal" style="width: 35px">$${dd.value.price}</span>
              </div>
              <!-- 以上是訂購的商品數量與商品名稱與價錢 ALL SPAN TAG-->
           </c:forEach>
           
            </div>
            
            
            <div class="row">
              <hr style="background-color: black" />
              <c:if test="${orderBean.invitationDiscount ne '無折扣'}">
               <div
                style="display: flex; justify-content: space-between"
              >
                <p class="fw-bold">折扣</p>
                <!-- SPAN allTotal -->
                <span style="margin: 0px">-50</span>
                <!-- SPAN allTotal -->
              </div>
              
              </c:if>
             
              
              <div
                class="total"
                style="display: flex; justify-content: space-between"
              >
                <p class="fw-bold">總計</p>
                <!-- SPAN allTotal -->
                <span class="fw-bold allTotal" style="margin: 0px">$<fmt:formatNumber type="number" value="${orderBean.order_total}" maxFractionDigits="0"/></span>
                <!-- SPAN allTotal -->
              </div>
              <div
                class="payMethod"
                style="display: flex; justify-content: space-between"
              >
                <p class="fw-bold">付款方式</p>
                <!-- SPAN PayMethod -->
                <span
                  class="d-flex fw-bold PayMethod"
                  style="width:auto"
                  >${orderBean.payment}
                </span>
                <!-- SPAN PayMethod -->
              </div>
            </div>
          </div>
          <!-- BODY -->
          <!-- End -->
        </div>
        <!-- End -->
      </div>
    </div>
<c:remove var="ShoppingCart" scope="session" />
    <!-- 結束 -->

    

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
  

    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    
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
