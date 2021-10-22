$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/whattodrink/DeleteProductServlet",
    dataType: "json",
    success: function (response) {
      // console.log(response);
      renderHTML(response);
    },
  });
});


function getRandom(min,max){
    return Math.floor(Math.random()*(max-min+1))+min;
};


function renderHTML(data) {
	console.log(data);
  var str = "";
  for (let i = 0; i < 12; i++) {
    str += `
        <div class="col-sm-4" >
          <div class="card mb-3">
            <div class="row">
              <div class="col-md-6">
                <img
                  class="ratio ratio-4x3"
                  src="http://localhost:8080/whattodrink/${data[i].picPath}?yui=${getRandom(0,1000)}"
                  />
              </div>
              <div class="col-md-6">
                <div class="card-body d-flex flex-column">
                  <div class="mb-4">
                  <h5 class="card-title">${data[i].proName}\ ${data[i].proCap}</h5>
                  </div>
                  <div
                    class="d-flex"
                    style="justify-content: space-between"
                  ><span class="d-none">${data[i].proId}</span>
                    <i
                      class="fas fa-edit"
                      role="button"
                      id="${data[i].proId}"
                    ></i>
                    <i class="fas fa-trash" role="button"></i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      `;
  }
  $("#card").append(str);
  //刪除品項
  $(".fa-trash").click(function (e) {
    e.preventDefault();
    // console.log(e.target.parentElement.parentElement.parentElement.parentElement.parentElement);-->card
    e.target.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.remove();
    console.log(
      e.target.previousElementSibling.previousElementSibling.innerText
    );

    //傳送被刪除的配料id
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/whattodrink/DeleteProductServlet",
      data: {
        proId: e.target.previousElementSibling.previousElementSibling.innerText,
      },
      success: function (res) {
        if (res == "yes") {
          window.location.assign(
            "http://localhost:8080/whattodrink/_06_Maintain/b_06_maintain/1_business_beverages_list.jsp"
          );
        }
      },
    });
  });

  //顯示更多
  $("#more").click(function () {
    var str2 = "";
    for (let i = 12; i < data.length; i++) {
      str2 += `
        <div class="col-sm-4" >
          <div class="card mb-3">
            <div class="row">
              <div class="col-md-6">
                <img
                  class="ratio ratio-4x3"
                  src="http://localhost:8080/whattodrink/${data[i].picPath}?yui=${getRandom(0,1000)}"
                  />
              </div>
              <div class="col-md-6">
                <div class="card-body d-flex flex-column">
                  <div class="mb-4">
                  <h5 class="card-title">${data[i].proName}\ ${data[i].proCap}</h5>
                  </div>
                  <div
                    class="d-flex"
                    style="justify-content: space-between"
                  >
                    <i
                      class="fas fa-edit data2"
                      role="button"
                      id="${data[i].proId}"
                    ></i>
                    <i class="fas fa-trash" role="button"></i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      `;
    }
    $("#card").append(str2);
    $("#more").hide();

    // 開啟modal
    const modal = new bootstrap.Modal($("#alter"), { keyboard: false });
    $(".data2").click(function (e) {
      var proId = e.target.id;
      console.log(proId);
      //請求該筆資料
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/whattodrink/UpdateProductServlet",
        data: { proId: proId },
        cache: false,
        success: function (response) {
          console.log(response);
          var str3 = "";
          str3 = `
          <div class="d-flex align-items-center mb-3">
                <span class="fs-6 me-3">飲品圖片</span>
                <div style="width: 300px">
                  <div>
                    <div class="input-group">
                      <input
                        accept="image/jpg,image/jpeg"
                        type="file"
                        class="form-control abc"
                        name="proImg"
                        id="topping"
                        style="border: 0px"
                      />
                    </div>
                  </div>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="fs-6 me-3">飲品名稱</span>
                <input
                  class="form-control form-control-sm"
                  style="width: 300px"
                  type="text"
                  name="pro_name"
                  id="proName"
                />
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="fs-6 me-3">飲品金額</span>
                <input
                  class="form-control form-control-sm me-3"
                  style="width: 100px"
                  type="number"
                  name="proPrice"
                  id="proPrice"
                />
                <span class="fs-6 me-3">飲品熱量</span>
                <input
                  class="form-control form-control-sm"
                  style="width: 100px"
                  type="number"
                  name="proCal"
                  id="proCal"
                />
              </div>
              <div class="d-flex align-items-center mb-3">
                <span>Hashtag (至多2個，可以不選)</span>
              </div>
              <div class="d-flex align-items-center mb-3">
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="紅茶"
                  />
                  <label class="form-check-label">紅茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="綠茶"
                  />
                  <label class="form-check-label">綠茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="奶茶"
                  />
                  <label class="form-check-label">奶茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="青茶"
                  />
                  <label class="form-check-label">青茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="烏龍茶"
                  />
                  <label class="form-check-label">烏龍茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="鮮奶茶"
                  />
                  <label class="form-check-label">鮮奶茶</label>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="抹茶"
                  />
                  <label class="form-check-label">抹茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="可可"
                  />
                  <label class="form-check-label">可可</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="冰沙"
                  />
                  <label class="form-check-label">冰沙</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="冬瓜茶"
                  />
                  <label class="form-check-label">冬瓜茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="水果茶"
                  />
                  <label class="form-check-label">水果茶</label>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input me-1"
                    type="checkbox"
                    name="hashtag"
                    id="customized"
                  />
                  <input
                    class="form-control form-control-sm"
                    type="text"
                    placeholder="自訂"
                    name="customized"
                    disabled
                  />
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="me-5">甜度</span>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="1"
                  />
                  <label class="form-check-label">無糖</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="2"
                  />
                  <label class="form-check-label">微糖</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="3"
                  />
                  <label class="form-check-label">半糖</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="4"
                  />
                  <label class="form-check-label">少糖</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="5"
                  />
                  <label class="form-check-label">全糖</label>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="me-5">溫度</span>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="temp"
                    value="冰"
                  />
                  <label class="form-check-label">冰</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="temp"
                    value="熱"
                  />
                  <label class="form-check-label">熱</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="temp"
                    value="皆可"
                  />
                  <label class="form-check-label">皆可</label>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="me-3">系列分類</span>
                <select
                  class="form-select"
                  style="max-width: 30%"
                  name="catagories"
                  id="catalog"
                ></select>
              </div>
              <div class="modal-footer justify-content-center">
                <input class="d-none" id="proId" name="proId" value="${response.proId}"/>
                <button
                  type="submit"
                  class="btn btn-dark"
                  data-bs-dismiss="modal"
                >
                  送出
                </button>
              </div>`;
          $("#form").append(str3);
          //圖片格式驗證
          $("[name=proImg]").change(function () {
            var img = $("[name=proImg]").get(0).files[0].name;
            var idx = img.lastIndexOf(".");
            var lastname = img.substring(idx, img.length);
            var name = lastname.toLowerCase();
            if (name != ".jpg" && name != ".jpeg") {
              alert("圖片格式錯誤，請重新選擇");
              $("[name=proImg]").val("");
            } else {
              $("[name=proImg]").attr(
                "src",
                URL.createObjectURL($(this).get(0).files[0])
              );
            }
          });
          $("#proName").val(`${response.proName}`);
          $("#proPrice").val(`${response.proPrice}`);
          $("#proCal").val(`${response.proCal}`);
          //hashtag:checked
          var hashtag = response.hashtag;
          hashtag.forEach(function (item) {
            $("input[name=hashtag]").each(function () {
              if ($(this).val() == item) {
                $(this).attr("checked", true);
              }
            });
          });
          //hashtag <= 2
          $(":input[name=hashtag]").click(function (e) {
            $("input[name=hashtag]").attr("disabled", false);
            $("input[name=customized]").attr("disabled", true);
            if ($("input[name=hashtag]:checked").length >= 2) {
              $("input[name=hashtag]").attr("disabled", true);
              $("input[name=hashtag]:checked").attr("disabled", false);
            }
            if ($("input[id=customized]").is(":checked") == false) {
              $("input[name=customized]").attr("disabled", true);
            } else {
              $("input[name=customized]").attr("disabled", false);
            }
          });
          //sugar:checked
          var sugar = response.sugar;
          sugar.forEach(function (item) {
            $("input[name=sugar]").each(function () {
              if ($(this).val() == item) {
                $(this).attr("checked", true);
              }
            });
          });
          //temp:checked
          $("input[type=radio]").each(function () {
            if ($(this).val() == `${response.proTemp}`) {
              $(this).attr("checked", true);
            }
          });
          //catalog:selected
          var text = "";
          for (let j = 0; j < response.catalog.length; j++) {
            text += `<option value="${response.catalog3[j]}">${response.catalog[j]}</option>`;
          }
          $("#catalog").append(text);
          $("#catalog").val(`${response.catalog4}`);
          if ($("[name=catagories]").val() == `${response.catalog4}`) {
            $(this).attr("selected", true);
          }
        },
      });
      // $("#proId").attr("value", proId);
      modal.show();
      $("[data-bs-dismiss]").click(function () {
        $(".tr").empty();
      });
    });

    //刪除品項
    $(".fa-trash").click(function (e) {
      //e.preventDefault();
      e.target.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.remove();
      //傳送被刪除的配料id
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/whattodrink/DeleteProductServlet",
        data: {
          proId: e.target.previousElementSibling.id,
        },
        success: function (res) {
          if (res == "yes") {
            window.location.assign(
              "http://localhost:8080/whattodrink/_06_Maintain/b_06_maintain/1_business_beverages_list.jsp"
            );
          }
        },
      });
    });
  });
  // 開啟modal
  const modal = new bootstrap.Modal($("#alter"), { keyboard: false });
  $(".fa-edit").click(function (e) {
    var proId = e.target.id;
    console.log(proId);
    //請求該筆資料
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/whattodrink/UpdateProductServlet",
      data: { proId: proId },
      cache: false,
      success: function (response) {
        console.log(response);
        var str3 = "";
        str3 = `
          <div class="d-flex align-items-center mb-3">
                <span class="fs-6 me-3">飲品圖片</span>
                <div style="width: 300px">
                  <div>
                    <div class="input-group">
                      <input
                        accept="image/jpg,image/jpeg"
                        type="file"
                        class="form-control abc"
                        name="proImg"
                        id="topping"
                        style="border: 0px"
                      />
                    </div>
                  </div>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="fs-6 me-3">飲品名稱</span>
                <input
                  class="form-control form-control-sm"
                  style="width: 300px"
                  type="text"
                  name="pro_name"
                  id="proName"
                />
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="fs-6 me-3">飲品金額</span>
                <input
                  class="form-control form-control-sm me-3"
                  style="width: 100px"
                  type="number"
                  name="proPrice"
                  id="proPrice"
                />
                <span class="fs-6 me-3">飲品熱量</span>
                <input
                  class="form-control form-control-sm"
                  style="width: 100px"
                  type="number"
                  name="proCal"
                  id="proCal"
                />
              </div>
              <div class="d-flex align-items-center mb-3">
                <span>Hashtag (至多2個，可以不選)</span>
              </div>
              <div class="d-flex align-items-center mb-3">
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="紅茶"
                  />
                  <label class="form-check-label">紅茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="綠茶"
                  />
                  <label class="form-check-label">綠茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="奶茶"
                  />
                  <label class="form-check-label">奶茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="青茶"
                  />
                  <label class="form-check-label">青茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="烏龍茶"
                  />
                  <label class="form-check-label">烏龍茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="鮮奶茶"
                  />
                  <label class="form-check-label">鮮奶茶</label>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="抹茶"
                  />
                  <label class="form-check-label">抹茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="可可"
                  />
                  <label class="form-check-label">可可</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="冰沙"
                  />
                  <label class="form-check-label">冰沙</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="冬瓜茶"
                  />
                  <label class="form-check-label">冬瓜茶</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="hashtag"
                    value="水果茶"
                  />
                  <label class="form-check-label">水果茶</label>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input me-1"
                    type="checkbox"
                    name="hashtag"
                    id="customized"
                  />
                  <input
                    class="form-control form-control-sm"
                    type="text"
                    placeholder="自訂"
                    name="customized"
                    disabled
                  />
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="me-5">甜度</span>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="1"
                  />
                  <label class="form-check-label">無糖</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="2"
                  />
                  <label class="form-check-label">微糖</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="3"
                  />
                  <label class="form-check-label">半糖</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="4"
                  />
                  <label class="form-check-label">少糖</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="checkbox"
                    name="sugar"
                    value="5"
                  />
                  <label class="form-check-label">全糖</label>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="me-5">溫度</span>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="temp"
                    value="冰"
                  />
                  <label class="form-check-label">冰</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="temp"
                    value="熱"
                  />
                  <label class="form-check-label">熱</label>
                </div>
                <div class="form-check form-check-inline">
                  <input
                    class="form-check-input"
                    type="radio"
                    name="temp"
                    value="皆可"
                  />
                  <label class="form-check-label">皆可</label>
                </div>
              </div>
              <div class="d-flex align-items-center mb-3">
                <span class="me-3">系列分類</span>
                <select
                  class="form-select"
                  style="max-width: 30%"
                  name="catagories"
                  id="catalog"
                ></select>
              </div>
              <div class="modal-footer justify-content-center">
                <input class="d-none" id="proId" name="proId" value="${response.proId}"/>
                <button
                  type="submit"
                  class="btn btn-dark"
                  data-bs-dismiss="modal"
                >
                  送出
                </button>
              </div>`;
        $("#form").append(str3);
        //圖片格式驗證
        $("[name=proImg]").change(function () {
          var img = $("[name=proImg]").get(0).files[0].name;
          var idx = img.lastIndexOf(".");
          var lastname = img.substring(idx, img.length);
          var name = lastname.toLowerCase();
          if (name != ".jpg" && name != ".jpeg") {
            alert("圖片格式錯誤，請重新選擇");
            $("[name=proImg]").val("");
          } else {
            $("[name=proImg]").attr(
              "src",
              URL.createObjectURL($(this).get(0).files[0])
            );
          }
        });
        $("#proName").val(`${response.proName}`);
        $("#proPrice").val(`${response.proPrice}`);
        $("#proCal").val(`${response.proCal}`);
        //hashtag:checked
        var hashtag = response.hashtag;
        hashtag.forEach(function (item) {
          $("input[name=hashtag]").each(function () {
            if ($(this).val() == item) {
              $(this).attr("checked", true);
            }
          });
        });
        //hashtag <= 2
        $(":input[name=hashtag]").click(function (e) {
          $("input[name=hashtag]").attr("disabled", false);
          $("input[name=customized]").attr("disabled", true);
          if ($("input[name=hashtag]:checked").length >= 2) {
            $("input[name=hashtag]").attr("disabled", true);
            $("input[name=hashtag]:checked").attr("disabled", false);
          }
          if ($("input[id=customized]").is(":checked") == false) {
            $("input[name=customized]").attr("disabled", true);
          } else {
            $("input[name=customized]").attr("disabled", false);
          }
        });
        //sugar:checked
        var sugar = response.sugar;
        sugar.forEach(function (item) {
          $("input[name=sugar]").each(function () {
            if ($(this).val() == item) {
              $(this).attr("checked", true);
            }
          });
        });
        //temp:checked
        $("input[type=radio]").each(function () {
          if ($(this).val() == `${response.proTemp}`) {
            $(this).attr("checked", true);
          }
        });
        //catalog:selected
        var text = "";
        for (let j = 0; j < response.catalog.length; j++) {
          text += `<option value="${response.catalog3[j]}">${response.catalog[j]}</option>`;
        }
        $("#catalog").append(text);
        $("#catalog").val(`${response.catalog4}`);
        if ($("[name=catagories]").val() == `${response.catalog4}`) {
          $(this).attr("selected", true);
        }
      },
    });
    // $("#proId").attr("value", proId);
    modal.show();
    $("[data-bs-dismiss]").click(function () {
      $(".tr").empty();
    });
  });
}
