//載入頁面要資料
$(document).ready(function () {
  $.ajax({
	cache: false,
    type: "GET",
    url: "http://localhost:8080/whattodrink/BusinessCompanyInfoServlet",
    dataType: "json",
    success: function (response) {
      loadData(response);
    },
  });
});
function loadData(data) {
  var storeName = data.trade_name;
  var storeAddress = data.company_address;
  var startTime = data.start_time;
  var endTime = data.end_time;
  var storeTel = data.tel;
  var person = data.company_owner;
  var num = data.business_name;
  var tax_id_number = data.tax_id_number;
  var tel = data.company_owner_phone;
  $("#storeName").val(storeName);
  $("#storeAddress").val(storeAddress);
  $("#startTime").val(startTime);
  $("#endTime").val(endTime);
  $("#storeTel").val(storeTel);
  $("#person").val(person);
  $("#num").val(num);
  $("#tax_id_number").val(tax_id_number);
  $("#tel").val(tel);



function getRandom(min,max){
    return Math.floor(Math.random()*(max-min+1))+min;
};



  var pic1 = `<img src="../../${data.company_iconpath}?yui=${getRandom(0,1000)}" style="max-width: 100%; max-height: 100%" id="pic1"/>`;
  var pic2 = `<img src="../../${data.bg_iconpath}?yui=${getRandom(0,1000)}" style="max-width: 100%; max-height: 100%" id="pic2"/>`;
  img1.insertAdjacentHTML("afterbegin", pic1);
  img2.insertAdjacentHTML("afterbegin", pic2);
}

//圖片格式驗證
$("#jpg1").change(function () {
  var img = $(":file[name=pic1]").get(0).files[0].name;
  var idx = img.lastIndexOf(".");
  var lastname = img.substring(idx, img.length);
  var name = lastname.toLowerCase();
  if (name != ".jpg" && name != ".jpeg") {
    alert("圖片格式錯誤，請重新選擇");
    $("#jpg1").val("");
  } else {
    $("#showimg1").show();
    $("#showimg1").attr("src", URL.createObjectURL($(this).get(0).files[0]));
    $("#pic1").hide();
  }
});

$("#jpg2").change(function () {
  var img = $(":file[name=pic2]").get(0).files[0].name;
  var idx = img.lastIndexOf(".");
  var lastname = img.substring(idx, img.length);
  var name = lastname.toLowerCase();

  if (name != ".jpg" && name != ".jpeg") {
    alert("圖片格式錯誤");
    $("#jpg2").val("");
  } else {
    $("#showimg2").show();
    $("#showimg2").attr("src", URL.createObjectURL($(this).get(0).files[0]));
    $("#pic2").hide();
  }
});

//驗證營業時間必填
$(function () {
  $.validator.setDefaults({
    errorClass: "help-block",
    highlight: function (element) {
      $(element).closest(".form-control").addClass("has-error");
      // console.log(e);
    },
    unhighlight: function (element) {
      console.log(element);
      $(element).closest(".form-control").removeClass("has-error");
    },
  });

  $("#storeInfo").validate({
    rules: {
      startTime: {
        required: true,
      },
      endTime: {
        required: true,
      },
    },
    messages: {
      startTime: {
        required: "*必填",
      },
      endTime: {
        required: "*必填",
      },
    },
  });
});

//設定營業時間
$(".timepicker1").timepicker({
  timeFormat: "HH:mm:ss",
  interval: 30,
  minTime: "10",
  maxTime: "12:00",
  startTime: "10:00",
  dynamic: false,
  dropdown: true,
  scrollbar: true,
});
$(".timepicker2").timepicker({
  timeFormat: "HH:mm:ss",
  interval: 30,
  minTime: "21:30",
  maxTime: "23:30",
  startTime: "21:30",
  dynamic: false,
  dropdown: true,
  scrollbar: true,
});

//ajax傳值
$("#check1").click(function (e) {
  e.preventDefault();
  var storeName = $("#storeName").val();
  var storeAddress = $("#storeAddress").val();
  var startTime = $("#startTime").val();
  var endTime = $("#endTime").val();
  var storeTel = $("#storeTel").val();
  $.ajax({
    url: "http://localhost:8080/whattodrink/BusinessCompanyInfoServlet?type=1",
    type: "POST",
    data: {
      storeName: storeName,
      storeAddress: storeAddress,
      startTime: startTime,
      endTime: endTime,
      storeTel: storeTel,
    },
    success: function () {},
  });
});

$("#check2").click(function (e) {
  e.preventDefault();
  var person = $("#person").val();
  var num = $("#num").val();
  var tax_id_number = $("#tax_id_number").val();
  var tel = $("#tel").val();
  if (tax_id_number.length != 8) {
    if (tax_id_number.length == 0) {
      $.ajax({
        url: "http://localhost:8080/whattodrink/BusinessCompanyInfoServlet?type=2",
        type: "POST",
        cache: false,
        data: {
          person: person,
          num: num,
          tax_id_number: tax_id_number,
          tel: tel,
        },
      });
    } else {
      alert("統一編號需為8碼");
    }
  } else {
    $.ajax({
      url: "http://localhost:8080/whattodrink/BusinessCompanyInfoServlet?type=2",
      type: "POST",
      cache: false,
      data: {
        person: person,
        num: num,
        tax_id_number: tax_id_number,
        tel: tel,
      },
    });
  }
});
