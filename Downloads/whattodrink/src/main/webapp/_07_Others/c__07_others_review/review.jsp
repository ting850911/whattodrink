<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>評價</title>
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
      crossorigin="anonymous"
    />
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"
      integrity="sha512-iBBXm8fW90+nuLcSKlbmrPcLa0OT92xO1BIsZ+ywDWZCvqsWgccV3gFoRBv0z+8dLJgyAHIhR35VZc2oM/gI1w=="
      crossorigin="anonymous"
      referrerpolicy="no-referrer"
    />
    
    <link rel="stylesheet" href="<c:url value="/_07_Others/c__07_others_review/star1.css"/>">
    <link rel="stylesheet" href="<c:url value="/_07_Others/c__07_others_review/review.css"/>" />
    
  
  </head>

  <body>
 
  <jsp:include page="/_08_Fragment/top.jsp" />

   <!-- 開始 -->
  <div class="container-fluid">
    <div class="row">

      <div class="col-sm-1"></div>
          <div class="col-sm-10 mx-auto">
        <br>
        <!-- title框開始 -->
        <div class="EvaluationTittle">
          <h4><i class="fas fa-thumbs-up"></i>&nbsp;&nbsp;評價</h4>
          
            <h5>${itemBeans[0].orderBean.companyBean.company_name} | ${itemBeans[0].orderBean.companyBean.trade_name}感謝您的訂購</h5>
          
          <p>和大家分享這杯飲料吧 </p>
        </div>

        
        <!-- title框結束 -->

        <div class="d-flex justify-content-center">
          <div class="productbody">
            <!-- row 是裝本體的外框 -->
            <div class="row3" style="display: flex; flex-direction: row;">

             <c:forEach varStatus="stVar" var="bb" items="${itemBeans}">
             
             <c:if test="${bb.drinkBean.enabled==false}">
                  <div class="row">
                  <p class="fw-bold fs-5 m-0">${bb.drinkBean.product_name}${bb.capacity}</p>
                <img src="<c:url value="/images/notcomment.png"/>" style="width:170px;height:170px;padding:0px;">
                  </div>
             </c:if>
             
             
             
             <c:if test="${bb.drinkBean.enabled==true}">
             <form id="form${stVar.count}" enctype="multipart/form-data" method="POST" name="fileinfo">
				
              <div class="row mb-3 rr">
                <!-- 以下商品名跟星星 -->
                <div class="col-sm-6">
                  <p class="fw-bold fs-5 m-0">${bb.drinkBean.product_name}${bb.capacity}</p>
                  <Input type='hidden' name='product_id' value='${bb.product_id}'/>
                  <Input type='hidden' name='order_id' value='${bb.order_id}' />
                  <Input type='hidden' name='item_id' value='${bb.item_id}' />
                  <Input type='hidden' name='customer_id' value='${bb.orderBean.customer_id}' />
                  <div class="rate1">
                    <input type="radio" id="star5${stVar.count}" name="star" value="5" checked="checked"/>
                    <label for="star5${stVar.count}">5 stars</label>
                    <input type="radio" id="star4${stVar.count}" name="star" value="4" />
                    <label for="star4${stVar.count}">4 stars</label>
                    <input type="radio" id="star3${stVar.count}" name="star" value="3" />
                    <label for="star3${stVar.count}">3 stars</label>
                    <input type="radio" id="star2${stVar.count}" name="star" value="2" />
                    <label for="star2${stVar.count}">2 stars</label>
                    <input type="radio" id="star1${stVar.count}" name="star" value="1" />
                    <label for="star1${stVar.count}">1 star</label>
                  </div>
                </div>
                <!-- 以上商品名跟星星 -->
                <!-- 以下這格裝了圖片跟輸入文字框 -->
                <div class="pictureText" style="display: flex">
                  <div class="col-sm-6" style="display: flex; align-items: center;" >
                    <div id="newImg1"></div>
                  </div>
                  <!-- 如果用格子裝textarea會對不齊圖片 -->
                  <!-- <div class="accordion-body"> -->
                  <textarea name="talk" cols="20" rows="7" placeholder="限制30字" required></textarea>
                  <!-- </div> -->
                </div>
                <!-- 以上這格裝了圖片跟輸入文字框 -->
                <!-- 這是上傳檔案鍵 -->
                <div><label class="btn" style="border: #ccc 1px solid;"><input id="upload_img${stVar.count}" style="display:none;" type="file" accept="image/*"><i
                      class="fa fa-photo"></i> 上傳圖片</label></div>
                <!-- 這是上傳檔案鍵 -->
              </div>
              
                 </form>
             
             </c:if>
             
				
                 
                 
              </c:forEach>



            </div>
          

            </div>
          </div>

        
        <!-- 以上商品body -->
        <div class="mt-5 d-flex justify-content-center">
          <button type="submit" class="btn btn-dark mx-auto" style="width: 200px" id="submitbtn">
            完成
          </button>
        </div>
          </div>
        
      <div class="col-sm-1"></div>

    </div>
  </div>

  <!-- 結束 -->
  


 <!-- Modal(sharecode) start-->
   <div class="modal fade" id="sharecode" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">分享你的邀請碼給好友吧</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body d-flex justify-content-center" style="padding-top: 30px;">
          <i class="fas fa-gift fa-lg"
            id="Invitationcode">&nbsp;&nbsp;&nbsp;&nbsp;${CLoginOK.invitation}&nbsp;</i>
          <button type="button" class="btn" style="position: relative;bottom: 9px;" data-bs-container="body" data-bs-toggle="popover" data-bs-placement="right" data-bs-content="已複製">
            <i class="far fa-copy fa-lg" onclick="copy()"></i>
          </button>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn" data-bs-dismiss="modal" aria-label="Close">完成</button>
        </div>
      </div>
    </div>
  </div>
  <!-- Modal(sharecode) end-->
    

    <!-- footer end -->
    <footer id="footer">
      <div class="container-fluid">
        <div class="row">
          <div class="col-sm-1"></div>
          <div class="footer-content logo col-sm-5 m-0">
            <a href=""
              ><img src="<c:url value="/images/logo.png"/>" alt="logo" class="small-logo"
            /></a>
            <div class="app_download">
              <a href="" class="text-dark text-decoration-none"
                ><span class="border border-1 rounded-1 p-1 border-dark"
                  ><i class="fab fa-google-play me-1"></i>應用程式</span
                ></a
              >
            </div>
          </div>
          <div class="col-sm-1"></div>
          <div class="footer-content col-sm-2">
            <a href="" class="link-dark text-decoration-none">關於我們</a>
            <a href="" class="link-dark text-decoration-none">建立企業帳戶</a>
            <a href="" class="link-dark text-decoration-none">登入您的商店</a>
          </div>
          <div class="footer-content col-sm-2">
            <a href="" class="link-dark text-decoration-none">取得協助</a>
            <a href="" class="link-dark text-decoration-none">附近的商家</a>
            <a href="" class="link-dark text-decoration-none">切換語言</a>
          </div>
          <div class="col-sm-1"></div>
        </div>
        <div class="row d-flex justify-content-center">
          <div class="footer-content col-sm-10">
            <hr />
          </div>
        </div>
        <div class="row">
          <div class="col-sm-1"></div>
          <div class="footer-content icons col-sm-5">
            <i class="fab fa-facebook"></i>
            <i class="fab fa-instagram"></i>
            <i class="fab fa-twitter"></i>
          </div>
          <div
            class="
              footer-content
              col-sm-5
              d-flex
              flex-direction-row
              align-items-end
            "
          >
            <span>&copy;2021 今天喝什麼 Inc.</span>
          </div>
          <div class="col-sm-1"></div>
        </div>
      </div>
    </footer>
    <!-- footer end -->
    
   <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.1.5/dist/sweetalert2.all.min.js" integrity="sha256-sq9BgeqJAlCLfjfAO+2diFGt5O8aIYNrds+yhlpFnvg=" crossorigin="anonymous"></script>

