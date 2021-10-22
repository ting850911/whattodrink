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
    function () {
      let flag = false;
      $.ajax({
        type: "GET",
        url: "http://localhost:8080/whattodrink/B_GetAccount",
        data: $("#email").serialize(),
        dataType: "json",
        async: false,
        success: function (res) {
          console.log(res);
          if (res == "yes") {
            flag = true;
          }
        },
      });
      return flag;
    },
    `無此信箱，請聯繫<a class="text-decoration-none" href="mailto:whattodrink2021@whattodrink.com">今天喝什麼</a>`
  );

  $("#getUsername").validate({
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

  $("#next").click(function (e) {
    e.preventDefault();
    $.ajax({
      url: "http://localhost:8080/whattodrink/B_GetAccount",
      type: "POST",
      data: $("#email").serialize(),
      success() {
        $("#getUsername").addClass("d-none");
        $("#back").removeClass("d-none");
        $("#back").addClass("flex");
      },
    });
  });
});
