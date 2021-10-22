<%@page import="_04_ShoppingCart.model.ShoppingCart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>再次確認訂單</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  
  <link rel="stylesheet" href="<c:url value='/_04_ShoppingCart/confirmOrderPage.css'/>">
      <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
 
 <jsp:include page="/_08_Fragment/top.jsp" />
 
 
  <!-- 開始 -->
  <div class="container">
      <div class="row mt-5">
          <div class="col-sm-1"></div>
          <div class="col-sm-10 col-md-8 mx-auto">
            <i class="far fa-bell fa-2x"></i>
            <h3  class="d-inline"><b>&nbsp請再次確認訂單</b><div style="margin-top: 15px;">${ShoppingCart.companyName}・${ShoppingCart.companyTradeName}</div></h3>
            <div class="mt-3">
                <i class="fas fa-map-marker-alt fa-1x" style="color:red"></i><span>&nbsp;&nbsp;${ShoppingCart.companyAddress}</span>
            </div>
            <div class="items mt-2">
   <c:if test='${ShoppingCart.taxId!=""}'>
   <div class="row mb-2"><span>統一編號 : ${ShoppingCart.taxId}</span></div>
   </c:if>
             <c:forEach var="dd" items="${ShoppingCart.shoppingCart}">
             <Input type='hidden' id="${dd.key}"name='topping' value='${ShoppingCart.itemToppings[dd.key]}' />
                <div class="row">
                    <div class="d-flex">
                        <div class="col-1 ps-2">${dd.value.quantity}</div>
                        <div class="col-2">${dd.value.drinkBean.product_name}&nbsp;${dd.value.capacity}</div>
                        <div class="col-8">${dd.value.tempLevelBean.temp_level}&nbsp;${dd.value.sugarLevelBean.sugar_level}&nbsp;
                        
                        <c:forEach var="cc" items="${ShoppingCart.itemToppings[dd.key]}">
                       				 ${cc}
                         </c:forEach>
                        &nbsp; 
                        <c:if test='${dd.value.message!=""}'>
                        <span style="color:#F5C6AA;">傳情小貼紙：</span>${dd.value.message}&nbsp;
                        </c:if>
                        <c:if test='${dd.value.note!=""}'>
                        <span style="color:#F5C6AA;">備註：</span>${dd.value.note}&nbsp;
                        </c:if></div>
                        <div class="col-1 text-end pe-2">$${dd.value.price}</div>
                    </div>
                </div>
                
             </c:forEach>
                
                
               <c:if test="${ShoppingCart.invitationDiscount!='no'}"> 
                  <div class="row">
                    <div class="d-flex">
                        <div class="col-2"></div>
                        <div class="col-4"></div>
                        <div class="col-6 text-end pe-2">折扣&nbsp;-$50</div>
                    </div>
                </div>
                 <hr style="border: 1px solid rgb(226, 103, 20) ;">
                <div class="row">
                    <div class=" d-flex">
                        <div class="col  mb-2">總計</div>
                        <div class="col text-end fw-bold">$<fmt:formatNumber type="number" value="${ShoppingCart.cartSubTotal-50}" maxFractionDigits="0"/></div>
                    </div>
                </div>
                
                
                
                </c:if>
        <c:if test="${ShoppingCart.invitationDiscount=='no'}"> 
                <hr style="border: 1px solid rgb(226, 103, 20) ;">
                <div class="row">
                    <div class=" d-flex">
                        <div class="col  mb-2">總計</div>
                        <div class="col text-end fw-bold">$<fmt:formatNumber type="number" value="${ShoppingCart.cartSubTotal}" maxFractionDigits="0"/></div>
                    </div>
                </div>
                  </c:if>
                <div class="row d-flex">
                    <div class="d-flex ">
                        <div class="col">付款方式</div>
                        <div class="col text-end fw-bold">${ShoppingCart.payment}</div>
                    </div> 
                </div>

            </div>
            <div class="mt-4 d-flex justify-content-between">
              <button type="button" id="cancle" style="width: 200px;" class="btn btn-dark my-2">取消</button>
              <button type="button" id="submit" style="width: 200px;" class="btn btn-dark my-2">確認訂單</button>
            </div>
          </div>
          <div class="col-sm-1"></div>
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

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>

    
  <script type="text/javascript">

	//複製邀請碼右方彈跳文字
	var popoverTriggerList = [].slice.call(
		document.querySelectorAll('[data-bs-toggle="popover"]')
	);
	var popoverList = popoverTriggerList.map(function(popoverTriggerEl) {
		return new bootstrap.Popover(popoverTriggerEl);
	});



  
  $('#submit').click(function(){
	  window.location.assign("http://localhost:8080/whattodrink/_05_Order/ConfirmOrderServlet");
	  
	  
	  })
	  
 $('#cancle').click(function(){
	  window.location.assign("http://localhost:8080/whattodrink/_00_Index/index.jsp");
	  
	  
	  })
	  

  </script>
  
  
</body>

</html>