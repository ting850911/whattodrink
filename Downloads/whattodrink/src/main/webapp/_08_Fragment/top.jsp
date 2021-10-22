<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>




<c:if test="${CLoginOK==null}">

	<!-- hamburger menu1 start-->
	<div class="offcanvas offcanvas-start d-flex flex-column"
		id="hamburgerMenu" style="width: 300px; padding: 10px" tabindex="-1">
		<div class="offcanvas-header">
			<button type="button" class="btn btn-close text-reset"
				data-bs-dismiss="offcanvas"></button>
		</div>
		<div class="offcanvas-body lh-base">
			<div class="d-block" style="height: 70vh">
				<h6 class="fw-normal">
					<a
						href="<c:url value="/_01_Register/c_01_register/LoginRegister.jsp"/>"><button
							type="submit" style="width: 200px;" class="btn btn-dark my-2">登入</button></a>
				</h6>
				<h6 class="fw-normal">
					<a class="link-dark text-decoration-none"
						href="<c:url value="/_01_Register/b_01_register/1_business_register_1.jsp"/>">建立企業帳戶</a>
				</h6>
				<h6 class="fw-normal">
					<a class="link-dark text-decoration-none"
						href="<c:url value="/_02_Login/b_02_login/1_business_login.jsp"/>">登入您的商店</a>
				</h6>

			</div>
			<div class="d-flex flex-column">
				<a href="" class="logo"><img
					src="<c:url value="/images/logo.png"/>" alt="logo"
					class="small-logo" /></a> <a href=""
					class="text-dark text-decoration-none app_download"><span
					class="border border-1 rounded-1 p-1 border-dark"
					style="width: fit-content"><i
						class="fab fa-google-play me-1"></i>應用程式</span></a>
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
					<a class="link-secondary text-decoration-none"
						href="<c:url value="/C_LogoutServlet"/>">登出</a>
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
</c:if>



