<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>商家頁面</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
	integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />

<link rel="stylesheet"
	href="<c:url value="/_03_ListDrinks/c_03_storePage/StorePage.css"/>" />

</head>

<body>


	<!-- hamburger menu1 start-->

	<c:if test="${CLoginOK==null}">
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
	</c:if>
	<!-- hamburger menu1 end-->





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
						style="width: fit-content"><i
							class="fab fa-google-play me-1"></i>應用程式</span></a>
				</div>
			</div>
		</div>
	</c:if>
	<!-- hamburger menu end-->

	<!-- Menu Start -->
	<nav id="menu"
		class="navbar navbar-expand-sm navbar-light d-flex flex-column">
		<div class="container-fluid">
			<div class="col-sm-1"></div>
			<a class="navbar-brand" data-bs-toggle="offcanvas"
				href="#hamburgerMenu"><span id="openbar"
				class="navbar-toggler-icon"></span></a>
			<!-- lift menu -->
			<ul class="navbar-nav me-md-auto left-menu">
				<li class="nav-item"><a class="nav-link"
					href="<c:url value="/_00_Index/index.jsp"/>"><img id="logo"
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
					aria-expanded="false" aria-controls="collapseExample1"> <i
						class="fas fa-search fa-lg"></i>
				</a></li>
				<li class="nav-item show7-1"><a class="nav-link"
					onclick="doo3()" style="cursor: pointer;"><i class="fas fa-shopping-cart fa-lg"></i></a></li>

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
							placeholder="您現在的位置" required="required"
							style="background-color: rgba(255, 255, 255, 0.26); border: none;" />
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
						<input type="text" class="form-control" id="search"
							placeholder="好想喝..." name="keyword" required="required"
							style="background-color: rgba(255, 255, 255, 0.26); border: none;" />
					</form>
				</li>
				<li class="nav-item"><a class="nav-link"  onclick="doo1()"><i
						class="fas fa-search fa-lg"></i></a></li>

				<li class="nav-item"><a class="nav-link cart-icon" style="cursor: pointer;"
					onclick="doo3()"><i class="fas fa-shopping-cart fa-lg"></i></a></li>
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

						<input type="text" class="form-control" name="keyword" id=""
							placeholder="好想喝..." required="required"
							style="background-color: rgba(255, 255, 255, 0.26); border: none;" />
					</form>

				</li>
				<li class="nav-item"><a class="nav-link" onclick="doo2()"><i
						class="fas fa-search fa-lg"
						style="position: relative; right: 35px;"></i></a></li>


			</ul>
		</div>
	</nav>

	<!-- Menu End -->

	<!-- 開始 -->
	<!-- Store Picture START -->
	<c:set var="radom" value="<%=Math.random()%>" />
	<div class="container-fluid bg-light storeimage"
		style="background-image:linear-gradient(rgba(0, 0, 0, 0.3),rgba(0,0,0,0.3)), url(<c:url value="/${storeCategoryBeans[0].companyBean.bg_iconpath}?rr=${radom}"/>);">
		<div class="col-sm-2 ps-md-4 pt-3">
			<a href="<c:url value="/_00_Index/index.jsp"/>"
				style="text-decoration: none; color: white"><i
				class="fas fa-chevron-left"></i>上一頁</a>
		</div>
		<div class="row">
			<div class="col-sm-10 mx-auto">
				<h3>
					<div style="margin-top: 15px">
						<c:if test="${CLoginOK!=null}">
							<i onclick="change(this)" class="far fa-heart fa-lg"
								style="color: pink"
								id="${storeCategoryBeans[0].companyBean.company_id}"></i>
						</c:if>
						<span style="color: white;">${storeCategoryBeans[0].companyBean.company_name}
							| ${storeCategoryBeans[0].companyBean.trade_name}</span>
					</div>
				</h3>
				<div class="mt-3 ps-2">
					<i class="fas fa-map-marker-alt fa-1x text-danger"></i><span>&nbsp;</span>
					<button
						style="border: none; background: transparent; text-decoration: underline; color: rgb(255, 255, 255);"
						id="addressMaps" class="addressMaps">${storeCategoryBeans[0].companyBean.company_address}</button>
				</div>


				<!-- 彈跳maps start-->
				<div class="modal fade mt-5" id="storeMaps" tabindex="-1"
					aria-labelledby="exampleModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div
								class="modal-header  d-flex flex-column align-items-start border-0 h-25"
								id="modalHeader">
								<button type="button" class="btn-close ms-0 "
									data-bs-dismiss="modal" aria-label="Close"></button>
								<h5 class="modal-title ms-4 pb-0" id="exampleModalLabel">
									<i class="fas fa-map-marker-alt fa-1x text-danger"></i>${storeCategoryBeans[0].companyBean.company_name}
									| ${storeCategoryBeans[0].companyBean.trade_name}
								</h5>
							</div>
							<div class="modal-body mb-2 pt-0">
								<div id="storeMapsBody">
									<iframe
										src="https://maps.google.com?output=embed&q=${storeCategoryBeans[0].companyBean.company_address}"
										width="100%" height="100%" frameborder="0"></iframe>
								</div>
							</div>
						</div>
					</div>
				</div>
				<!-- 彈跳maps end -->

				<Input type='hidden' name='start_time'
					value='${fn:substring(storeCategoryBeans[0].companyBean.start_time,0,5)}' />
				<Input type='hidden' name='end_time'
					value='${fn:substring(storeCategoryBeans[0].companyBean.end_time,0,5)}' />
				<div class="mt-3 mb-4 ps-2">
					<i class="far fa-clock" style="color: white"></i><span
						style="color: white">&nbsp;營業時間
						${fn:substring(storeCategoryBeans[0].companyBean.start_time,0,5)}-${fn:substring(storeCategoryBeans[0].companyBean.end_time,0,5)}</span>
				</div>
			</div>
		</div>
	</div>
	<!-- Store Picture END -->

	<!-- Store Category START -->
	<div id="categories-section" class="container-fluid storeCategory">
		<div class="row d-flex align-items-center ps-2" style="height: 70px">
			<div
				class="
            col-sm-10
            mx-auto
            d-flex
            justify-content-between
            categoryToHeader">

				<c:forEach varStatus="stVar" var="aa" begin="0" end="5"
					items="${storeCategoryBeans}">
					<a class="fs-5" href="#categoryBody${stVar.index}"
						id="category${stVar.index}">${aa.category_name}</a>

				</c:forEach>


				<c:if test="${storeCategoryBeans.size()<6}">

					<a class="fs-5" href="#categoryBody12" id="category12">塑膠袋</a>

				</c:if>


				<c:if test="${storeCategoryBeans.size()>5}">
					<div class="btn-group">
						<a class="fs-5 d-flex" href="" data-bs-toggle="dropdown">顯示更多<i
							class="fas fa-sort-down "></i></a>
						<ul class="dropdown-menu dropdown-menu-sm-end">
							<c:forEach varStatus="stVar" var="aa" begin="6" end="11"
								items="${storeCategoryBeans}">
								<li class="d-flex justify-content-center"><a
									class="dropdown-item fs-5" style="width: fit-content;"
									href="#categoryBody${stVar.index}" id="category${stVar.index}">${aa.category_name}</a></li>
							</c:forEach>
							<li class="d-flex justify-content-center"><a
								class="dropdown-item fs-5" style="width: fit-content;"
								href="#categoryBody12" id="category12">塑膠袋</a></li>
						</ul>
					</div>

				</c:if>

			</div>
		</div>
		<!-- additional cartIcon -->
		<ul class="navbar-nav">
			<li class="nav-item special-cartIcon" id="special-cartIcon"><a
				class="nav-link" onclick="doo3()" style="cursor: pointer;"><i
					class="fas fa-shopping-cart"></i></a></li>
		</ul>
	</div>
	<div class="col-sm-10 mx-auto" style="border-top: solid 1px black"></div>
	<!-- Store Category END -->

	<!-- Store drink START -->
	<c:forEach varStatus="varStatusName" var="bb" begin="0"
		end="${storeCategoryBeans.size()-1}" items="${storeCategoryBeans}">
		<div class="container-fluid">
			<div class="row" id="categoryBody${varStatusName.index}">
				<div class="col-sm-10 mx-auto">
					<p style="font: bold 25px Tahoma; margin-top: 30px">${storeCategoryBeans[varStatusName.index].category_name}</p>
					<div class="row drink">


						<c:set var="category_id_start"
							value="${storeCategoryBeans[0].category_id}" scope="session" />


						<c:forEach varStatus="ss" var="cc" items="${storeDrinkBeans}"> 				
							<c:if test="${cc.category_id==bb.category_id}">
							<!-- card1 -->
								<div class="col-sm-4">
									<div class="card mb-3">
										<div class="row">
											<div class="col-lg-6">
												<img class="ratio ratio-4x4"
													src="<c:url value='/${cc.product_picpath}'/>"/>
											</div>
											<div class="col-lg-6">
												<div class="card-body">
													<div class="mb-2">
														<Input type='hidden' name='product_id'
															value='${cc.product_id}' />
														<h6 class="card-title fw-bold  addButton">${cc.product_name}${cc.capacity}</h6>
													</div>
													<div>${cc.product_cal}cal</div>
													<div>$${cc.product_price}元</div>
													<c:if test="${cc.avgStar>0}">
														<div>
															<i class="fas fa-star text-warning comment"></i><span
																class="point comment" style="cursor: pointer;">${cc.avgStar}</span>
														</div>
													</c:if>

												</div>
											</div>
										</div>
									</div>
								</div>
							</c:if>
						</c:forEach>
					</div>
				</div>
			</div>
			<div class="col-sm-10 mx-auto mt-3"
				style="border-top: solid 1px black"></div>
		</div>


	</c:forEach>

	<!-- Store drink END -->




	<!-- 塑膠袋 START -->
	<div class="container-fluid">
		<div class="row" id="categoryBody12">
			<div class="col-sm-10 mx-auto">
				<p style="font: bold 25px Tahoma; margin-top: 30px">塑膠袋</p>
				<div class="row">
					<!-- card1 -->
					<div class="col-9 col-md-4">
						<div class="card mb-3">
							<div class="row p-3">
								<h5 class="fw-bold addButton">塑膠袋</h5>
								<h6>
									一起環保愛地球，請盡量自備袋子。<br />$1
								</h6>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 塑膠袋 END -->

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
										<!-- 										<input type="radio" class="btn-check" name="ice" id="ice1" -->
										<!-- 											autocomplete="off" checked /> <label -->
										<!-- 											class="btn btn-outline-dark" for="ice1">完全去冰</label> <input -->
										<!-- 											type="radio" class="btn-check" name="ice" id="ice2" -->
										<!-- 											autocomplete="off" /> <label class="btn btn-outline-dark" -->
										<!-- 											for="ice2">去冰</label> <input type="radio" class="btn-check" -->
										<!-- 											name="ice" id="ice3" autocomplete="off" /> <label -->
										<!-- 											class="btn btn-outline-dark" for="ice3">微冰</label> <input -->
										<!-- 											type="radio" class="btn-check" name="ice" id="ice4" -->
										<!-- 											autocomplete="off" /> <label class="btn btn-outline-dark" -->
										<!-- 											for="ice4">少冰</label> <input type="radio" class="btn-check" -->
										<!-- 											name="ice" id="ice5" autocomplete="off" /> <label -->
										<!-- 											class="btn btn-outline-dark" for="ice5">正常冰</label> -->
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
										<!-- 										<input type="radio" class="btn-check" name="sweet" id="sweet1" -->
										<!-- 											autocomplete="off" checked /> <label -->
										<!-- 											class="btn btn-outline-dark" for="sweet1">無糖</label> <input -->
										<!-- 											type="radio" class="btn-check" name="sweet" id="sweet2" -->
										<!-- 											autocomplete="off" /> <label class="btn btn-outline-dark" -->
										<!-- 											for="sweet2">微糖</label> <input type="radio" class="btn-check" -->
										<!-- 											name="sweet" id="sweet3" autocomplete="off" /> <label -->
										<!-- 											class="btn btn-outline-dark" for="sweet3">半糖</label> <input -->
										<!-- 											type="radio" class="btn-check" name="sweet" id="sweet4" -->
										<!-- 											autocomplete="off" /> <label class="btn btn-outline-dark" -->
										<!-- 											for="sweet4">少糖</label> <input type="radio" class="btn-check" -->
										<!-- 											name="sweet" id="sweet5" autocomplete="off" /> <label -->
										<!-- 											class="btn btn-outline-dark" for="sweet5">全糖</label> -->
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
										<!-- 										<input type="checkbox" class="btn-check" name="addon" -->
										<!-- 											id="addon1" autocomplete="off" checked /> <label -->
										<!-- 											class="btn btn-outline-dark" for="addon1">珍珠+10</label> <input -->
										<!-- 											type="checkbox" class="btn-check" name="addon" id="addon2" -->
										<!-- 											autocomplete="off" /> <label class="btn btn-outline-dark" -->
										<!-- 											for="addon2">椰果+10</label> <input type="checkbox" -->
										<!-- 											class="btn-check" name="addon" id="addon3" autocomplete="off" /> -->
										<!-- 										<label class="btn btn-outline-dark" for="addon3">布丁+10</label> -->

										<!-- 										<input type="checkbox" class="btn-check" name="addon" -->
										<!-- 											id="addon4" autocomplete="off" /> <label -->
										<!-- 											class="btn btn-outline-dark" for="addon4">寒天+10</label> -->

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
									<textarea id="message" name="talk" cols="45" rows="3"
										placeholder="限制30字"></textarea>
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
									class="text_box" name="" type="text" id="quantity" value="1" readonly />
									<input class="add" name="" type="button" />
								</td>
							</tr>
						</table>
					</div>

					<button type="button" class="btn btn-dark" style="width: 300px;"
						id="submitbtn">完成</button>
				</div>
			</div>
		</div>
	</div>

	<!-- modify Modal END-->

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

