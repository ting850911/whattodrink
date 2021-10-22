//圖片格式驗證
$("#topping").change(function () {
  var img = $(":file[name=topping_pic]").get(0).files[0].name;
  var idx = img.lastIndexOf(".");
  var lastname = img.substring(idx, img.length);
  var name = lastname.toLowerCase();
  if (name != ".jpg" && name != ".jpeg") {
    alert("圖片格式錯誤，請重新選擇");
    $("#topping").val("");
  } else {
    $("#showimg").show();
    $("#showimg").attr("src", URL.createObjectURL($(this).get(0).files[0]));
  }
});
