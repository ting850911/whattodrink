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
    "noEmail",
    function (value, element) {
    let flag = false;
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/whattodrink/_01_Register/B_RegisterCheckServlet.do",
        data: $("#email").serialize(),
        dataType: "json",
    	async: false,
        success: function (res) {
          if (res == "yes") {
			flag = true;
          }
        }
      });
      return flag;
    },`無此信箱，請聯繫<a class="text-decoration-none" href="mailto:whattodrink2021@whattodrink.com">今天喝什麼</a>`
  );
  
   $.validator.addMethod(
    "noInvitationCode",
    function (value, element) {
    let flag = false;
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/whattodrink/_01_Register/B_RegisterCheckServlet.do",
        data: $("#invitationCode").serialize(),
        dataType: "json",
		async: false,
        success: function (res) {
          if (res == "yes") {
            flag = true;
          }
        }
      });
      return flag;
    },"邀請碼錯誤"
  );

  $("#register_form").validate({
    rules: {
      email: {
        required: true,
        email: true,
        noEmail: true,
      },
      invitationCode: {
        required: true,
        noInvitationCode: true,
      },
    },
    messages: {
      email: {
        required: "請輸入信箱",
        email: "信箱格式錯誤，須包含@與.com",
//        noEmail: `無此信箱，請聯繫<a class="text-decoration-none" href="mailto:whattodrink2021@whattodrink.com">今天喝什麼</a>`,
        remote: "noEmail",
      },
      invitationCode: {
        required: "請輸入邀請碼",
//        noInvitationCode:"邀請碼錯誤",
        remote: "noInvitationCode"
      },
    }
  });

//  $("#next").submit(function (e) {
//    e.preventDefault();
//    $ajax({
//      url: "http://localhost:8080/whattodrink/_01_Register/B_RegisterCheckServlet.do",
//      type: "POST",
//      data: $("#email").serialize(),
//      success(res) {},
//    });
//  });
});