<!--opening hours Modal start-->
<div class="modal fade" id="openinghoursModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="staticBackdropLabel">目前商家尚未營業</h5>
      </div>
      <div class="modal-body" id="openinghoursModalmodalbody" style="text-align: center;">
      </div>
      <div class="modal-footer">
        <a href="<c:url value='/_00_Index/index.jsp'/>"><button type="button" class="btn" style="background-color:#FCD8D4">去別家逛逛</button></a>
      </div>
    </div>
  </div>
</div>
<!--opening hours Modal end-->




	<!-- nocart Modal -->
	<div class="modal fade" id="NocartModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" style="margin-left: auto;">購物還空空的哦</h5>
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




	<!-- clear shopping Modal start-->
	<div class="modal fade" id="clearModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">啟動新購物車?</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body d-flex justify-content-center">

					<div>
						您的購物車已包含其他店家的飲品，是否清空購物車，<br>改成這項飲品?
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">取消</button>
					<button type="button" class="btn btn-dark" id="clearbtn">更新購物車</button>
				</div>


			</div>
		</div>
	</div>
	<!-- clear shopping Modal end-->

	<!-- review Modal -->
	<div class="modal fade" id="review" tabindex="-1">
		<div class="modal-dialog modal-dialog-scrollable">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title">關於此飲品的評價</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body" id="review_body"></div>
			</div>
		</div>
	</div>
	<!-- review Modal -->


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

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
		crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	 <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDx1N-wHeMdqX-ZieKXx2BeYnLz1drNa6U&callback=initMap&libraries=&v=weekly"
      async
    ></script>
	<script
		src="<c:url value="/_03_ListDrinks/c_03_storePage/StorePage1.js"/>"></script>

	<!-- 飲料名稱點擊選配料JS start-->
	<script>
      const addCartAlert = document.getElementById('addCartAlert');
      const addCartAlertModal = new bootstrap.Modal(addCartAlert, {keyboard: true});


	const clearModal = document.getElementById("clearModal");
	const modal3 = new bootstrap.Modal(clearModal, { keyboard: false });

							 
      let list = document.querySelectorAll(".addButton");
      for(let i = 0; i < list.length - 1; i++){
        list[i].addEventListener('click', (e) => {
        	console.log(e.target.previousElementSibling.value);
        	
						$.ajax({
							url: "http://localhost:8080/whattodrink/_03_ListDrinks/DrinkDetail",
							type: "POST",
							data: {productId:e.target.previousElementSibling.value},
							dataType:"json",
							success(res) {
								console.log(res);
								addon = document.getElementById('addon');
								  while (addon.hasChildNodes()) {
								    addon.removeChild(addon.firstChild);
								  }
								  icebtn = document.getElementById('icebtn');
								  while (icebtn.hasChildNodes()) {
								    icebtn.removeChild(icebtn.firstChild);
								  }

								  sweetbtn = document.getElementById('sweetbtn');
								  while (sweetbtn.hasChildNodes()) {
								    sweetbtn.removeChild(sweetbtn.firstChild);
								  }
								
								  $('#note').val("");
									$('#message').val("");
								  
								  $('#quantity').val(1);

								$('#productname').text(res.drinkNameAndCapacity[0]+res.drinkNameAndCapacity[1]);
								
								$('#productId').val(e.target.previousElementSibling.value);
								
								 let message1 = '';
							      for (let i = 0; i < res.toppingIdJson.length; i++) {
							        message1 += `<input type="checkbox" class="btn-check" name="addon"  value="\${res.toppingIdJson[i]}" id="addon\${i}" autocomplete="off" />
							      <label class="btn btn-outline-dark" for="addon\${i}">`;
							        message1 += res.toppingNameJson[i];
							        message1 += `+`;
							        message1 += res.toppingPriceJson[i];
							        message1 += `</label>`;
							      }



								
								let message2 = '';
							      for (let i = 0; i <res.tempIdJson.length; i++) {
							        message2 += `<input type="radio" class="btn-check" name="ice" value="\${res.tempIdJson[i]}" id="ice\${i}" autocomplete="off"/>
							    <label class="btn btn-outline-dark" for="ice\${i}">`;
							        message2 += res.tempNameJson[i];
							        message2 += `</label>`;
							      }


							      let message3 = '';
							      for (let i = 0; i < res.sugarIdJson.length; i++) {
							        message3 += `<input type="radio" class="btn-check" name="sweet" value="\${res.sugarIdJson[i]}" id="sweet\${i}" autocomplete="off"/>
							        <label class="btn btn-outline-dark" for="sweet\${i}">`;
							        message3 += res.sugarNameJson[i];
							        message3 += `</label>`;
							      }

							      $('#sweetbtn').append(message3);
							      $('#icebtn').append(message2);
							      $('#addon').append(message1);
							    
								  $("input[name=ice]")[0].checked = true;
								  $("input[name=sweet]")[0].checked = true;
							 }
						});
            
            
          addCartAlertModal.show();
        });
      };



	  $(function () {
  $(".add").click(function () {
    var t = $(this).parent().find("input[class*=text_box]");
    t.val(parseInt(t.val()) + 1);
  });
  $(".min").click(function () {
    var t = $(this).parent().find("input[class*=text_box]");
    t.val(parseInt(t.val()) - 1);
    if (parseInt(t.val()) < 1) {
      t.val(1);
    }
  });
});





      $("#submitbtn").click(function () {

		addCartAlertModal.hide();
    	selected=[];
          $("[name=addon]:checkbox:checked").each(function(){
            selected.push($(this).val());
            });

    	  $.ajax({
				url: "http://localhost:8080/whattodrink/_04_ShoppingCart/AddToCartServlet",
				type: "POST",
				data: {
					productId:$('#productId').val(),
					tempId:$('[name=ice]:checked').val(),
					sugarId:$('[name=sweet]:checked').val(),
					addon:JSON.stringify(selected),
					note:$('#note').val(),
					message:$('#message').val(),
					quantity:$('#quantity').val()
					},
				dataType:"json",
				success(res) {
						//alert('成功傳向後端!');
						console.log(res);
						 if(res=="differentCompany"){
							
						 	
						 	addCartAlertModal.hide();
						 	modal3.show();
						 	}


						 }
				});



          });


      

    
	<!-- 飲料名稱點擊選配料JS  end-->
	
	<!--清空購物車start-->
		$("#clearbtn").click(function () {
			modal3.hide();
			$.ajax({
				url: "http://localhost:8080/whattodrink/_04_ShoppingCart/deleteCartServlet",
				type: "POST",
				data:{
					productId:$('#productId').val(),
					tempId:$('[name=ice]:checked').val(),
					sugarId:$('[name=sweet]:checked').val(),
					addon:JSON.stringify(selected),
					note:$('#note').val(),
					message:$('#message').val(),
					quantity:$('#quantity').val()
					},
				dataType:"json",
				success(res) {
					alert('更換成功!');

						}  
						});
				
		})

	<!--清空購物車end-->
