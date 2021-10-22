$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/whattodrink/B_orderHistory",
    dataType: "json",
    success: function (response) {
      console.log(response);
      var str = "";
      for (let i = 0; i < response.length; i++) {
        str += `
        <tr class="hover">
        <td>${response[i].order_id}</td>
        <td>${response[i].order_date}</td>
        <td>${response[i].payment}</td>
        <td>${response[i].order_quantity}</td>
        <td>${response[i].order_total}</td>
        <td>${response[i].orderStatus}</td>
        <td>
          <i
            class="fas fa-info-circle info-btns"
            role="button"
            id="${response[i].order_id}"
          ></i>
        </td>
        <td><i class="delete fas fa-trash" role="button"></i></td>
        </tr>`;
      }
      tr.insertAdjacentHTML("afterend", str);
      $(".delete").click(function (e) {
        console.log(e.target.parentElement.parentElement);
        e.target.parentElement.parentElement.remove();
        //刪除訂單
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/whattodrink/B_orderHistoryDelete",
          data: {
            order_id:
              e.target.parentElement.parentElement.firstElementChild.innerText,
          },
        });
      });
      // 開啟modal
      const modifyModal = document.getElementById("detail");
      const modal = new bootstrap.Modal(modifyModal, { keyboard: false });
      $(".fa-info-circle").click(function (e) {
        console.log(e.target.id);
        var ordId = e.target.id;
        //請求該配料資料
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/whattodrink/B_orderClickItem",
          data: { order_id: ordId },
          dataType: "json",
          success: function (res) {
            console.log(res);
            let str3 = "";
            for (let i = 0; i < res.length; i++) {
              var itemName = res[i].product_name;
              var capacity = res[i].capacity;
              var temp = res[i].temp_level;
              var sugar = res[i].sugar_level;
              var toppings = res[i].toppings;
              var quantity = res[i].quantity;
              var memo = res[i].note;
              var sticker = res[i].message;
              str3 += `
                  <tr class="tr">
                  <td>${itemName}</td>
                  <td>${capacity}</td>
                  <td>${temp}</td>
                  <td>${sugar}</td>
                  <td>${toppings}</td>
                  <td>${quantity}</td>
                  <td>${memo}</td>
                  <td>${sticker}</td>
                  </tr>
                  `;
            }
            tr_modal.insertAdjacentHTML("afterend", str3);
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
