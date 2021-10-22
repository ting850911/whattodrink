//請求分類的資料
$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/whattodrink/AddProductServlet",
    dataType: "json",
    success: function (response) {
      let count = response[0].qua;
      console.log(response[1].name);
      var str = "";
      for (let i = 1; i <= count; i++) {
        str += `<option value="${response[i].categoryId}">${response[i].name}</option>
        `;
        // console.log(response[i].category_name);
      }
      catalog.insertAdjacentHTML("afterbegin", str);
    },
  });
  //hashtag <= 2
  $(":input[name=hashtag]").click(function (e) {
    $("input[name=hashtag]").attr("disabled", false);
    if ($("input[name=hashtag]:checked").length >= 2) {
      $("input[name=hashtag]").attr("disabled", true);
      $("input[name=hashtag]:checked").attr("disabled", false);
    }
    if ($("input[id=customized]:checked")) {
      $("input[name=customized]").attr("disabled", false);
    }
  });
  //限制必填項
 $("[name=price_L]").change(function () {
    if ($("[name=price_L]").val() != "") {
      $("[name=cal_L]").attr("required", true);
    } else {
      $("[name=cal_L]").attr("required", false);
    }
  });
  $("[name=price_M]").change(function () {
    if ($("[name=price_M]").val() != "") {
      $("[name=cal_M]").attr("required", true);
    } else {
      $("[name=cal_M]").attr("required", false);
    }
  });

  $("[name=cal_L]").change(function () {
    if ($("[name=cal_L]").val() != "") {
      $("[name=price_L]").attr("required", true);
    } else {
      $("[name=cal_L]").attr("required", false);
    }
  });

  $("[name=cal_M]").change(function () {
    if ($("[name=cal_M]").val() != "") {
      $("[name=price_M]").attr("required", true);
    } else {
      $("[name=price_M]").attr("required", false);
    }
  });

  //圖片格式驗證
  $("#pro").change(function (e) {
    var img = $(":file[name=pro_pic]").get(0).files[0].name;
    var idx = img.lastIndexOf(".");
    var lastname = img.substring(idx, img.length);
    var name = lastname.toLowerCase();
    if (name != ".jpg" && name != ".jpeg") {
      alert("圖片格式錯誤，請重新選擇");
      $("#pro").val("");
    } else {
      $("#showimg").show();
      $("#showimg").attr("src", URL.createObjectURL($(this).get(0).files[0]));
    }
  });
});

//圖base64
//偵測input
$("#pro").change(function (e) {
  //取得圖檔資訊
  const file = e.target.files[0];
  const reader = new FileReader();
  // 轉換成 DataURL
  reader.readAsDataURL(file);
  reader.onload = function () {
    // 將圖片 src 替換為 DataURL
    showimg.src = reader.result;
    console.log(reader.result);
    //可能有+全部轉為%2B
    // base64 = showimg.src.replace(/\+/g, "%2B");
    // console.log(base64);
  };
  //包進formdata傳
  // formData = new FormData();
  // formData.append("proImg", showimg.src);
  // console.log(formData);
});

// $("#upload").click(function (e) {
//   e.preventDefault();
//   // alert(formData);
//   $.ajax({
//     type: "POST",
//     url: "url",
//     data: { proImg: showimg.src },
//     // data: formData,
//     success: function (response) {},
//   });
// });

// //ajax傳值
// $("#send").click(function (e) {
//   e.preventDefault();
//   var pro_name = $("input[name=pro_name]").val();
//   var price_L = $("input[name=price_L]").val();
//   var price_M = $("input[name=price_M]").val();
//   var cal_L = $("input[name=cal_L]").val();
//   var cal_M = $("input[name=cal_M]").val();
//   var hashtag = $('input:checkbox:checked[name="hashtag"]')
//     .map(function () {
//       return $(this).val();
//     })
//     .get();

//   $("input[name = customized]").change(function () {
//     $("#customized").attr("value", $("input[name = customized]").val());
//     // console.log($("#customized").val());
//   });
//   var suger = $('input:checkbox:checked[name="suger"]')
//     .map(function () {
//       return $(this).val();
//     })
//     .get();
//   var temp = $("select[name=temp]").val();
//   var catagories = $("select[name=catagories]").val();
//   var price_L = $("input[name=price_L]").val();
//   var price_L = $("input[name=price_L]").val();
//   var price_L = $("input[name=price_L]").val();
//   var price_L = $("input[name=price_L]").val();
//   var price_L = $("input[name=price_L]").val();
//   $.ajax({
//     type: "POST",
//     url: "",
//     contentType: "json",
//     // data: $("#proData").serialize(),
//     data: {
//       proImg: showimg.src,
//       proName: pro_name,
//       price_L: price_L,
//       price_M: price_M,
//       cal_L: cal_L,
//       cal_M: cal_M,
//       hashtag: hashtag,
//       suger: suger,
//       temp: temp,
//       catagories: catagories,
//     },
//     success: function (response) {
//       console.log(data);
//     },
//   });
// });
