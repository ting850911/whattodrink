<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title></title>
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
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css">
    <link rel="stylesheet" href="<c:url value='/_04_ShoppingCart/shoppingcart1.css'/>" />
  </head>

  <body>
<c:if test="${CLoginOK==null}">

<!-- hamburger menu1 start-->
  <div
  class="offcanvas offcanvas-start d-flex flex-column"
  id="hamburgerMenu"
  style="width: 300px; padding: 10px"
  tabindex="-1"
>
  <div class="offcanvas-header">
    <button
      type="button"
      class="btn btn-close text-reset"
      data-bs-dismiss="offcanvas"
    ></button>
  </div>
  <div class="offcanvas-body lh-base">
    <div class="d-block" style="height: 70vh">
      <h6 class="fw-normal">
          <a href="<c:url value="/_01_Register/c_01_register/LoginRegister.jsp"/>"><button type="submit" style="width: 200px;" class="btn btn-dark my-2">登入</button></a>
      </h6>
      <h6 class="fw-normal">
        <a class="link-dark text-decoration-none" href="<c:url value="/_01_Register/b_01_register/1_business_register_1.jsp"/>">建立企業帳戶</a>
      </h6>
      <h6 class="fw-normal">
        <a class="link-dark text-decoration-none" href="<c:url value="/_02_Login/b_02_login/1_business_login.jsp"/>">登入您的商店</a>
      </h6>

    </div>
    <div class="d-flex flex-column">
      <a href="" class="logo"><img src="<c:url value="/images/logo.png"/>" alt="logo" class="small-logo"/></a>
      <a href="" class="text-dark text-decoration-none app_download"
        ><span
          class="border border-1 rounded-1 p-1 border-dark"
          style="width: fit-content"
          ><i class="fab fa-google-play me-1"></i>應用程式</span
        ></a
      >
    </div>
  </div>
</div>
  <!-- hamburger menu1 end-->



</c:if>



<!-- hamburger menu start-->
<c:if test="${CLoginOK!=null}">

<div class="offcanvas offcanvas-start d-flex flex-column"
	id="hamburgerMenu" style="width: 300px; padding: 10px" tabindex="-1">
	<div class="offcanvas-header">
		<button type="button" class="btn btn-close text-reset"
			data-bs-dismiss="offcanvas"></button>
	</div>
	<div class="offcanvas-body lh-base ">
		<div class="d-block atext" style="height: 70vh">
			<div style="display: flex;">
				<i class="fas fa-user-circle mb-4" style="font-size: xxx-large"></i>
				<h6 style="margin-top: 15px; margin-left: 15px;">${CLoginOK.customer_name}
					今天喝什麼?</h6>
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
					href="<c:url value="//ShowMyFavoriteServlet"/>"><i
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
				<a class="link-secondary text-decoration-none" href="<c:url value="/C_LogoutServlet"/>">登出</a>
			</h6>
		</div>
		<div class="d-flex flex-column">
			<a href="<c:url value='/_00_Index/index.jsp'/>" class="logo"><img
				src="<c:url value="/images/logo.png"/>" alt="logo"
				class="small-logo" /></a> <a href=""
				class="text-dark text-decoration-none app_download"><span
				class="border border-1 rounded-1 p-1 border-dark"
				style="width: fit-content"><i class="fab fa-google-play me-1"></i>應用程式</span></a>
		</div>
	</div>
</div>
<!-- hamburger menu end-->
</c:if>

    <!-- Menu Start -->
    <nav class="navbar navbar-expand-sm navbar-light d-flex flex-column" style="margin-bottom: 10px;">
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
     
             <div class="col-sm-1"></div>
      </div>
    </nav>
    <!-- Menu End -->

    <!-- 開始 -->
    <div class="container-fluid" style="min-height: 70vh;">
      <div class="row">
        <div class="col-sm-1"></div>
        <div class="col-sm-6">
          <div class="d-flex flex-column left1">
            <h3 id="companyinfo"></h3>
            <div class="mb-1">
              <div id="map">
              </div>
           
            </div>
            <h6 style="margin-top: 15px" id="address">
            </h6>
            <hr class="aa" />
            <div class="row">
              <h3 class="mb-4">選擇自取時間</h3>
              <form>
                <input type="radio" name="time" value="now" checked/><span>現在(約15-30min)</span><br>
                <input
                  type="radio"
                  name="time"
                  value="selectTime"
                /><span id="selectTime">安排時間</span> 
              </form>
            </div>

            <hr class="aa" />
            <div class="row mb-4" >
              <div class="col-6 d-flex align-items-end">
                <h3 style="margin-bottom: 3px">您的飲品</h3>
              </div>
              <div class="col-6 d-flex justify-content-end">
                <button class="btn" id="gobackshop">
                  <i class="fas fa-plus">&emsp;繼續購物</i>
                </button>
              </div>
            </div>

