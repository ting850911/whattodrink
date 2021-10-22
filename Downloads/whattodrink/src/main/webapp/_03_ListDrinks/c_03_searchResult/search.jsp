<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>index</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />

  <link rel="stylesheet" href="<c:url value='/_03_ListDrinks/c_03_searchResult/search1.css'/>">
  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>

<body>
  
   <jsp:include page="/_08_Fragment/top.jsp" />

  <!-- 開始 -->
  <div class="container-fluid" style="min-height: 70vh;">
    <div class="row mt-4">
      <div class="col-sm-1"></div>
      <div class="col-sm-2 mx-auto">
        <div style="margin-bottom: 20px;position: sticky ;top: 100px;">
          <p style="font-size: 28px;">「${tagNameOrKeyword}」</p>
          <p style="font-size: 18px;">「${tagNameOrKeyword}」的${searchResult.size()}項結果</p>
          <Input type='hidden' id="searchMethod" name='searchMethod' value='${searchMethod}'/>
          <Input type='hidden' id="tagNameOrKeyword"name='tagNameOrKeyword' value='${tagNameOrKeyword}'/>
          <form id="businessescategory">
            <input type="radio" name="category" id="distance"  value="distance" onclick="godistance()">距離<br>
            <input type="radio" name="category" id="price"     value="price" onclick="goprice()">價錢<br>
            <input type="radio" name="category" id="calories"value="calories" onclick="gocal()">熱量<br>
          </form>

        </div>

      </div>
      <div class="col-sm-8 mx-auto d-flex flex-wrap justify-content-start lign-items-start">


	<c:forEach varStatus="stVar" var="bb" items="${searchResult}">
        <div class="allshop">
        <Input type='hidden' name='startTime' value='${fn:substring(bb.companyBean.start_time,0,5)}'/>
        <Input type='hidden' name='endTime' value='${fn:substring(bb.companyBean.end_time,0,5)}'/>
        <Input type='hidden' name='company' value='${bb.product_id}'/>
          <img src="<c:url value='/${bb.product_picpath}'/>" style="width: 180px;" alt="">
          <h5>${bb.product_name}${bb.capacity}</h5>
          <p>$${bb.product_price}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${bb.product_cal}cal</p>
          <p><i class="fas fa-street-view"></i>${bb.companyBean.distance}公尺</p>
          <p> <a href="<c:url value="/_03_ListDrinks/StorePage?companyId=${bb.companyBean.company_id}"/>" style="color: black;">${bb.companyBean.company_name}${bb.companyBean.trade_name}</a></p>
        </div>
    </c:forEach> 
       
        
      </div>
      <div class="col-sm-1"></div>
    </div>


  </div>

  <!-- 結束 -->

	<!-- modify Modal START-->
	<div class="modal fade" id="addCartAlert" tabindex="-1"
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

					<button type="button" class="btn btn-dark" style="width: 300px;" id="submitbtn">完成</button>
				</div>
			</div>
		</div>
	</div>

	<!-- modify Modal END-->



 

<!-- clear shopping Modal start-->
  <div class="modal fade" id="clearModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">啟動新購物車?</h5>
          <button
            type="button"
            class="btn-close"
            data-bs-dismiss="modal"
            aria-label="Close"
          ></button>
        </div>
        <div class="modal-body d-flex justify-content-center">
          
          <div>
            您的購物車已包含其他店家的飲品，是否清空購物車，<br>改成這項飲品?
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
          <button type="button" class="btn btn-dark" id="clearbtn">更新購物車</button>
        </div>
    
    
    </div>
  </div>
  </div>
<!-- clear shopping Modal end-->



  <!-- Modal(sharecode) start-->
  <div class="modal fade" id="sharecode" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">分享你的邀請碼給好友吧</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body d-flex justify-content-center" style="padding-top: 30px;">
          <i class="fas fa-gift fa-lg" id="Invitationcode">&nbsp;&nbsp;&nbsp;&nbsp;${CLoginOK.invitation}&nbsp;</i>
          <button type="button" class="btn" style="position: relative;bottom: 9px;" data-bs-container="body"
            data-bs-toggle="popover" data-bs-placement="right" data-bs-content="已複製">
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
  
  
  
  <!--opening hours Modal start-->
<div class="modal fade" id="openinghoursModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel" style="margin:0px">此商家目前尚未營業</h5>
      </div>
      <div class="modal-body" id="openinghoursModalmodalbody" style="text-align: center;">
      </div>
      <div class="modal-footer">
       <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close" style="background-color:#FCD8D4">去別家逛逛</button>
      </div>
    </div>
  </div>
</div>
<!--opening hours Modal end-->
  
  
  
  
  
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
     
    <script src="<c:url value="/_03_ListDrinks/c_03_searchResult/search1313.js"/>"></script>


</body>

</html>