<script>



  (function ($) {
    var compressRatio = 0.8, // 圖片壓縮比例
      imgNewWidth = 200, // 圖片新寬度

      img = new Image(),
      canvas = document.createElement("canvas"),
      context = canvas.getContext("2d"),
      file, fileReader, dataUrl;

    // 上傳檔案
    $('input[type=file]').change(function (c) {
    	 var image = $("input[type=file]").get(0).files[0].name;
         var idx = image.lastIndexOf(".");
         var lastname = image.substring(idx, image.length);
         var name = lastname.toLowerCase();
         if (name != ".jpg" && name != ".jpeg" && name!= ".png") {
           alert("圖片格式錯誤，請重新選擇");
           $("input[type=file]").val("");
         }
     
      file = this.files[0];
      // 圖片才處理
      if (file && file.type.indexOf("image") == 0) {
        fileReader = new FileReader();
        fileReader.onload = getFileInfo;
        fileReader.readAsDataURL(file);
      }


      if (this.files.length==0) {
     $(c.target.parentNode.parentNode.previousElementSibling.firstElementChild.firstElementChild).empty();
        }
      
      
      function getFileInfo(evt) {
      dataUrl = evt.target.result,

        // 取得圖片
        img.src = dataUrl;
    }

    // 圖片載入後
    img.onload = function () {
      var width = this.width, // 圖片原始寬度
        height = this.height, // 圖片原始高度
        imgNewHeight = imgNewWidth * height / width, // 圖片新高度
        html = "",
        newImg;

      // 顯示預覽圖片
      // html += "<img src='" + dataUrl + "'/>";
      // html += "<p>這裡是原始圖片尺寸 " + width + "x" + height + "</p>";
      // html += "<p>檔案大小約 " + Math.round(file.size / 1000) + "k</p>";
      // $("#oldImg").html(html);

      // 使用 canvas 調整圖片寬高
      canvas.width = imgNewWidth;
      canvas.height = imgNewHeight;
      context.clearRect(0, 0, imgNewWidth, imgNewHeight);

      // 調整圖片尺寸
      context.drawImage(img, 0, 0, imgNewWidth, imgNewHeight);

      // 顯示新圖片
      newImg1 = canvas.toDataURL("image/jpeg", compressRatio);
      html = "";
      html += "<img src='" + newImg1 + "' />";
      // html += "<p>圖片尺寸 " + imgNewWidth + "x" + imgNewHeight + "</p>";
      // html += "<p>檔案大小約 " + Math.round(0.75 * newImg.length / 1000) + "k</p>"; // 出處 https://stackoverflow.com/questions/18557497/how-to-get-html5-canvas-todataurl-file-size-in-javascript
      

      console.log(c.target.parentNode.parentNode.previousElementSibling.firstElementChild.firstElementChild);
      
       $(c.target.parentNode.parentNode.previousElementSibling.firstElementChild.firstElementChild).html(html);

      // canvas 轉換為 blob 格式、上傳
      canvas.toBlob(function (blob) {
        // 輸入上傳程式碼
      }, "image/jpeg", compressRatio);
    };



    });

    
  })(jQuery);
