//載入頁面時請求後端資料
$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/whattodrink/AddCategoryServlet",
		dataType: "json",
		success: function(response) {
			console.log(response);
			$("#name1").val(response.name[0]);
			$("#name2").val(response.name[1]);
			$("#name3").val(response.name[2]);
			$("#name4").val(response.name[3]);
			$("#name5").val(response.name[4]);
			$("#name6").val(response.name[5]);
			$("#name7").val(response.name[6]);
			$("#name8").val(response.name[7]);
			$("#name9").val(response.name[8]);
			$("#name10").val(response.name[9]);
			//動態新增顯示在右邊
			var show_result = "";
			for (let i = 0; i < 10; i++) {
				show_result += `
        <span>${response.name[i]}</span></br>
        `;
			}
			result.insertAdjacentHTML("afterbegin", show_result);
			if ($("#name1").val() != "") {
        $("#name1").addClass("d-none");
      }
      if ($("#name2").val() != "") {
        $("#name2").addClass("d-none");
      }
      if ($("#name3").val() != "") {
        $("#name3").addClass("d-none");
      }
      if ($("#name4").val() != "") {
        $("#name4").addClass("d-none");
      }
      if ($("#name5").val() != "") {
        $("#name5").addClass("d-none");
      }
      if ($("#name6").val() != "") {
        $("#name6").addClass("d-none");
      }
      if ($("#name7").val() != "") {
        $("#name7").addClass("d-none");
      }
      if ($("#name8").val() != "") {
        $("#name8").addClass("d-none");
      }
      if ($("#name9").val() != "") {
        $("#name9").addClass("d-none");
      }
      if ($("#name10").val() != "") {
        $("#name10").addClass("d-none");
      }
		},
	});
});
