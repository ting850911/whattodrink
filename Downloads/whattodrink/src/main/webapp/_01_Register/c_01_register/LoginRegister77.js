let ccc = document.querySelector("#login1");
let registerForm1 = document.querySelector("#register1");
let registerForm2 = document.querySelector("#register2");
let registerForm3 = document.querySelector("#register3");
let switchButton = document.querySelector(".switch");

let login = () => {
  registerForm3.style.display = "none";
  registerForm2.style.display = "none";
  registerForm1.style.display = "flex";
  ccc.style.left = "30px";
  registerForm1.style.left = "400px";
  switchButton.style.left = "0px";
};

let register = () => {
  registerForm3.style.display = "none";
  registerForm2.style.display = "none";
  registerForm1.style.display = "flex";

  ccc.style.left = "-400px";
  registerForm1.style.left = "30px";
  switchButton.style.left = "120px";
};

$("#register_next0").click(function (e) {
  if (document.getElementById("register1").checkValidity() == true) {
    e.preventDefault();
    const regex = /^09/;
    const str = $('#userID_register').val();
    
    if ((str.length == 10) && (regex.test(str)===true)) {

     $.ajax({
       url: "http://localhost:8080/whattodrink/_01_register/register.do",
       type: "POST",
       data: $("#register1").serialize()+"&"+"step=1",
       success(res) {
       console.log(res);
       
         if (res == 1) {
          registerForm1.style.display = "none";
          registerForm2.style.display = "flex";


		$.ajax({
		      url: "http://localhost:8080/whattodrink/_01_register/register.do",
		      type: "POST",
		      data: "step=9"+"&"+$("#register1").serialize(),
		      success(res) {
			
						}
				})



         }else if(res == 2){
           $("#register1_warn").html("此手機號碼已註冊過");
         }else if(res == 3){
           $("#register1_warn").html("此EMAIL已使用過");
         }else if(res == 4){
           $("#register1_warn").html("此手機號碼&EMAIL已使用過");
         }
       },
     });

    }else{
      $("#register1_warn").text(`請輸入正確電話號碼`);
    }
  }
});

$("#register_next1").click(function (e) {
  if (document.getElementById("register2").checkValidity() == true) {
    e.preventDefault();
    $.ajax({
      url: "http://localhost:8080/whattodrink/_01_register/register.do",
      type: "POST",
      data: $("#register2").serialize()+"&"+"step=2"+"&"+$("#register1").serialize(),
      success(res) {
      console.log(res);
        if (res != 0) {
          $("#register2_warn").html("驗證碼輸入錯誤");
        }
        if (res == 0) {
          registerForm1.style.display = "none";
          registerForm2.style.display = "none";
          registerForm3.style.display = "flex";
        
}
      },
    });
  }
});

$("#register_next2").click(function (e) {
  if (document.getElementById("register3").checkValidity() == true) {
    e.preventDefault();
    const str1 = $('#password1_register').val();
     if (($("#password1_register").val() == $("#password2_register").val())&&(/^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$/.test(str1)==true)) {

       $.ajax({
         url: "http://localhost:8080/whattodrink/CustomerRegisterServlet",
         type: "POST",
         data: $("#register1").serialize()+"&"+$("#register3").serialize(),
         success(res) {
           if (res != 0) {
             $("#register3_warn").html(res);
           }
           if (res == 0) {
             window.location.assign("http://localhost:8080/whattodrink/_01_Register/c_01_register/Registersuccess.jsp");
           }
         },
       });
     } else {
       $("#register3_warn").text(`輸入密碼不一致或格式錯誤`);
     }
  }
});





$("#resend").click(function () {
	
	 $("#register2_warn").html(`已重新發送，請至信箱確認`);
       $.ajax({
         url: "http://localhost:8080/whattodrink/_01_register/register.do",
         type: "POST",
         data: "step=9"+"&"+$("#register1").serialize(),
         success(res) {
     
         },
       });
     
});