<!-- Menu Start -->
<nav class="navbar navbar-expand-sm navbar-light d-flex flex-column">
	<div class="container-fluid">
		<div class="col-sm-1"></div>
		<a class="navbar-brand" data-bs-toggle="offcanvas"
			href="#hamburgerMenu"><span id="openbar"
			class="navbar-toggler-icon"></span></a>
		<!-- lift menu -->
		<ul class="navbar-nav me-md-auto">
			<li class="nav-item"><a class="nav-link"
				href="<c:url value='/_00_Index/index.jsp'/>"><img id="logo"
					src="<c:url value="/images/logo.png"/>" alt="" /></a></li>
		</ul>
		<ul class="navbar-nav mx-md-auto flex-row hide show7">
			<li class="nav-item show7-1"><a class="nav-link btn"
				data-bs-toggle="collapse" href="#collapseExample0" role="button"
				aria-expanded="false" aria-controls="collapseExample0"> <i
					class="fas fa-map-marker-alt fa-lg"></i>
			</a></li>
			<li class="nav-item show7-1"><a class="nav-link btn"
				data-bs-toggle="collapse" href="#collapseExample1" role="button"
				style="cursor: pointer;" aria-expanded="false"
				aria-controls="collapseExample1"> <i class="fas fa-search fa-lg"></i></a></li>
			<li class="nav-item show7-1"><a class="nav-link"
				onclick="doo3()" style="cursor: pointer;"><i
					class="fas fa-shopping-cart fa-lg"></i></a></li>

			<c:if test="${CLoginOK==null}">
				<li class="nav-item show7-1"><a class="nav-link"
					style="cursor: pointer;"
					href="<c:url value='/_01_Register/c_01_register/LoginRegister.jsp'/>">
						<i class="fas fa-sign-in-alt fa-lg"></i>
				</a></li>
			</c:if>

			<c:if test="${CLoginOK!=null}">
				<li class="nav-item show7-1"><a class="nav-link"
					style="cursor: pointer;" href="<c:url value="/C_LogoutServlet"/>">
						<i class="fas fa-sign-out-alt fa-lg"></i>
				</a></li>
			</c:if>

		</ul>


		<ul class="navbar-nav mx-md-auto flex-row hide7">

			<li class="nav-item me-1">
				<form action="" id="locationform0">
					<input type="text" class="form-control" id="location0"
						placeholder="您現在的位置"
						style="background-color: rgba(255, 255, 255, 0.26); border: none;" required="required" />
				</form>

			</li>
			<li class="nav-item"><i class="fas fa-map-marker-alt fa-lg"
				style="position: relative; right: 30px; top: 8px; color: rgb(80, 77, 77);"></i>
			</li>
		</ul>
		<!-- right menu -->
		<ul class="navbar-nav ms-ms-auto ms-md-auto hide7" id="menuright">
			<li class="nav-item">
				<form
					action="<c:url value='/_03_listDrinks/RetrieveDrinksByNative'/>"
					method="POST" id="searchform1">
					<input type="text" class="form-control"
						placeholder="好想喝..." name="keyword" required="required"
						style="background-color: rgba(255, 255, 255, 0.26); border: none;" />
				</form>
			</li>
			<li class="nav-item"><a class="nav-link"
				style="cursor: pointer;" onclick="doo1()"><i
					class="fas fa-search fa-lg"></i></a></li>


			<li class="nav-item"><a class="nav-link"><i
					class="fas fa-shopping-cart fa-lg" style="cursor: pointer;"
					onclick="doo3()"></i></a></li>

			<c:if test="${CLoginOK==null}">
				<li class="nav-item"><a class="nav-link"
					style="cursor: pointer;"
					href="<c:url value='/_01_Register/c_01_register/LoginRegister.jsp'/>">
						<i class="fas fa-sign-in-alt fa-lg"></i>
				</a></li>
			</c:if>
			<c:if test="${CLoginOK!=null}">
				<li class="nav-item"><a class="nav-link"
					style="cursor: pointer;" href="<c:url value="/C_LogoutServlet"/>">
						<i class="fas fa-sign-out-alt fa-lg"></i>
				</a></li>
			</c:if>




		</ul>
		<div class="col-sm-1"></div>
	</div>
	<div class="collapse hide show7" id="collapseExample0">
		<ul class="navbar-nav mx-md-auto flex-row">
			<li class="nav-item me-1">
				<form action="" id="locationform1">
					<input type="text" class="form-control" id="location1"
						placeholder="您現在的位置" required="required"
						style="background-color: rgba(255, 255, 255, 0.26); border: none;" />
				</form>

			</li>
			<li class="nav-item "><i class="fas fa-map-marker-alt fa-lg"
				style="position: relative; right: 30px; top: 8px; color: rgb(80, 77, 77);"></i>
			</li>
		</ul>
	</div>
	<div class="collapse hide show7" id="collapseExample1">
		<ul class="navbar-nav mx-md-auto flex-row">
			<li class="nav-item">
				<form
					action="<c:url value='/_03_listDrinks/RetrieveDrinksByNative'/>"
					method="POST" id="searchform2">

					<input type="text" class="form-control" name="keyword"
						placeholder="好想喝..." required="required"
						style="background-color: rgba(255, 255, 255, 0.26); border: none;" />
				</form>

			</li>
			<li class="nav-item"><a class="nav-link"
				style="cursor: pointer;" onclick="doo2()"><i
					class="fas fa-search fa-lg"
					style="position: relative; right: 35px;"></i></a></li>


		</ul>
	</div>
</nav>

<!-- Menu End -->




<!-- nocart Modal -->
<div class="modal fade" id="NocartModal" tabindex="-1"
	aria-labelledby="exampleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">購物還空空的哦</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body d-flex justify-content-center">

				<button type="button" class="btn btn-dark" style="width: 200px;"
					data-bs-dismiss="modal">快去逛逛吧</button>

			</div>
		</div>

	</div>
</div>


<!-- nocart Modal -->






<!--location Modal -->
<div class="modal fade" id="location" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel" style="margin-left: 0px">請輸入地址/地標</h5>
      </div>
      <form action="" id="locationform2">
      <div class="modal-body">
        
					<input type="text" class="form-control" id="location2" required="required"
						placeholder="您現在的位置" required style="background-color: rgba(255, 255, 255, 0.26); border: none;" />
				
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn" id="locationbtn">確定</button>
        <button type="button" class="btn" data-bs-dismiss="modal">取消</button>
      </div>
      </form>
    </div>
  </div>
</div>
<!--location Modal -->



<script
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDx1N-wHeMdqX-ZieKXx2BeYnLz1drNa6U&callback=initMap&libraries=&v=weekly"
	async></script>
<script>

//location


