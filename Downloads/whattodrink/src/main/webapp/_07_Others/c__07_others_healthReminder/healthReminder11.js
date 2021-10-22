$(document).ready(function () {
  //請求體重
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/whattodrink/HealthReminderServlet",
    dataType: "json",
    success: function (response) {
	console.log(response[0].weight);
      var weight = response[0].weight;
      var calTotal = 0;
      var str = "";
      for (var i = 0; i < response.length; i++) {
        calTotal += response[i].item_cal;
        str += `
        <p>
        <span class="text-secondary">${response[i].time}<br /></span>
        <span>${response[i].product_name}&nbsp;${response[i].capacity}&nbsp;
        ${response[i].sugar_level}&nbsp;${response[i].item_cal}大卡<br /></span>
        </p>
        `;
      }
      content.insertAdjacentHTML("afterbegin", str);

      $("#cal").text(calTotal);
      $("#weight").val(weight);
      if ((weight >= 40 && weight < 50) || weight < 40) {
        $("#run_time").text(Math.round((calTotal / 164) * 30));
        $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 164));
        $("#bike_time").text(Math.round((calTotal / 80) * 30));
        $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 80));
        $("#walk_time").text(Math.round((calTotal / 110) * 30));
        $("#walk_cal").text(Math.round(($("#walk_time").text() / 30) * 110));
      } else if (weight >= 50 && weight < 60) {
        $("#run_time").text(Math.round((calTotal / 205) * 30));
        $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 205));
        $("#bike_time").text(Math.round((calTotal / 100) * 30));
        $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 100));
        $("#walk_time").text(Math.round((calTotal / 137.5) * 30));
        $("#walk_cal").text(Math.round(($("#walk_time").text() / 30) * 137.5));
      } else if (weight >= 60 && weight < 70) {
        $("#run_time").text(Math.round((calTotal / 246) * 30));
        $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 246));
        $("#bike_time").text(Math.round((calTotal / 120) * 30));
        $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 120));
        $("#walk_time").text(Math.round((calTotal / 165) * 30));
        $("#walk_cal").text(Math.round(($("#walk_time").text() / 30) * 165));
      } else if (weight >= 70) {
        $("#run_time").text(Math.round((calTotal / 287) * 30));
        $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 287));
        $("#bike_time").text(Math.round((calTotal / 140) * 30));
        $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 140));
        $("#walk_time").text(Math.round((calTotal / 192.5) * 30));
        $("#walk_cal").text(Math.round(($("#walk_time").text() / 30) * 192.5));
      }

      // 輸入體重
      $("#weight").keydown(function(event) {
		if (event.keyCode == 13) {
			event.preventDefault();
      var weight = $("#weight").val();
      console.log($("#weight").val());
      if (weight >= 40 && weight < 50) {
        $("#run_time").text(Math.round((calTotal / 164) * 30));
        $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 164));
        $("#bike_time").text(Math.round((calTotal / 80) * 30));
        $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 80));
        $("#walk_time").text(Math.round((calTotal / 110) * 30));
        $("#walk_cal").text(Math.round(($("#walk_time").text() / 30) * 110));
      } else if (weight >= 50 && weight < 60) {
        $("#run_time").text(Math.round((calTotal / 205) * 30));
        $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 205));
        $("#bike_time").text(Math.round((calTotal / 100) * 30));
        $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 100));
        $("#walk_time").text(Math.round((calTotal / 137.5) * 30));
        $("#walk_cal").text(
          Math.round(($("#walk_time").text() / 30) * 137.5)
        );
      } else if (weight >= 60 && weight < 70) {
        $("#run_time").text(Math.round((calTotal / 246) * 30));
        $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 246));
        $("#bike_time").text(Math.round((calTotal / 120) * 30));
        $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 120));
        $("#walk_time").text(Math.round((calTotal / 165) * 30));
        $("#walk_cal").text(Math.round(($("#walk_time").text() / 30) * 165));
      } else if (weight >= 70) {
        $("#run_time").text(Math.round((calTotal / 287) * 30));
        $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 287));
        $("#bike_time").text(Math.round((calTotal / 140) * 30));
        $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 140));
        $("#walk_time").text(Math.round((calTotal / 192.5) * 30));
        $("#walk_cal").text(
          Math.round(($("#walk_time").text() / 30) * 192.5)
        );
      }
			
			}
			});
      
      $("#weight").blur(function (e) {
        e.preventDefault();
        var weight = $("#weight").val();
        console.log($("#weight").val());
        if (weight >= 40 && weight < 50) {
          $("#run_time").text(Math.round((calTotal / 164) * 30));
          $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 164));
          $("#bike_time").text(Math.round((calTotal / 80) * 30));
          $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 80));
          $("#walk_time").text(Math.round((calTotal / 110) * 30));
          $("#walk_cal").text(Math.round(($("#walk_time").text() / 30) * 110));
        } else if (weight >= 50 && weight < 60) {
          $("#run_time").text(Math.round((calTotal / 205) * 30));
          $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 205));
          $("#bike_time").text(Math.round((calTotal / 100) * 30));
          $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 100));
          $("#walk_time").text(Math.round((calTotal / 137.5) * 30));
          $("#walk_cal").text(
            Math.round(($("#walk_time").text() / 30) * 137.5)
          );
        } else if (weight >= 60 && weight < 70) {
          $("#run_time").text(Math.round((calTotal / 246) * 30));
          $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 246));
          $("#bike_time").text(Math.round((calTotal / 120) * 30));
          $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 120));
          $("#walk_time").text(Math.round((calTotal / 165) * 30));
          $("#walk_cal").text(Math.round(($("#walk_time").text() / 30) * 165));
        } else if (weight >= 70) {
          $("#run_time").text(Math.round((calTotal / 287) * 30));
          $("#run_cal").text(Math.round(($("#run_time").text() / 30) * 287));
          $("#bike_time").text(Math.round((calTotal / 140) * 30));
          $("#bike_cal").text(Math.round(($("#bike_time").text() / 30) * 140));
          $("#walk_time").text(Math.round((calTotal / 192.5) * 30));
          $("#walk_cal").text(
            Math.round(($("#walk_time").text() / 30) * 192.5)
          );
        }
      });
    },
  });
});

// 運動項目
// 40KG
// 50KG
// 60KG
// 70KG

// 慢跑
// 164
// 205
// 246
// 287

// 騎腳踏車
// 80
// 100
// 120
// 140

// 快走
// 110
// 137.5
// 165
// 192.5