</script>
    
    
    
    
<script type="text/javascript">
$('#submitbtn').click(function(e){

	    e.preventDefault();


	var formData = new FormData();

	
	$('input[name=customer_id]').each(function(){
		formData.append('customer_id',$(this).val());
	})
	
	
		$('input[name=order_id]').each(function(){
		formData.append('order_id',$(this).val());
	})
	
	
	
	$('input[name=item_id]').each(function(){
		formData.append('item_id',$(this).val());
	})
	
	
		$('input[name=product_id]').each(function(){
		formData.append('product_id',$(this).val());
	})
	
	
	$('[name="star"]:checked').each(function(){
		formData.append('star',$(this).val());
		
	})
	
	
	
	$('textarea[name=talk]').each(function(){
		formData.append('comment',$(this).val());
	})

	
	$.each($("input[type=file]"), function(i, obj) {
		if(obj.files.length==1){
        $.each(obj.files,function(j, file){
        	formData.append('photo['+j+']', file);
        	formData.append('upload',1);
        	
        })
		}else{
			formData.append('upload',0);
			formData.append('photo[0]', 0);
		}
		
		
		
});

// console.log(formData.getAll('comment'));
// console.log(formData.getAll('star'));
// console.log(formData.getAll('item_id'));
	

    $.ajax({
        url: 'http://localhost:8080/whattodrink/AddCommentsServlet',
        cache: false,
        contentType: false,
        processData: false,
        data: formData,                    
        type: 'post',
       success: function(data){
    	   
    	   
    	   
    	   Swal.fire({
    		   icon: 'success',
    		   title: '已送出評論!',
    		   showConfirmButton: false,
    		   timer: 2000
    		 }).then(function() {
    			 window.location.assign("http://localhost:8080/whattodrink/_05_Order/c_05_order/myOrder.jsp");
    		 })
    		 
    		 
    	
    
    	   
    	  
        }
});




	

});



</script>   

<script type="text/javascript">
	
	//複製邀請碼右方彈跳文字
	var popoverTriggerList = [].slice.call(
	  document.querySelectorAll('[data-bs-toggle="popover"]')
	);
	var popoverList = popoverTriggerList.map(function (popoverTriggerEl) {
	  return new bootstrap.Popover(popoverTriggerEl);
	});

	</script>
	 
  </body>
</html>
