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
	        url: "http://localhost:8080/whattodrink/_01_Register/B_RegisterUpdateServlet.do",
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
    "smaeAccount",
    function (value, element) {
      let flag = false; 
      $.ajax({
		cache: false,
        type: "POST",
        url: "http://localhost:8080/whattodrink/_01_Register/B_RegisterUpdateServlet.do",
        // data: { username: value },
        data: $("#username").serialize(),
        dataType: "json",
        async: false,
        success: function (res) {
          if (res == "yes") {
            flag = true; //沒有重複帳號
          }
        }
      });
      return flag;
    },`帳號已經存在，前往<a href="http://localhost:8080/whattodrink/_02_Login/b_02_login/1_business_login.jsp"/>登入</a>`
  );
  $("#register_form").validate({
    rules: {
      username: {
        required: true,
        smaeAccount: true,
        //   remote: {
        //     url: "http://localhost:8080/whattodrink/_01_Register/B_RegisterUpdateServlet.do",
        //     type: "POST",
        //     dataType: "json",
        //     data: {
        //       username: function () {
        //         return $("#username").val();
        //       },
        //     },
        //     dataFilter: function (data, type) {
        //       if (data == "yes") {
        //         return true;
        //       } else {
        //         return false;
        //       }
        //     },
        //   },
      },
      password: {
        required: true,
        constrains: true,
      },
      password2: {
        required: true,
        equalTo: "#password",
      },
    },
    messages: {
      username: {
        required: "請輸入帳號",
        remote: "smaeAccount",
        // `帳號已經存在，前往<a href="./1_business_login.html">登入</a>`,
      },
      password: {
        required: "請輸入密碼",
      },
      password2: {
        required: "請輸入密碼",
        equalTo: "密碼不一致，請重新輸入",
      },
    },
  });

  $("#register_form").submit(function (e) {
//  	e.preventDefault();
    $.ajax({
      url: "http://localhost:8080/whattodrink/_01_Register/B_RegisterUpdateServlet.do",
      type: "POST",
      async: false,
      data: 
      $("#register_form").serialize(),
//      {
//        username: function () {
//        console.log($("#username").val());
//          return $("#username").val();
//        },
//        password: function () {
//        console.log($("#password").val());
//          return $("#password").val();
//        },
//      },
      success(res) {
      	if(res == "InsertOK"){
	        alert("註冊成功，前往登入頁");     	
	        window.location.assign("http://localhost:8080/whattodrink/_02_Login/b_02_login/1_business_login.jsp");      	
      	}
      }
    });
  });
});
