$("button").click(function (e) {
  if (document.getElementById("bRegisterform1").checkValidity() == true) {
    e.preventDefault();
     $.ajax({
       url: "http://localhost:8080/whattodrink/_01_Register/B_RegisterCheckServlet.do",
       type: "POST",
       data: $("#bRegisterform1").serialize(),
       success(res) {
       console.log(res);
       
         if (res == 1) {
 		   window.location.assign("1_business_register2.jsp");
         }else if(res == 2){
           $("#bRegisterform1_warn").html("Email或邀請碼錯誤");
         }
       },
     });
  }
});