<c:forEach var="cart" items="${ShoppingCart.shoppingCart}">
<Input type='hidden' name='company_name' id="company_name" value='${cart.value.drinkBean.companyBean.company_name}' />
<Input type='hidden' name='company_id' id="company_id" value='${cart.value.drinkBean.companyBean.company_id}' />
<Input type='hidden' name='trade_name' id="trade_name" value='${cart.value.drinkBean.companyBean.trade_name}' />
<Input type='hidden' name='company_address' id="company_address" value='${cart.value.drinkBean.companyBean.company_address}' />
<Input type='hidden' name='company_address' id="company_start_time" value='${fn:substring(cart.value.drinkBean.companyBean.start_time,0,5)}' />
<Input type='hidden' name='company_address' id="company_end_time" value='${fn:substring(cart.value.drinkBean.companyBean.end_time,0,5)}' />
            <div class="item" id="${cart.key}item">
          
              <div class="row">
                <div class="col-2">
                <Input type='hidden' name='quantity' id="${cart.key}quantity" value='${cart.value.quantity}' />
                <select
                    class="form-select item-select"
                    name="count"
                    style="width: 80px; height: 35px"
                    id="${cart.key}quantityoption"
                  >
                  <c:forEach varStatus="stVar"  begin="0" end="100">
                  <option value="${stVar.index}" ${cart.value.quantity==stVar.index?'selected':''}>${stVar.index}</option>
                  
                  
                  </c:forEach>
                  
                  
                  </select>
                </div>
                <div class="col-7 d-flex align-items-end">
                  <Input type='hidden' name='itemkey' id='${cart.key}' value='${cart.key}' />
                   <Input type='hidden' name='sugar_ID' id='${cart.key}sugar_ID' value='${cart.value.sugarLevelBean.sugar_id}' />
                    <Input type='hidden' name='temp_ID' id='${cart.key}temp_ID' value='${cart.value.tempLevelBean.temp_id}' />
                    <Input type='hidden' name='product_id' id='${cart.key}product_id' value='${cart.value.drinkBean.product_id}' />
                  <h5 class="itemtile" id="${cart.key}product_name">${ cart.value.drinkBean.product_name}${ cart.value.capacity}</h5>
                </div>
                <div class="col-3 d-flex justify-content-end ">
                  <h6 id="${cart.key}itemprice" class="itemprice">$${ cart.value.price}</h6>
                </div>
              </div>
              <div class="row itemSpecification">
                <div class="col-2"></div>
                <div class="col-3 d-flex align-items-end">
                  <p>
                    溫度<br />
                    甜度<br />
                    加料<br />
                    備註<br />
                    傳情小貼紙
                  </p>
                </div>
                <div class="col-7 d-flex align-items-end">
                  <p>
                    <span id="${cart.key}temp_level">${ cart.value.tempLevelBean.temp_level}</span><br />
                    <span id="${cart.key}sugar_level">${ cart.value.sugarLevelBean.sugar_level}</span><br />
                    <span id="${cart.key}topping_name">
                    <c:forEach var="topping" items="${cart.value.itemToppings}">
                    ${topping.toppingBean.topping_name}&nbsp;
                    
                    </c:forEach>
                  
                    </span><br/>
                    <span id="${cart.key}note">${cart.value.note }&nbsp;</span><br />
                    <span id="${cart.key}message">${cart.value.message }&nbsp;</span>
                  </p>
                </div>
              </div>
              <div class="row">
                <div class="col d-flex justify-content-end">
                  <Input type='hidden' name='item_cal' id="${cart.key}item_cal" value='${cart.value.item_cal}' />
                  <button class="btn addhealthReminder">
                    <i class="fas fa-plus" style="pointer-events:none;" id="${cart.key}addhealthReminder">&emsp;健康提醒</i>
                  </button>
                   <Input type='hidden' name='add_to_health' id="${cart.key}add_to_health" value='${cart.value.add_to_health}' />
                </div>
                <div style="display:none" id="${cart.key}topping_id"><c:forEach var="topping" items="${cart.value.itemToppings}">${topping.toppingBean.topping_id},</c:forEach></div>  
              </div>
            </div>
