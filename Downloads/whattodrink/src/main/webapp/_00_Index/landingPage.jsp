<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>推廣頁</title>
  <script src="<c:url value="/_00_Index/landingPage.js"/>"></script>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />
  <link href="https://unpkg.com/aos@2.3.1/dist/aos.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="<c:url value="/_00_Index/landingPage.css"/>">
</head>

<body onLoad="setFocusToAddress()">


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
				<a class="link-secondary text-decoration-none" href="">登出</a>
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
  <nav class="navbar navbar-expand-sm navbar-light">
    <div class="container-fluid">
     <div class="col-sm-1"></div>
      <a class="navbar-brand" data-bs-toggle="offcanvas" href="#hamburgerMenu"><span id="openbar"
          class="navbar-toggler-icon"></span></a>

        <!-- lift menu -->
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/_00_Index/index.jsp"/>"><img id="logo" src="<c:url value="/images/logo.png"/>" alt="" /></a>
          </li>

        </ul>
        <!-- right menu -->
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li class="nav-item hide">
            <a class="nav-link" href="#"><i class="fab fa-google-play fa-lg"></i></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<c:url value="/_01_Register/c_01_register/LoginRegister.jsp"/>"><i class="fas fa-sign-in-alt fa-lg"></i></a>
          </li>

        </ul>
 <div class="col-sm-1"></div>
      </div>
    </div>
  </nav>
  <!-- Menu End -->

  <!-- Mian Start -->
  <div class="main1">
    <div class="container">
      <div class="row">
      <div class="col-sm-1"></div>
        <div class="col-sm-6 col-10 ms-4">
          <form id="locationform">
            <div class="d-flex">
              <i class="fas fa-map-marker-alt fa-2x" style="position: relative;right: 15px;"></i>
              <input type="text" class="form-control" id="location" name="location" placeholder="您在的位置"
                style="background-color: rgba(255, 255, 255, 0.26);border: none;">
            </div>
            <p class="ms-4 mt-2"><a href="<c:url value="/_01_Register/c_01_register/LoginRegister.jsp"/>" style="color: #000;">登入</a>搜尋想喝飲品</p>
          </form>
         
        </div>
      </div>
    </div>
  </div>

  <!-- Mian End -->

  <!-- Qrcode Start -->
  <div class="qrcode" data-aos="fade-right" data-aos-duration="1500">
    <img src="<c:url value="/images/QRcode.png"/>" alt="" height="120px">
    <h4>歡迎下載今天喝什麼app</h4>
  </div>
  <!-- Qrcode End -->

<!-- 小貼紙廣告 Start-->
  <div class="qrcode" data-aos="zoom-in" data-aos-duration="800">
    <img src="<c:url value="/images/圖片1.png"/>" style="width: 70%;">
  </div>
