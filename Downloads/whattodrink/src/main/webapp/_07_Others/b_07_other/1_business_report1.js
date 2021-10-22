$(document).ready(function () {
  $.ajax({
    type: "GET",
    url: "http://localhost:8080/whattodrink/BusinessReportServlet",
    dataType: "json",
    success: function (response) {
	console.log(response);
      startTime = response.start_time;
      endTime = response.end_time;
      timeSession = response.countByHour;
      ordQuantity = response.orderCount;
      ordTotal = response.total;
     
	  if(ordQuantity==0&&ordTotal==0){
//		  alert();
		  ordAvg=0;
	  }else{
	  	  ordAvg = Math.round(ordTotal / ordQuantity);
	  }
      quan = `<span class="ms-3">${ordQuantity}\ 筆</span>`;
      total = `<span class="ms-3">${ordTotal}\ 元</span>`;
      avg = `<span class="ms-3">${ordAvg}\ 元</span>`;
      ordQ.insertAdjacentHTML("beforeend", quan);
      ordT.insertAdjacentHTML("beforeend", total);
      ordA.insertAdjacentHTML("beforeend", avg);

      //判斷營業時間的時段
      labels = [];
      for (
        var i = parseInt(startTime.substring(0, 2));
        i < parseInt(endTime.substring(0, 2));
        i++
      ) {
        labels.push(`${i}-${i + 1}\ hr`);
      }
      // console.log(labels);

      //判斷時段資料
      if (parseInt(startTime) == 9) {
        startTime = 0;
      } else if (parseInt(startTime) == 10) {
        startTime = 1;
      } else if (parseInt(startTime) == 11) {
        startTime = 2;
      } else if (parseInt(startTime) == 12) {
        startTime = 3;
      }
      if (parseInt(endTime) == 21) {
        endTime = 12;
      } else if (parseInt(endTime) == 22) {
        endTime = 13;
      } else if (parseInt(endTime) == 23) {
        endTime = 14;
      }

      //判斷時段單數
      t = [];
      for (let i = startTime; i < endTime; i++) {
        t.push(timeSession[i]);
      }
      // console.log(t);

      //動態新增圖表
      var ctx = $("#myChart");
      var data = t;
      var labels = labels;
      var myChart = new Chart(ctx, {
        type: "bar",
        data: {
          labels: labels,
          datasets: [
            {
              label: "",
              data: data,
              backgroundColor: [
                "rgba(255, 99, 132, 0.2)",
                "rgba(255, 159, 64, 0.2)",
                "rgba(255, 205, 86, 0.2)",
                "rgba(75, 192, 192, 0.2)",
                "rgba(30, 49, 99,0.2)",
                "rgba(5, 80, 82, 0.2)",
                "rgba(153, 102, 255, 0.2)",
                "rgb(255, 36, 66,0.2)",
                "rgb(27, 26, 23,0.2)",
                "rgba(61, 178, 255, 0.2)",
                "rgba(240, 55, 165, 0.2)",
                "rgba(134, 84, 57,0.2)",
              ],
            },
          ],
        },
        options: {
          scales: {
            y: {
              min: 0,
              ticks: {
                stepSize: 1,
              },
            },
          },
        },
      });
    },
  });
});