<hr class="aa" />
 </c:forEach>   

          </div>
        </div>
        <div class="col-sm-4">
          <div
            class="
              d-flex
              flex-column
              right1
              justify-content-center
              align-items-center
            "
          >
            <div style="margin-bottom: 15px"><h3 id="subtotal">總計$<fmt:formatNumber type="number" value="${ShoppingCart.cartSubTotal}" maxFractionDigits="0"/></h3></div>
            <div>
              <a href="#"
                ><button
                id="gotopay"
                  type="button"
                  style="width: 200px"
                  class="btn btn-dark"
                >
                  去付款
                </button></a
              >
            </div>
          </div>
        </div>

        <div class="col-sm-1"></div>
      </div>
    </div>

    <!-- 結束 -->

   
    <!-- Health reminder Modal -->
    <div
      class="modal fade"
      id="healthReminder"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">
              將此飲品加入健康提醒?
            </h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body d-flex">
            <div>
              <select
                class="form-select"
                id="floatingSelect"
                name="count"
                style="width: 80px; height: 35px"
              >
              </select>
            </div>
            <div
              id="healthReminder_productname"
              style="
                margin-right: 5px;
                font-size: 20px;
                margin-top: 5px;
                margin-left: 5px;
              "
            >飲品名稱</div>
            <div
              style="
                margin-left:auto;
                font-size: 20px;
                margin-top: 5px;
              "
            ><span id="healthReminder_productcal"></span>cal</div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              取消
            </button>
            <button type="button" class="btn btn-dark" id="savecal">儲存</button>
            <Input type='hidden' name='ittkey' id="ittkey" value='aa'/>
          </div>
        </div>
      </div>
    </div>

    <!-- selectTime Modal -->
    <div
      class="modal fade"
      id="selectTimeModal"
      tabindex="-1"
      aria-labelledby="exampleModalLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">挑選時間</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <form action="">
          <div class="modal-body d-flex justify-content-center">
            <div style="margin-right: auto; font-size: 20px;" id="today">
            </div>
            <div>
             <input  id="appt-time" type="text" class="timepicker" style="width: 150px;height: 30px;text-align: center;">
             
            </div>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              data-bs-dismiss="modal"
            >
              取消
            </button>
            <button type="submit" class="btn btn-dark" onclick="timesave()">儲存</button>
          </div>
        </form>
        </div>
      </div>
    </div>



 <!-- login Modal -->
 <div
 class="modal fade"
 id="loginModal"
 tabindex="-1"
 aria-labelledby="exampleModalLabel"
 aria-hidden="true"