</script>
	<!-- dynamic menu start -->
	<script>
      const menu = document.getElementById('menu');
      const menuBox = menu.getBoundingClientRect();
      
      function shouldHideMenu() {
        const categorySection = document.getElementById('categories-section');
        const categorySectionBox = categorySection.getBoundingClientRect();
        return menuBox.height / 2 > categorySectionBox.y;
      }

      const elements = [
        '.left-menu',
        '#menuright',
        '.hide7',
        '.show7',
        '#openbar',
      ];

      function hideMenu() {
        elements.forEach(ele => $(ele).addClass('special-hidden'));
        $('#special-cartIcon').fadeIn(10);
      }

      function showMenu() {
        elements.forEach(ele => $(ele).removeClass('special-hidden'));
        $('#special-cartIcon').fadeOut(10);
      }

      const titles = document.querySelectorAll('div p');
      function categoryMark(){
        let targetHeight = titles[titles.length-1].getBoundingClientRect().y;        
        for(let i = 0; i < titles.length; i++){          
          amountOfEachTop = titles[i].getBoundingClientRect().y;
          if(amountOfEachTop <= menuBox.height + 20 && amountOfEachTop + $('#categoryBody' + i).outerHeight() >= menuBox.height +20){            
            $('#category' + i).css({"color":"#da5d0a", "border-bottom":"white 3px solid"});
          }else{
            $('#category' + i).css({"color":"black", "border-bottom":"transparent"});
          }
        }
        if($(this).scrollTop() >= $(document).height() - $(this).height() -100){
          let finalIndex =  titles.length - 1;              
          $('#category' + (finalIndex - 1)).css({"color":"black", "border-bottom":"transparent"});
          $('#category' + finalIndex).css({"color":"#da5d0a", "border-bottom":"#FCD8D4 3px solid", "background-color":"transparent"});
        }
      }
      $(window).scroll(function handleForScroll() {
        shouldHideMenu() ? hideMenu() : showMenu();
        categoryMark();
      });

      </script>
	<!-- dynamic menu end -->



	<!-- storeMaps start -->

	<script>
      const storeMaps = document.getElementById('storeMaps');
      const mapsModal = new bootstrap.Modal(storeMaps, {keyboard: true});
  
      const addressMapsLink = document.getElementById('addressMaps')
      addressMapsLink.addEventListener('click', function (event) {
        mapsModal.show();
      });
      
      
    </script>
	<!-- storeMaps end -->


	<!-- 點擊購物車判斷&跳轉 -->
	<script>

	
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

}	


	//自然搜尋
	
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
</script>

	<script type="text/javascript">