<!-- 小貼紙廣告 End-->

  <!-- card Start -->
  <div data-aos="fade-zoom-in" data-aos-easing="ease-in-back"
    class="card1 container-fluid" style="margin-top: 30px;">
    <div class="row d-flex justify-content-center align-items-center">
      <div class="col-sm-1"></div>
      <div class="col-sm-3 d-flex justify-content-center align-items-center">
        <div class="card" style="width: 18rem;">
          <img src="<c:url value='/images/landing0.png'/>" class="card-img-top" alt="..." height="200px">
          <div class="card-body">
            <p class="card-text">分享你的邀請碼!雙方都能獲得50元優惠券 趕快來註冊吧
              </p>
          </div>
        </div>
      </div>
      <div class="col-sm-3 d-flex justify-content-center align-items-center">
        <div class="card" style="width: 18rem;">
          <img src="<c:url value='/images/landing2.png'/>" class="card-img-top" alt="..." height="200px">
          <div class="card-body">
            <p class="card-text">雖然熱愛飲料，但也不忘保持健康，來看看今日的嗜糖紀錄吧 !</p>
          </div>
        </div>
      </div>
      <div class="col-sm-3 d-flex justify-content-center align-items-center">
        <div class="card" style="width: 18rem;">
          <img src="<c:url value='/images/landing3.png'/>" class="card-img-top" alt="..." height="200px">
          <div class="card-body">
            <p class="card-text">買飲料但怕踩雷 ? 別擔心 ! 先來看看飲品評論吧</p>
          </div>
        </div>
      </div>
      <div class="col-sm-1"></div>

    </div>


  </div>
  <!-- card end -->


  <!-- map start -->
  <div id="map"></div>
  <!-- map end -->


  <!-- footer end -->
  <footer id="footer">
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-1"></div>
        <div class="footer-content logo col-sm-5 m-0">
          <a href=""><img src="<c:url value='/images/logo.png'/>" alt="logo" class="small-logo" /></a>
          <div class="app_download">
            <a href="" class="text-dark text-decoration-none"><span class="border border-1 rounded-1 p-1 border-dark"><i
                  class="fab fa-google-play me-1"></i>應用程式</span></a>
          </div>
        </div>
        <div class="col-sm-1"></div>
        <div class="footer-content col-sm-2">
          <a href="" class="link-dark text-decoration-none">關於我們</a>
          <a href="" class="link-dark text-decoration-none">建立企業帳戶</a>
          <a href="" class="link-dark text-decoration-none">登入您的商店</a>
        </div>
        <div class="footer-content col-sm-2">
          <a href="" class="link-dark text-decoration-none">取得協助</a>
          <a href="" class="link-dark text-decoration-none">附近的商家</a>
          <a href="" class="link-dark text-decoration-none">切換語言</a>
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
          <i class="fab fa-facebook"></i>
          <i class="fab fa-instagram"></i>
          <i class="fab fa-twitter"></i>
        </div>
        <div class="
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


  <script src="https://unpkg.com/aos@2.3.1/dist/aos.js"></script>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>

    <!-- Async script executes immediately and must be after any DOM elements used in callback. -->
    <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDx1N-wHeMdqX-ZieKXx2BeYnLz1drNa6U&callback=initMap&libraries=&v=weekly"
      async
    ></script>
    <script>
    AOS.init();
    
    let storage = sessionStorage;
    $("#location").keydown(function (event) {
    if (event.keyCode == 13) {
      event.preventDefault();
      let Address = document.getElementById("location").value;
      storage.setItem("Address", Address);
      initMap();
      
function initMap() {
         destination1=['台北市中正區新生南路一段52之3號','台北市中正區八德路一段82巷9弄18號','台北市中正區八德路一段82巷9弄20之1號','台北市中山區遼寧街38號','台北市大安區市民大道三段136號','台北市中正區青島東路21之10號','台北市大安區新生南路一段1號'];

         
        // 所在位置跟各點的距離
        var service = new google.maps.DistanceMatrixService();
        service.getDistanceMatrix(
          {
            origins: [document.getElementById("location").value],
            destinations: destination1,
            travelMode: "WALKING", // 交通方式：BICYCLING(自行車)、DRIVING(開車，預設)、TRANSIT(大眾運輸)、WALKING(走路)
            unitSystem: google.maps.UnitSystem.METRIC, // 單位 METRIC(公里，預設)、IMPERIAL(哩)
          },function (response) {
            console.log(response);
            
                str='';
                str += `A01=`+response.rows[0].elements[0].distance.value+`&B01=`+response.rows[0].elements[1].distance.value+`&C01=`+response.rows[0].elements[2].distance.value+`&D01=`+response.rows[0].elements[3].distance.value+`&E01=`+response.rows[0].elements[4].distance.value+`&F01=`+response.rows[0].elements[5].distance.value+`&G01=`+response.rows[0].elements[6].distance.value;
                console.log(response.rows[0].elements[0].distance.value);
                console.log(str);  
                  
                $.ajax({
                    type: "POST",
                    url: "http://localhost:8080/whattodrink/DistanceServlet",
                    data:str,
                    success: function (response) {
                      console.log(response);  
                    },
                  });
                window.location.assign("http://localhost:8080/whattodrink/_00_Index/index.jsp");
              }
        );
  }






    }
   })
    </script>

<script type="text/javascript">
//由<body>的onLoad事件處理函數觸發此函數
function setFocusToAddress(){   
	 document.forms[0].location.focus();  
}
</script>

</body>

</html>