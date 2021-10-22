$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/whattodrink/B_item",
    dataType: "json",
    success: function (response) {
      console.log(response);
      renderHTML(response);
    },
  });
});
function renderHTML(data) {
  var str = "";
  for (let i = 0; i < data.length; i++) {
    var itemId = data[i].item_id;
    console.log(itemId);
    var ordId = data[i].order_id;
    var itemName = data[i].product_name;
    var capacity = data[i].capacity;
    var temp = data[i].temp_level;
    var sugar = data[i].sugar_level;
    var toppings = data[i].toppings;
    var memo = data[i].note;
    var sticker = data[i].message;
    var quantity = data[i].quantity;
    var itemStatus = data[i].itemStatus;
    var str = "";
    str += `
    <tr class="hover">
    <td>${ordId}</td>
    <td>${itemName}</td>
    <td>${capacity}</td>
    <td>${temp}</td>
    <td>${sugar}</td>
    <td>${toppings}</td>
    <td>
      <select class="form-select-sm" id="${itemId}">
        <option selected disabled >待製作</option>
        <option class="ss">製作中</option>
        <option >已完成</option>
      </select>
    </td>
    <td>${quantity}</td>
    <td>${memo}</td>
    <td>${sticker}</td>
    </tr>
    `;
    tr2.insertAdjacentHTML("beforebegin", str);
    $(`#${itemId}`).val(itemStatus);
    console.log($(`#${itemId}`).val());
    if ($(`#${itemId}`).val() == "待製作") {
      $(`#${itemId}`).val().attr("selected", "selected");
    }
  }

  $("select").change(function (e) {
    if ($(e.target).val() == "已完成") {
      e.target.parentElement.parentElement.remove();
      ordId2 = e.target.id;
    }
    console.log($(e.target).val());
    console.log(e.target.id);
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/whattodrink/B_itemStatusChange",
      data: { itemStatus: $(e.target).val(), item_id: e.target.id },
      success: function (response) {
//		conlose.log(response);
	  },
    });
  });
}
