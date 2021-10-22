function initMap() {
  // 預設顯示的地點：北科大光華館
  let location = {
    lat: 25.04420993781886,  // 經度
    lng: 121.53329432406366, // 緯度
  };

  //   if (navigator.geolocation) {
  //     navigator.geolocation.getCurrentPosition(function (position) {
  //       var pos = {
  //         lat: position.coords.latitude,
  //         lng: position.coords.longitude,
  //       };
  //       map.setZoom(16);
  //       map.setCenter(pos);
  //     });

  // }

  // }

  // 建立地圖
  this.map = new google.maps.Map(document.getElementById("map"), {
    center: location,
    zoom: 17,
    mapTypeId: "terrain",

  });

  let aa = new google.maps.Marker({
    position:location,     //position: position,
    map,          //map: map,
});


  // 放置多個marker
  fetch("http://localhost:8080/whattodrink/_00_Index/storeMap.geojson")
    .then((results) => results.json())
    .then((result) => {
      let res = result.features;
      Array.prototype.forEach.call(res, (r) => {
        let latLng = new google.maps.LatLng(
          r.geometry.coordinates[0],
          r.geometry.coordinates[1]
        );
        let marker = new google.maps.Marker({
          position: latLng,
          icon: "http://localhost:8080/whattodrink/_00_Index/mapicon.png",
          map: this.map,
        });
        // info window
        let infowindow = new google.maps.InfoWindow({
          content: `<b>${r.properties.name}</b><br>${r.properties.site}<br><a href="${r.properties.web}">今天喝什麼? 來去看看吧</a>`, // 支援html
        });

        // 監聽 marker click 事件
        marker.addListener("click", (e) => {
          infowindow.open(this.map, marker);
        });
      });
    });

    let storage = sessionStorage;
if(storage.getItem("Address")==null){
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (position) {
          var pos = {
            lat: position.coords.latitude,
            lng: position.coords.longitude,
          };
  
          var geocoder = new google.maps.Geocoder();
  
          // google.maps.LatLng 物件
          var coord = new google.maps.LatLng(
            position.coords.latitude,
            position.coords.longitude
          );
  
          // 傳入 latLng 資訊至 geocoder.geocode
          geocoder.geocode({ latLng: coord }, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
              // 如果有資料就會回傳
              if (results) {
                console.log(results[0].formatted_address);
                let str = results[0].formatted_address;
                str=str.slice(3,str.length);
                
                storage.setItem("currentAddress", str);
                
                  document.getElementById("location").value=storage.getItem("currentAddress");
                  
               



              }
            }
            // 經緯度資訊錯誤
            else {
              alert("Reverse Geocoding failed because: " + status);
            }
          });
        });
     
      }



    }else{
      document.getElementById("location").value=storage.getItem("Address");

    }

    





}

