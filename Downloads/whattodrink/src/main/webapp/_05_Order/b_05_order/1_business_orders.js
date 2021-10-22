$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/whattodrink/B_order",
    dataType: "json",
    success: function (res) {
      var str = "";
      for (let i = 1; i < res.length; i++) {
        var orderStatus = res[i].orderStatus;
        var order_id = res[i].order_id;
        str = "";
        str += `<tr class="hover">
        <td>
        <i
        class="fas fa-info-circle info-btns"
        role="button"
        id="${res[i].order_id}"
        >
        </td>
        <td>${res[i].order_id}</td>
        <td>${res[i].scheduled_time}</td>
        <td>${res[i].payment}</td>
        <td>${res[i].order_quantity}</td>
        <td>${res[i].order_total}</td>
        <td>
            <select class="form-select-sm " id="0${order_id}">
              <option>待確認</option>
              <option>接單</option>
              <option>取消</option>
            </select>
            <select class="form-select-sm d-none" id="1${order_id}">
              <option class="ss" disabled>待製作</option>
              <option class="ss1">可領取</option>
              <option>已領取</option>
            </select>
          </td>
          <td>${res[i].taxId}</td>
        </tr>`;
        tr2.insertAdjacentHTML("beforebegin", str);
        $(`#0${order_id}`).val(orderStatus);
        $(`#1${order_id}`).val(orderStatus);
        if ($(`#0${order_id}`).val() != "待確認") {
          $(`#0${order_id}`).addClass("d-none");
          $(`#1${order_id}`).removeClass("d-none");
          $("select:last-child").change(function (e) {
            if ($(e.target).val() == "已領取") {
              e.target.parentElement.parentElement.remove();
            }
          });
        }
      }

      //偵測第一個select
      $("select").change(function (e) {
        if ($(e.target).val() == "接單") {
          $(e.target).addClass("d-none");
          $(e.target.nextElementSibling).removeClass("d-none");
          $(e.target.nextElementSibling.firstElementChild).attr(
            "selected",
            "selected"
          );
          console.log($(e.target).val());
          //偵測第二個select
          $("select:last-child").change(function (e) {
            if ($(e.target).val() == "已領取") {
              e.target.parentElement.parentElement.remove();
            }
          });
        } else if ($(e.target).val() == "取消") {
          e.target.parentElement.parentElement.remove();
        }
        var ordState = $(e.target).val();
        if (ordState == "接單") {
          ordState = "待製作";
        }
        var ordId = e.target.id.substring(1, e.target.id.length);
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/whattodrink/B_orderStatusChange",
          data: {
            orderStatus: ordState,
            order_id: ordId,
          },
          success: function (response) {},
        });
      });
      // 開啟modal
      const modifyModal = document.getElementById("alter");
      const modal = new bootstrap.Modal(modifyModal, { keyboard: false });
      $(".info-btns").click(function (e) {
        ordId = e.target.id;
        //請求該訂單資料
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/whattodrink/B_orderClickItem",
          data: { order_id: ordId },
          dataType: "json",
          success: function (response) {
            console.log(response);
            var str2 = "";
            for (var j = 0; j < response.length; j++) {
              var quantity = response[j].quantity;
              var itemName = response[j].product_name;
              var capacity = response[j].capacity;
              var temp = response[j].temp_level;
              var sugar = response[j].sugar_level;
              var toppings = response[j].toppings;
              var mes = response[j].message;
              var memo = response[j].note;
              str2 += `
                  <tr class="tr">
                  <td>${quantity}</td>
                  <td>${itemName}</td>
                  <td>${capacity}</td>
                  <td>${temp}</td>
                  <td>${sugar}</td>
                  <td>${toppings}</td>
                  <td>${mes}</td>
                  <td>${memo}</td>
                  </tr>
                  `;
            }
            tr_modal.insertAdjacentHTML("afterend", str2);
          },
        });
        modal.show();
        $("[data-bs-dismiss]").click(function () {
          $(".tr").remove();
        });
      });
    },
  });
});
