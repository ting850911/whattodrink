<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>我的最愛</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
    integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
    crossorigin="anonymous" referrerpolicy="no-referrer" />

 
</head>

<body>
<p>測試之前要先登入成功  建立session  CLoginOk</p>
<a href="<c:url value='/_01_Register/c_01_register/LoginRegister.jsp'/>">去登入</a>
${CLoginOk}



 <a href="#">A01</a>
 
  <a href="#">B01</a>
  
  <a href="#">C01</a>
  
  <a href="#">D01</a>
  
  <a href="#">E01</a>
  
  <a href="#">F01</a>
    
  <a href="#">G01</a>

  <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>



$('a').click(function(e){


  console.log(e.target.innerHTML);
  $.ajax({
            url: "http://localhost:8080/whattodrink/MyFavoriteServlet",
            type: "post",
            data: {
              company_id : e.target.innerHTML
            },
            success: function (data) {
            alert('成功傳向後端 (去資料庫檢查)')
            }})





})




</script>

</body>

</html>