let ee="${favoritecompany}";
let ee1 = ee.split(',');

for(i=0;i<ee1.length;i++){
	console.log(ee1[i]);
$(`#\${ee1[i]}`).attr("class","fas fa-heart fa-lg");
}
</script>

	<script>
// 點星星跳出評論
const review = document.getElementById("review");
const reviewmodal = new bootstrap.Modal(review, { keyboard: false });

$(".comment").click(function (e) {
	
	$('#review_body').empty();
	
	
	$.ajax({
	    url: "http://localhost:8080/whattodrink/DrinkForCompanyFromStarServlet",
	    type: "POST",
		data: {product_id:e.target.parentElement.parentElement.firstElementChild.firstElementChild.value},
		dataType:"json",
	    success(res) {
		console.log(res);
			let str=""
		
	for(i=0;i<res.length;i++){
		
		let star=""
			for(j=0;j<parseInt(res[i].drinkStar);j++){
				star+=`<i class="fas fa-star"></i>`; 
				}
		
		console.log(star);
		str+=`<div class="row">
		<div class="col-sm-10 mx-auto">
		<div class="row mb-3">
			<div class="col-sm-6">
				<img class="ratio ratio-4x3"
					src="\${res[i].drinkPicpath!=undefined?`http://localhost:8080/whattodrink/\${res[i].drinkPicpath}`:`http://localhost:8080/whattodrink/images/none.png`}"
					style="max-width: fit-content" />
			</div>
			<div class="col-sm-6">
				<p class="fw-bold fs-5 m-0">\${res[i].drinkName}</p>
				<div class="rating_stars my-1"><p class="text-warning text-decoration-none">\${star}</p></div>
				<p class="m-0">\${res[i].drinkComment!=undefined?res[i].drinkComment:''}</p>
			</div>
		</div>
	</div>
</div>`;
		
	}
		
		$('#review_body').append(str);
	
	
		
					}
		});
	
	
	
  reviewmodal.show();
});
</script>


<!-- 判斷是否為營業時間 -->
<script type="text/javascript">
function checkAuditTime(beginTime,endTime) {
	let nowDate=new Date();
	let beginDate=new Date(nowDate);
	let endDate=new Date(nowDate);
	
	let beginIndex=beginTime.lastIndexOf("\:");
	let beginHour =beginTime.substring(0,beginIndex);
	let beginMinute=beginTime.substring(beginIndex+1,beginIndex.length);
	
	beginDate.setHours(beginHour,beginMinute,0,0);
	
	
	let endIndex=endTime.lastIndexOf("\:");
	let endHour =endTime.substring(0,endIndex);
	let endMinute=endTime.substring(endIndex+1,endIndex.length);
	
	endDate.setHours(endHour,endMinute,0,0);
	
	if(nowDate.getTime()-beginDate.getTime()>=0&&nowDate.getTime()<=endDate.getTime()){
		return true;
	}else{
		return false;
	}
		
		
	}
	
const openinghoursModal = document.getElementById("openinghoursModal");
const OpeninghoursModal = new bootstrap.Modal(openinghoursModal, { keyboard: false });

let startTime=$('input[name="start_time"]').val();	
let endTime=$('input[name="end_time"]').val();	

if(checkAuditTime(startTime,endTime)==false){
	$('#openinghoursModalmodalbody').empty();
	$('#openinghoursModalmodalbody').text(`營業時間為\${startTime}~\${endTime}`);
	OpeninghoursModal.show();
}

</script>
<!-- 判斷空飲料分類 -->
<script type="text/javascript">

$('.drink').each(function () {
	console.log($.trim($(this).text()).length);
    if($.trim($(this).text()).length==0){
    	$(this).append(`<div class="col-9 col-md-4">
    						<div class="card mb-3">
    							<div class="row p-3">
    								<h5 class="fw-bold" style="color:#da5d0a;">待商家新增中</h5>
    								<h6>
    									此分類目前尚無飲品。
    								</h6>
    							</div>
    						</div>
    					</div>`);
    }
});

</script>


</body>
</html>
