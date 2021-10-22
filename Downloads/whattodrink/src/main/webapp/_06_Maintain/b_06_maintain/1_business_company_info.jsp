<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>公司資訊</title>
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
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.css"
    />
   <style>
      .table {
        vertical-align: middle;
      }
      tbody {
        border-color: black;
      }
      .abc {
        border: 0px;
      }
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
        <div class="col-sm-10 d-flex mx-auto">
          <p class="fs-4 mt-5 fw-bold">店家資訊</p>
        </div>
        <div class="table-responsive col-sm-10 d-flex mx-auto">
          <form id="storeInfo">
            <table class="table">
              <thead>
                <tr>
                  <th style="width: 15%">店家名稱</th>
                  <th style="width: 44%">店家地址</th>
                  <th style="width: 13%">起始時間</th>
                  <th style="width: 13%">結束時間</th>
                  <th style="width: 15%">店家電話</th>
                  <th><i class="far fa-edit"></i></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>
                    <input
                      class="form-control"
                      type="text"
                      id="storeName"
                      placeholder="XX店"
                      name="store"
                    />
                  </td>
                  <td>
                    <input
                      class="form-control"
                      type="text"
                      id="storeAddress"
                      placeholder="地址"
                      name="address"
                    />
                  </td>
                  <td>
                    <input
                      class="form-control timepicker1"
                      id="startTime"
                      placeholder="11:00:00"
                      name="startTime"
                    />
                  </td>
                  <td>
                    <input
                      class="form-control timepicker2"
                      id="endTime"
                      placeholder="22:00:00"
                      name="endTime"
                    />
                  </td>
                  <td>
                    <input
                      type="tel"
                      maxlength="10"
                      pattern="\d{2}\d{8}"
                      class="form-control"
                      id="storeTel"
                      placeholder="02XXXXXXXX"
                      name="storeTel"
                    />
                  </td>
                  <td>
                    <!-- src="<c:url value="/images/check.png"/>" -->
                    <input
                      type="image"
                      src="<c:url value="/images/check.png"/>"
                      style="height: 3vh"
                      id="check1"
                    />
                  </td>
                </tr>
              </tbody>
            </table>
          </form>
        </div>
      </div>
      <div class="row p-0">
        <div class="col-sm-10 d-flex mx-auto">
          <p class="fs-4 mt-5 fw-bold">企業資訊</p>
        </div>
        <div class="table-responsive col-sm-10 d-flex mx-auto">
          <table class="table">
            <thead>
              <tr>
                <th style="width: 28%">負責人</th>
                <th style="width: 42%">公司/行號</th>
                <th style="width: 15%">統一編號</th>
                <th style="width: 15%">聯絡電話</th>
                <th><i class="far fa-edit"></i></th>
              </tr>
            </thead>
            <form>
              <tbody>
                <tr>
                  <td>
                    <input
                      type="text"
                      class="form-control"
                      id="person"
                      placeholder="XXX"
                    />
                  </td>
                  <td>
                    <input
                      type="text"
                      class="form-control"
                      id="num"
                      placeholder="XX股份有限公司/XX商行"
                    />
                  </td>
                  <td>
                    <input
                      type="number"
                      class="form-control"
                      id="tax_id_number"
                      oninput="if(value.length>8)value=value.slice(0,8)"
                      placeholder="12345678"
                    />
                  </td>
                  <td>
                    <input
                      type="tel"
                      class="form-control"
                      maxlength="10"
                      pattern="09\d{2}\d{6}"
                      id="tel"
                      placeholder="09XXXXXXXX"
                    />
                  </td>
                  <td>
                    <input
                      type="image"
                      src="<c:url value="/images/check.png"/>"
                      style="height: 3vh"
                      id="check2"
                    />
                  </td>
                </tr>
              </tbody>
            </form>
          </table>
        </div>
      </div>
      <div class="row p-0">
        <div class="col-sm-10 d-flex mx-auto">
          <p class="fs-4 mt-5 fw-bold">店家縮圖上傳</p>
        </div>
      </div>
      <form
        action="http://localhost:8080/whattodrink/BusinessCompanyInfoServlet?type=3"
        method="POST"
        enctype="multipart/form-data"
      >
        <div class="p-0 mx-1 col-sm-10 mx-auto">
          <div
            class="p-0 bg-white col-sm-5"
            style="border: 1px solid; border-radius: 0.25rem; height: 20rem"
            id="img1"
          >
            <img
              id="showimg1"
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
                id="jpg1"
                name="pic1"
              />
              <button class="input-group-text" type="submit" id="upload1">
                上傳
              </button>
            </div>
          </div>
        </div>
      </form>
      <div class="row p-0">
        <div class="col-sm-10 d-flex mx-auto">
          <p class="fs-4 mt-5 fw-bold">店家底圖上傳</p>
        </div>
      </div>
      <form
        action="http://localhost:8080/whattodrink/BusinessCompanyInfoServlet?type=4"
        method="POST"
        enctype="multipart/form-data"
      >
        <div class="p-0 mx-1 col-sm-10 mx-auto">
          <div
            class="p-0 bg-white col-sm-5"
            style="border: 1px solid; border-radius: 0.25rem; height: 20rem"
            id="img2"
          >
            <img
              id="showimg2"
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
                name="pic2"
                id="jpg2"
              />
              <button class="input-group-text" type="submit" id="upload2">
                上傳
              </button>
            </div>
          </div>
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

	<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.3/jquery.validate.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/additional-methods.js"></script>
    <script
      src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
      integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
      crossorigin="anonymous"
    ></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/timepicker/1.3.5/jquery.timepicker.min.js"></script>
    <script src="<c:url value="/_06_Maintain/b_06_maintain/1_business_company_info.js"/>"></script>
  </body>
</html>
