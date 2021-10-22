<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>新增配料</title>
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
    <link rel="stylesheet" href="<c:url value="/_06_Maintain/b_06_maintain/style.css"/>" />
  </head>
  <body>
     <!-- header start -->
   <nav class="navbar navbar-expand-lg">
      <div class="col-sm-1"></div>
      <div class="container-fluid p-0">
        <a
          class="navbar-link text-dark p-0"
          data-bs-toggle="offcanvas"
          href="#hamburgerMenu"
          ><i class="fas fa-bars"></i
        ></a>
        <a class="navbar-link d-flex justify-content-center" href="#"
          > <img src="<c:url value="/images/logo.png"/>" alt="logo" /></a>
        <a class="nav-link text-dark p-0" href="<c:url value="/_02_Login/b_02_login/1_business_login.jsp"/>"
          ><i class="fas fa-sign-out-alt"></i
        ></a>
      </div>
      <div class="col-sm-1"></div>
    </nav>
    <!-- header end -->

    <!-- content start -->
    <div class="container-fluid" style="min-height: 80vh">
      <div class="row p-0">
        <div class="col-sm-10 mx-auto">
          <p class="fs-4 mt-5 fw-bold">新增配料</p>
        </div>
      </div>
      <!-- upload pic -->
      <div class="row p-0">
        <div class="col-sm-1"></div>
        <div class="col-sm-10">
          <p class="fs-6">配料圖上傳</p>
        </div>
        <div class="col-sm-1"></div>
      </div>
      <form action="http://localhost:8080/whattodrink/AddAddOnServlet" method="POST" enctype="multipart/form-data">
        <div class="p-0 mx-1 col-sm-10 mx-auto">
          <div
            class="p-0 bg-white col-sm-5"
            style="border: 1px solid; border-radius: 0.25rem; height: 20rem"
          >
            <img
              id="showimg"
              src=""
              style="display: none; max-width: 100%; max-height: 100%"
            />
          </div>
          <div class="col-sm-5 mt-2">
            <div class="input-group">
              <input
                accept="image/jpg,image/jpeg"
                type="file"
                class="form-control abc"
                name="topping_pic"
                id="topping"
                style="border: 0px"
              />
              <!-- <button class="input-group-text" type="submit" id="uploadImg">
                上傳
              </button> -->
            </div>
          </div>
        </div>

        <div class="col-sm-1"></div>

        <div class="row mt-3">
          <div class="col-sm-10 mx-auto d-flex align-items-center mb-3">
            <span class="fs-6 me-3">配料名稱</span>
            <input
              class="form-control form-control-sm"
              style="width: 250px"
              type="text"
              name="name"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-sm-10 mx-auto d-flex align-items-center mb-3">
            <span class="fs-6 me-3">配料金額</span>
            <input
              class="form-control form-control-sm"
              style="width: 100px"
              type="text"
              name="money"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-sm-10 mx-auto d-flex align-items-center mb-3">
            <span class="fs-6 me-3">配料熱量</span>
            <input
              class="form-control form-control-sm"
              style="width: 100px"
              type="text"
              name="cal"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-sm-1"></div>
          <div class="col-sm-5">
            <button type="submit" class="btn btn-dark my-2" id="upload">
              送出
            </button>
          </div>
          <div class="col-sm-1"></div>
        </div>
      </form>
    </div>
    <!-- content end -->

    <!-- footer start -->
    <footer id="footer">
      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-1"></div>
          <div class="footer-content logo col-sm-5 m-0">
            <a href=""><img src="<c:url value="/images/logo.png"/>" alt="logo" /></a>
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

    <!-- hamburger menu -->
    <div
      class="offcanvas offcanvas-start d-flex flex-column"
      id="hamburgerMenu"
      style="width: 300px; padding: 10px"
      tabindex="-1"
    >
      <div class="offcanvas-header">
        <button
          type="button"
          class="btn-close text-reset"
          data-bs-dismiss="offcanvas"
        ></button>
      </div>
      <div class="offcanvas-body lh-base">
        <div class="d-block" style="height: 70vh">
          <div>
            <i class="fas fa-user-circle mb-4" style="font-size: xxx-large"></i>
          </div>
          <h6 class="fw-normal">
            <a
              class="link-dark text-decoration-none"
              href="<c:url value="/_05_Order/b_05_order/1_business_orders.jsp"/>"
              >待處理訂單</a
            >
          </h6>
          <h6 class="fw-normal">
            <a
              class="link-dark text-decoration-none"
              href="<c:url value="/_06_Maintain/b_06_maintain/1_business_company_info.jsp"/>"
              >店家資訊</a
            >
          </h6>
          <h6 class="fw-normal">
            <a
              class="link-dark text-decoration-none"
              href="<c:url value="/_06_Maintain/b_06_maintain/1_business_beverages_list.jsp"/>"
              >飲品列表</a
            >
          </h6>
          <h6 class="fw-normal">
            <a
              class="link-dark text-decoration-none"
              href="<c:url value="/_06_Maintain/b_06_maintain/1_business_toppings_list.jsp"/>"
              >配料列表</a
            >
          </h6>
          <h6 class="fw-normal">
            <a
              class="link-dark text-decoration-none"
              href="<c:url value="/_06_Maintain/b_06_maintain/1_business_add_products.jsp"/>"
              >新增商品</a
            >
          </h6>
          <h6 class="fw-normal">
            <a
              class="link-dark text-decoration-none"
              href="<c:url value="/_06_Maintain/b_06_maintain/1_business_add_addOn.jsp"/>"
              >新增配料</a
            >
          </h6>
          <h6 class="fw-normal">
            <a
              class="link-dark text-decoration-none"
              href="<c:url value="/_06_Maintain/b_06_maintain/1_business_add_catalog.jsp"/>"
              >新增分類</a
            >
          </h6>
          <h6 class="fw-normal">
            <a
              class="link-dark text-decoration-none"
              href="<c:url value="/_07_Others/b_07_other/1_business_report.jsp"/>"
              >每日報表</a
            >
          </h6>
          <h6 class="fw-normal">
            <a
              class="link-dark text-decoration-none"
              href="<c:url value="/_05_Order/b_05_order/1_business_order_history.jsp"/>"
              >歷史訂單</a
            >
          </h6>
          <h6 class="fw-normal">
            <a
              class="link-secondary text-decoration-none"
              href="<c:url value="/_02_Login/b_02_login/1_business_login.jsp"/>"
              >登出</a
            >
          </h6>
        </div>
        <div class="d-flex flex-column">
          <a href="" class="logo"><img src="<c:url value="/images/logo.png"/>" alt="logo" /></a>
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

    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script src="<c:url value="/_06_Maintain/b_06_maintain/1_business_add_addOn.js"/>"></script>
  </body>
</html>
