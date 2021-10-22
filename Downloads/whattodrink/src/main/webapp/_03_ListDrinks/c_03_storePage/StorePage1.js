//收藏愛心
function change(heart) {
console.log(heart.id);
  if (heart.className == "far fa-heart fa-lg") {
  		console.log('空心轉實心');
   $.ajax({
            url: "http://localhost:8080/whattodrink/AddDeleteMyFavoriteServlet",
            type: "post",
            data: {
              company_id:heart.id
            },
            success(res) {
            console.log(res);
                heart.className = "fas fa-heart fa-lg";
              

            }});
  
  } else {
		console.log('實星轉空星');
   $.ajax({
     	
            url: "http://localhost:8080/whattodrink/AddDeleteMyFavoriteServlet",
            type: "post",
            data: {
              company_id:heart.id
            },
            success(res) {
             console.log(res);
                heart.className = "far fa-heart fa-lg";
            
            }
          });
  
  }
}




//儲存地址
	let storage = sessionStorage;

	document.getElementById("location0").value = storage.getItem("Address");
	document.getElementById("location1").value = storage.getItem("Address");

	//按下enter送出定位表單
	$("#location0").keydown(function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
			let Address = document.getElementById("location0").value;
			storage.setItem("Address", Address);
			document.getElementById("location0").value = storage.getItem("Address");
			document.getElementById("location1").value = storage.getItem("Address");
			initMap();
			
			
			
			
			function initMap() {
			    destination1=['台北市中正區新生南路一段52之3號','台北市中正區八德路一段82巷9弄18號','台北市中正區八德路一段82巷9弄20之1號','台北市中山區遼寧街38號','台北市大安區市民大道三段136號','台北市中正區青島東路21之10號','台北市大安區新生南路一段1號'];

			    
			   // 所在位置跟各點的距離
			   var service = new google.maps.DistanceMatrixService();
			   service.getDistanceMatrix(
			     {
			       origins: [document.getElementById("location0").value],
			       destinations: destination1,
			       travelMode: "WALKING", // 交通方式：BICYCLING(自行車)、DRIVING(開車，預設)、TRANSIT(大眾運輸)、WALKING(走路)
			       unitSystem: google.maps.UnitSystem.METRIC, // 單位 METRIC(公里，預設)、IMPERIAL(哩)
			     },function (response) {
			       console.log(response);
			       
			           str='';
			           str += `A01=`+response.rows[0].elements[0].distance.value+`&B01=`+response.rows[0].elements[1].distance.value+`&C01=`+response.rows[0].elements[2].distance.value+`&D01=`+response.rows[0].elements[3].distance.value+`&E01=`+response.rows[0].elements[4].distance.value+`&F01=`+response.rows[0].elements[5].distance.value+`&G01=`+response.rows[0].elements[6].distance.value;
			           console.log(response.rows[0].elements[0].distance.value);
			           console.log(str);  
			             
			           $.ajax({
			               type: "POST",
			               url: "http://localhost:8080/whattodrink/DistanceServlet",
			               data:str,
			               success: function (response) {
			                 console.log(response);  
			                 location.reload()
			               },
			             });
			          
			         }
			   );
			}

			
			
			
			
		}
	});

	$("#location1").keydown(function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
			let Address = document.getElementById("location1").value;
			storage.setItem("Address", Address);
			document.getElementById("location0").value = storage.getItem("Address");
			document.getElementById("location1").value = storage.getItem("Address");
			initMap();
			
			
			
			
			
			
			function initMap() {
			    destination1=['台北市中正區新生南路一段52之3號','台北市中正區八德路一段82巷9弄18號','台北市中正區八德路一段82巷9弄20之1號','台北市中山區遼寧街38號','台北市大安區市民大道三段136號','台北市中正區青島東路21之10號','台北市大安區新生南路一段1號'];

			    
			   // 所在位置跟各點的距離
			   var service = new google.maps.DistanceMatrixService();
			   service.getDistanceMatrix(
			     {
			       origins: [document.getElementById("location0").value],
			       destinations: destination1,
			       travelMode: "WALKING", // 交通方式：BICYCLING(自行車)、DRIVING(開車，預設)、TRANSIT(大眾運輸)、WALKING(走路)
			       unitSystem: google.maps.UnitSystem.METRIC, // 單位 METRIC(公里，預設)、IMPERIAL(哩)
			     },function (response) {
			       console.log(response);
			       
			           str='';
			           str += `A01=`+response.rows[0].elements[0].distance.value+`&B01=`+response.rows[0].elements[1].distance.value+`&C01=`+response.rows[0].elements[2].distance.value+`&D01=`+response.rows[0].elements[3].distance.value+`&E01=`+response.rows[0].elements[4].distance.value+`&F01=`+response.rows[0].elements[5].distance.value+`&G01=`+response.rows[0].elements[6].distance.value;
			           console.log(response.rows[0].elements[0].distance.value);
			           console.log(str);  
			             
			           $.ajax({
			               type: "POST",
			               url: "http://localhost:8080/whattodrink/DistanceServlet",
			               data:str,
			               success: function (response) {
			                 console.log(response);
			                 location.reload()
			               },
			             });
			          
			         }
			   );
			}

		}
	});