>
 <div class="modal-dialog">
   <div class="modal-content">
     <div class="modal-header">
       <h5 class="modal-title" id="exampleModalLabel">請登入以使用此功能</h5>
       <button
         type="button"
         class="btn-close"
         data-bs-dismiss="modal"
         aria-label="Close"
       ></button>
     </div>
     <div class="modal-body d-flex justify-content-center">
      
       <button type="button" class="btn btn-dark" style="width: 200px;" onclick="LoginRegister()">登入/註冊</button>
       
      </div>
     </div>
   
   </div>
 </div>

	<!-- modify Modal START-->
	<div class="modal fade" id="modifyModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="productname">飲品名稱</h5>
					<input type='hidden' id='productId'>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body item-modal-body">
					<div class="accordion" id="accordionPanelsStayOpenExample">
						<div class="accordion-item">
							<h2 class="accordion-header" id="panelsStayOpen-headingTwo">
								<button class="accordion-button" type="button"
									data-bs-toggle="collapse"
									data-bs-target="#panelsStayOpen-collapseTwo"
									aria-expanded="true" aria-controls="panelsStayOpen-collapseTwo">
									溫度</button>
							</h2>
							<div id="panelsStayOpen-collapseTwo"
								class="accordion-collapse collapse show"
								aria-labelledby="panelsStayOpen-headingTwo">
								<div class="accordion-body">
									<div class="btn-group" role="group"
										aria-label="Basic radio toggle button group" id="icebtn">
									</div>
								</div>
							</div>
						</div>

						<div class="accordion-item">
							<h2 class="accordion-header" id="panelsStayOpen-headingThree">
								<button class="accordion-button" type="button"
									data-bs-toggle="collapse"
									data-bs-target="#panelsStayOpen-collapseThree"
									aria-expanded="false"
									aria-controls="panelsStayOpen-collapseThree">甜度</button>
							</h2>
							<div id="panelsStayOpen-collapseThree"
								class="accordion-collapse collapse show"
								aria-labelledby="panelsStayOpen-headingThree">
								<div class="accordion-body">
									<div class="btn-group" role="group"
										aria-label="Basic radio toggle button group" id="sweetbtn">
									</div>
								</div>
							</div>
						</div>
						<div class="accordion-item">
							<h2 class="accordion-header" id="panelsStayOpen-heading4">
								<button class="accordion-button" type="button"
									data-bs-toggle="collapse"
									data-bs-target="#panelsStayOpen-collapse4"
									aria-expanded="false" aria-controls="panelsStayOpen-collapse4">
									加料</button>
							</h2>
							<div id="panelsStayOpen-collapse4"
								class="accordion-collapse collapse show"
								aria-labelledby="panelsStayOpen-heading4">
								<div class="accordion-body">
									<div class="btn-group" role="group"
										aria-label="Basic radio toggle button group" id="addon">
									</div>
								</div>
							</div>
						</div>
						<div class="accordion-item">
							<h2 class="accordion-header" id="panelsStayOpen-heading6">
								<button class="accordion-button" type="button"
									data-bs-toggle="collapse"
									data-bs-target="#panelsStayOpen-collapse6"
									aria-expanded="false"
									aria-controls="panelsStayOpen-collapseThree">備註</button>
							</h2>
							<div id="panelsStayOpen-collapse6"
								class="accordion-collapse collapse show"
								aria-labelledby="panelsStayOpen-heading664">
								<div class="accordion-body">
									<textarea id="note" name="memo" cols="45" rows="1"></textarea>
								</div>
							</div>
						</div>

						<div class="accordion-item">
							<h2 class="accordion-header" id="panelsStayOpen-heading5">
								<button class="accordion-button" type="button"
									data-bs-toggle="collapse"
									data-bs-target="#panelsStayOpen-collapse5"
									aria-expanded="false"
									aria-controls="panelsStayOpen-collapseThree">傳情小貼紙</button>
							</h2>
							<div id="panelsStayOpen-collapse5"
								class="accordion-collapse collapse show"
								aria-labelledby="panelsStayOpen-heading5">
								<div class="accordion-body">
									<textarea id="message" name="talk" cols="45" rows="3" placeholder="限制30字"></textarea>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer justify-content-between">
					<div>
						<table id="tab">
							<tr>
								<td class="d-flex justify-content-between align-items-center">
									<input class="min" name="" type="button" /> <input
									class="text_box" name="" type="text" id="quantity" value="1" readonly/> <input
									class="add" name="" type="button" />
								</td>
							</tr>
						</table>
					</div>
				<Input type='hidden' name='itemkeyyy' id="itemkeyyy" value='cc' />
					<button type="button" class="btn btn-dark" style="width: 300px;" id="submitbtn">完成</button>
				</div>
			</div>
		</div>
	</div>

	<!-- modify Modal END-->



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
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
    <script src="<c:url value='/_04_ShoppingCart/shoppingcart1.js'/>"></script>

  </body>
</html>
