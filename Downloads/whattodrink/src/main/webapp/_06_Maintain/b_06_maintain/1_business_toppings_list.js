$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/whattodrink/DeleteToppingServlet",
    dataType: "json",
    success: function (response) {
	console.log(response);
      renderHTML(response);
    },
  });
});

function getRandom(min,max){
    return Math.floor(Math.random()*(max-min+1))+min;
};

function renderHTML(data) {
  var str = "";
  for (let i = 0; i < data.length; i++) {
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
                    <h5 class="card-title">${data[i].toppingName}</h5>
                  </div>
                  <div
                    class="d-flex"
                    style="justify-content: space-between"
                  ><span class="d-none">${data[i].toppingId}</span>
                    <i
                      class="fas fa-edit"
                      role="button"
                      id="${data[i].toppingId}"
                    ></i>
                    <i class="fas fa-trash" role="button" ></i>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      `;
  }
  $("#card").append(str);
console.log($("i"));
  //刪除品項
  $(".fa-trash").click(function (e) {
     e.preventDefault();
//alert();
    console.log(e.target.id);
    e.target.parentElement.parentElement.parentElement.parentElement.parentElement.parentElement.remove();
    //傳送被刪除的配料id
        $.ajax({
      type: "POST",
      url: "http://localhost:8080/whattodrink/DeleteToppingServlet",
      data: {
        toppingId: e.target.previousElementSibling.previousElementSibling.innerText,
      },
      success: function (res) {
	console.log(res);
        if (res == "yes") {
			window.location.assign("http://localhost:8080/whattodrink/_06_Maintain/b_06_maintain/1_business_toppings_list.jsp");
        }
      },
    });
  });

  // 開啟modal
  const modifyModal = document.getElementById("alter");
  const modal = new bootstrap.Modal(modifyModal, { keyboard: false });
  $(".fa-edit").click(function (e) {
    var toppingId = e.target.id;
    console.log(toppingId);
    //請求該配料資料
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/whattodrink/UpdateToppingServlet",
      data: { toppingId: toppingId },
      success: function (response) {
	$("[name=toppingImg]").change(function () {
          var img = $("[name=toppingImg]").get(0).files[0].name;
          var idx = img.lastIndexOf(".");
          var lastname = img.substring(idx, img.length);
          var name = lastname.toLowerCase();
          if (name != ".jpg" && name != ".jpeg") {
            alert("圖片格式錯誤，請重新選擇");
            $("[name=toppingImg]").val("");
          } else {
            $("[name=toppingImg]").attr(
              "src",
              URL.createObjectURL($(this).get(0).files[0])
            );
          }
        });
        var toppingName = response.toppingName;
        var toppingPrice = response.toppingPrice;
        var toppingCal = response.toppingCal;
        $("#addOnName").val(toppingName);
        $("#addOnTotal").val(toppingPrice);
        $("#addOnCal").val(toppingCal);
      },
    });

    $("#toppingId").attr("value", toppingId);
    modal.show();
    $("[data-bs-dismiss]").click(function () {
      $(".tr").removeData();
    });
  });
}