$(function() {

	//儲存地址
	let storage = sessionStorage;

	document.getElementById("location0").value = storage.getItem("Address");
	document.getElementById("location1").value = storage.getItem("Address");
	
	
	
	const locationModal = document.getElementById("location");
	const LocationModal = new bootstrap.Modal(locationModal, { keyboard: false });

		if(($('#location0').val()=="")||($('#location1').val()=="")){		
			LocationModal.show();
		}

		
		
		
		$('#locationbtn').click(function(e){
			 if (document.getElementById("locationform2").checkValidity() == true) {
					e.preventDefault();
					let Address = document.getElementById("location2").value;
					storage.setItem("Address", Address);
					document.getElementById("location0").value = storage.getItem("Address");
					document.getElementById("location1").value = storage.getItem("Address");
					initMap();
					
					
					
					
					function initMap() {
					    destination1=['台北市中正區新生南路一段52之3號','台北市中正區八德路一段82巷9弄18號','台北市中正區八德路一段82巷9弄20之1號','台北市中山區遼寧街38號','台北市大安區市民大道三段136號','台北市中正區青島東路21之10號','台北市大安區新生南路一段1號'];

					    
					   // 所在位置跟各點的距離
					   var service = new google.maps.DistanceMatrixService();
					   service.getDistanceMatrix(
					     {
					       origins: [document.getElementById("location0").value],
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
					                 location.reload()
					               },
					             });
					          
					         }
					   );
					}
					
					
					LocationModal.hide();
			}
			
			
			
			
		});
	
	});



	function doo1() {
		if (document.getElementById("searchform1").checkValidity() == true) {
		document.getElementById('searchform1').submit();
		}
	};

	function doo2() {
		if (document.getElementById("searchform2").checkValidity() == true) {
		document.getElementById('searchform2').submit();
		}
	};



	
	function doo3() {

		$.ajax({
		    url: "http://localhost:8080/whattodrink/_04_ShoppingCart/RetrieveShoppingCart",
		    type: "POST",
		    success(res) {
			console.log(res);
				if(res=="NoCart"){
					const NocartModal = document.getElementById("NocartModal");
					const nocartModal = new bootstrap.Modal(NocartModal, { keyboard: false });
						nocartModal.show();
						
					}else{
						window.location.assign("http://localhost:8080/whattodrink/_04_ShoppingCart/shoppingcart.jsp");		
					}
			
						}
			});
		
	};
	

	
	// 複製邀請碼
	function copy() {
		var content = document.getElementById("Invitationcode").innerText;
		navigator.clipboard
			.writeText(content)
			.then(() => {
				console.log("Text copied to clipboard...");
			})
			.catch((err) => {
				console.log("Something went wrong", err);
			});
	}

	let storage = sessionStorage;

	//按下enter送出定位表單
	$("#location0").keydown(function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
			let Address = document.getElementById("location0").value;
			storage.setItem("Address", Address);
			document.getElementById("location0").value = storage.getItem("Address");
			document.getElementById("location1").value = storage.getItem("Address");
			initMap();
			
			
			
			
			function initMap() {
			    destination1=['台北市中正區新生南路一段52之3號','台北市中正區八德路一段82巷9弄18號','台北市中正區八德路一段82巷9弄20之1號','台北市中山區遼寧街38號','台北市大安區市民大道三段136號','台北市中正區青島東路21之10號','台北市大安區新生南路一段1號'];

			    
			   // 所在位置跟各點的距離
			   var service = new google.maps.DistanceMatrixService();
			   service.getDistanceMatrix(
			     {
			       origins: [document.getElementById("location0").value],
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
			                 location.reload()
			               },
			             });
			          
			         }
			   );
			}

			
			
			
			
		}
	});

	$("#location1").keydown(function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
			let Address = document.getElementById("location1").value;
			storage.setItem("Address", Address);
			document.getElementById("location0").value = storage.getItem("Address");
			document.getElementById("location1").value = storage.getItem("Address");
			initMap();
			
			
			function initMap() {
			    destination1=['台北市中正區新生南路一段52之3號','台北市中正區八德路一段82巷9弄18號','台北市中正區八德路一段82巷9弄20之1號','台北市中山區遼寧街38號','台北市大安區市民大道三段136號','台北市中正區青島東路21之10號','台北市大安區新生南路一段1號'];

			    
			   // 所在位置跟各點的距離
			   var service = new google.maps.DistanceMatrixService();
			   service.getDistanceMatrix(
			     {
			       origins: [document.getElementById("location0").value],
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
			                 location.reload()
			               },
			             });
			          
			         }
			   );
			}

		}
	});


</script>
