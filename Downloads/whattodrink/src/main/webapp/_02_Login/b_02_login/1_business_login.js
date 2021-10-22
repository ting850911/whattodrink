$(function () {
  $.validator.setDefaults({
    errorClass: "help-block",
    highlight: function (element) {
      $(element).closest(".form-control-lg").addClass("has-error");
    },
    unhighlight: function (element) {
      $(element).closest(".form-control-lg").removeClass("has-error");
    },
  });

  $.validator.addMethod(
    "constrains",
    function (value, element) {
	    $.ajax({
	        type: "POST",
	        url: "http://localhost:8080/whattodrink/_02_Login/B_LoginServlet.do",
	        data: $("#password").serialize(),
	        dataType: "json",
	        async: false,
	        success: function (res) {
	          if (res == "yes") {
	            flag = true; 
	          }
	        }
	    });
      return (
        this.optional(element) ||
        (value.length >= 8 &&
          /^[A-Za-z0-9]+$/.test(value) &&
          /[a-z]/g.test(value) &&
          /[A-Z]/g.test(value))
      );
    },
    "密碼需為英文大小寫及數字，至少8碼"
  );

  $.validator.addMethod(
    "noAccount",
    function (value, element) {
    let flag = false;
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/whattodrink/_02_Login/B_LoginServlet.do",
        data: $("#username").serialize(),
        dataType: "json",
        async: false,
        success: function (res) {
	      	console.log(res);
          if (res == "yes") {
	      	console.log(res);
            flag = true;
          }
        }
      });
      return flag;
    },
    `帳號錯誤<a class="mx-1 text-decoration-none" href="./1_business_get_username.jsp">取得帳號</a>`
  );

  $("#account").validate({
    rules: {
      username: {
        required: true,
        noAccount: true,
      },
      password: {
        required: true,
        constrains: true,
      },
    },
    messages: {
      username: {
        required: "請輸入帳號",
        remote: "noAccount",
      },
      password: {
        required: "請輸入密碼",
      },
    },
  });

//  $("#account").submit(function (e) {
//    e.preventDefault();
//    $ajax({
//      url: "http://localhost:8080/whattodrink/_02_Login/B_LoginServlet.do",
//      type: "POST",
//      data: {
//        username: function () {
//          return $("#username").val();
//        },
//        password: function () {
//          return $("#password").val();
//        },
//      },
//      success(res) {
        // (判斷第一次登入 : 公司頁 ? 訂單頁)
//      },
//    });
//  });
});
