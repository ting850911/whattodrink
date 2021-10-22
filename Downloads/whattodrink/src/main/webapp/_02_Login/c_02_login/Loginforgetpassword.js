$("#sub").click(function() {

let ee = $('#email').val();

	$.ajax({
		type: "POST",
		url: "http://localhost:8080/whattodrink/ForgetPasswordServlet",
		data: `step=1&email=${ee}`,
		success: function(res) {
			if (res != 0) {
				$("#warntext").html(`查無信箱`);
			}

			if (res == 0) {
				$("#warntext").html(`已發送驗證碼，請至信箱確認`);
				$.ajax({
					type: "POST",
					url: "http://localhost:8080/whattodrink/ForgetPasswordServlet",
					data: `step=2&email=${ee}`,
					success: function(response) {

					}
				})

			}
		},
	});


});


$("#code").click(function(e) {
	if (document.getElementById("form1").checkValidity() == true) {
	$('#form1').submit();
}
});


$("#checkPassword").click(function(e) {
	if (document.getElementById("form2").checkValidity() == true) {
		e.preventDefault();
		const str1 = $('#password1').val();
		if (($("#password1").val() == $("#password2").val()) && (/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.test(str1) == true)) {

			$('#form2').submit();

		} else {
			$("#warntext2").text(`輸入密碼不一致或格式錯誤`);
		}
	}
});


