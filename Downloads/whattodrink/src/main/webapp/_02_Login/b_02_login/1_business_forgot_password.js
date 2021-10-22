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
    "noEmail",
    function () {
      let flag = false;
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/whattodrink/B_GetAccount",
        data: $("#input_email").serialize(),
        dataType: "json",
        async: false,
        success: function (res) {
          console.log(res);
          if (res == "yes") {
            flag = true;
            $("#next").removeAttr("disabled");
          }
        },
      });
      return flag;
    },
    `無此信箱，請聯繫<a class="text-decoration-none" href="mailto:whattodrink2021@whattodrink.com">今天喝什麼</a>`
  );
  $.validator.addMethod(
    "wrongCode",
    function () {
      let flag = false;
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/whattodrink/B_GetPassword2",
        data: $("#variviedCode").serialize(),
        dataType: "json",
        async: false,
        success: function (res) {
          console.log(res);
          if (res == "yes") {
            flag = true;
            $("#next2").removeAttr("disabled");
          }
        },
      });
      return flag;
    },
    `驗證碼錯誤，請重新輸入`
  );


  $("#input_email").validate({
    rules: {
      email: {
        required: true,
        email: true,
        noEmail: true,
      },
    },
    messages: {
      email: {
        required: "請輸入信箱",
        email: "信箱格式錯誤，須包含@與.com",
        remote: "noEmail",
      },
    },
  });

  $("#variviedCode").validate({
    rules: {
      variviedCode: {
        required: true,
        wrongCode: true,
      },
    },
    messages: {
      variviedCode: {
        required: "請輸入驗證碼",
        remote: "wrongCode",
      },
    },
  });

  $("#resetPassword").validate({
    rules: {
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
      password: {
        required: "請輸入密碼",
      },
      password2: {
        required: "請輸入密碼",
        equalTo: "密碼不一致，請重新輸入",
      },
    },
  });

  $("#next").click(function (e) {
    e.preventDefault();
    $("#input_email").addClass("d-none");
    $("#variviedCode").removeClass("d-none");
    $("#variviedCode").addClass("flex");
    $.ajax({
      type: "POST",
      url: "http://localhost:8080/whattodrink/B_GetPassword",
      data: $("#input_email").serialize(),
      dataType: "json",
      success: function () {},
    });
    var timeoutID = setInterval(function () {
      Swal.fire("超過兩分鐘，已重新寄送驗證碼");
      //兩分鐘重寄驗證碼
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/whattodrink/B_GetPassword",
        data: $("#input_email").serialize(),
        dataType: "json",
        async: false,
        success: function () {},
      });
    }, 120000);
    console.log(timeoutID);
    $("#next2").click(function (e) {
      e.preventDefault();
      clearInterval(timeoutID);
      console.log(timeoutID);
    });

    $("#resend").click(function (e) {
      e.preventDefault();
      clearInterval(timeoutID);
      //發出重寄驗證碼請求
      $.ajax({
        type: "POST",
        url: "http://localhost:8080/whattodrink/B_GetPassword",
        data: $("#input_email").serialize(),
        dataType: "json",
        async: false,
        success: function () {},
      });
      var timeoutID2 = setInterval(function () {
        Swal.fire("超過兩分鐘，已重新寄送驗證碼");
        //兩分鐘重寄驗證碼
        $.ajax({
          type: "POST",
          url: "http://localhost:8080/whattodrink/B_GetPassword",
          data: $("#input_email").serialize(),
          dataType: "json",
          async: false,
          success: function () {},
        });
      }, 120000);
      console.log(timeoutID2);
      $("#next2").click(function (e) {
        e.preventDefault();
        clearInterval(timeoutID2);
        console.log(timeoutID2);
      });
    });
  });

  $("#next2").click(function (e) {
    e.preventDefault();
    $.ajax({
      url: "http://localhost:8080/whattodrink/B_GetPassword2",
      type: "POST",
      data: $("#variviedCode").serialize(),
      dataType: "json",
      success(res) {
        console.log(res);
        if (res == "yes") {
          $("#input_email").addClass("d-none");
          $("#variviedCode").addClass("d-none");
          $("#resetPassword").removeClass("d-none");
          $("#resetPassword").addClass("flex");
        }
      },
    });
  });

  $("#resetPassword").click(function (e) {
    e.preventDefault();
    $.ajax({
      url: "http://localhost:8080/whattodrink/B_GetPassword3",
      type: "POST",
      data: $("#resetPassword").serialize(),
      dataType: "json",
      success(res) {
        console.log(res);
        if (res == "yes") {
          Swal.fire("更新成功，回到登入頁");
          setTimeout(function () {
            window.location.assign(
              "http://localhost:8080/whattodrink/_02_Login/b_02_login/1_business_login.jsp"
            );
          }, 3000);
        }
      },
    });
  });
});
