
//一載入撈公司名稱地址
let namecompany = $('#company_name').val();
let trade_name = $('#trade_name').val();
let addresscompany = $('#company_address').val();
let company_id = $('#company_id').val();
$('#map').append(`<iframe src="https://maps.google.com?output=embed&q=${addresscompany}" width="100%"
                height="200"
                frameborder="0"></iframe>`);

$('#address').append(`<i class="fas fa-map-marker-alt fa-lg"></i>${addresscompany}`);
$('#companyinfo').append(`${namecompany}-${trade_name}`);



$('#gobackshop').click(function() {

	window.location.assign(`http://localhost:8080/whattodrink/_03_ListDrinks/StorePage?companyId=${company_id}`);

});

//靠選單修改飲品數量
$(".item-select").change(function(e) {

	$.ajax({
		url: "http://localhost:8080/whattodrink/_04_ShoppingCart/UpdateItemQuantityServlet",
		type: "POST",
		data: {
			cartKey: e.target.parentElement.nextElementSibling.firstElementChild.value,
			quantity: e.target.value
		},
		dataType: "json",
		success(res) {
			//				alert('成功傳向後端!');
			console.log(res);
			e.target.parentElement.nextElementSibling.nextElementSibling.firstElementChild.innerText = `$${res[0]}`;
			$('#subtotal').text(`總計$${res[1]}`);
			$(`#${e.target.parentElement.nextElementSibling.firstElementChild.value}quantity`).attr("value", e.target.value);


			if (e.target.value == 0) {

				$(`#${e.target.parentElement.nextElementSibling.firstElementChild.value}item`).next().remove();
				$(`#${e.target.parentElement.nextElementSibling.firstElementChild.value}item`).remove();
				let pp = $('#subtotal').text();

				if (parseInt(pp.substring(3)) == 0) {
					window.location.assign("http://localhost:8080/whattodrink/_00_Index/index.jsp");
				}

			}

		}

	});


});



// 加入健康提醒的modal
const messageDom = document.getElementById("healthReminder");
const modal = new bootstrap.Modal(messageDom, { keyboard: false });

//點擊加入健康提醒按鈕
$(".addhealthReminder").click(function(e) {

	$('#ittkey').attr("value", e.target.parentElement.parentElement.previousElementSibling.previousElementSibling.children[1].firstElementChild.value)

	$('#healthReminder_productname').text(e.target.parentElement.parentElement.previousElementSibling.previousElementSibling.children[1].lastElementChild.innerText);





	let count1 = parseInt(e.target.parentElement.parentElement.parentElement.firstElementChild.firstElementChild.children[1].value);

	//依飲品數量動態新增下拉選單
	while (floatingSelect.hasChildNodes()) {
		floatingSelect.removeChild(floatingSelect.firstChild);
	}
	for (i = 0; i <= count1; i++) {
		$("#floatingSelect").append(`<option value="${i}">${i}</option>`);
	}

	$("#floatingSelect").val(e.target.nextElementSibling.value);

	if ($("#floatingSelect").val() > 1) {
		let key = $('#ittkey').val();
		let cal = $(`#${key}item_cal`).val();
		let count = $("#floatingSelect").val();
		$('#healthReminder_productcal').text(`${count}杯熱量約為${cal * count}`);
	} else {
		let key = $('#ittkey').val();
		let cal = $(`#${key}item_cal`).val();
		$('#healthReminder_productcal').text(`1杯熱量約為${cal}`);
	}
	modal.show();


})



//健康提醒熱量試算

$("#floatingSelect").change(function() {

	if ($("#floatingSelect").val() > 1) {
		let key = $('#ittkey').val();
		let cal = $(`#${key}item_cal`).val();
		let count = $("#floatingSelect").val();
		$('#healthReminder_productcal').text(`${count}杯熱量約為${cal * count}`);
	} else {
		let key = $('#ittkey').val();
		let cal = $(`#${key}item_cal`).val();
		$('#healthReminder_productcal').text(`1杯熱量約為${cal}`);
	}

})



//若頁面重整判斷有無加入健康提醒
for (i = 0; i < 50; i++) {
	if (parseInt($(`#${i}add_to_health`).val()) > 0) {

		$(`#${i}addhealthReminder`).attr("class", "fas fa-check-circle");

	} else {
		$(`#${i}addhealthReminder`).attr("class", "fas fa-plus");


	};
}




//送出健康提醒
$('#savecal').click(function(c) {
	modal.hide();
	//hh是獲得itemkey
	let hh = c.target.nextElementSibling.value;

	$(`#${hh}add_to_health`).attr("value", c.target.parentElement.previousElementSibling.firstElementChild.firstElementChild.value);


	if (parseInt($(`#${hh}add_to_health`).val()) > 0) {

		$(`#${hh}addhealthReminder`).attr("class", "fas fa-check-circle");

	} else {
		$(`#${hh}addhealthReminder`).attr("class", "fas fa-plus");


	};
	$.ajax({
		url: "http://localhost:8080/whattodrink/_04_ShoppingCart/AddToHealthServlet",
		type: "POST",
		data: {
			cartKey: c.target.nextElementSibling.value,
			addToHealth: c.target.parentElement.previousElementSibling.firstElementChild.firstElementChild.value
		},
		dataType: "json",
		success(res) {
			console.log(res);


		}

	});


});




// 挑選領取時間
const messageDom1 = document.getElementById("selectTimeModal");
const today = document.getElementById("today");
const modal1 = new bootstrap.Modal(messageDom1, { keyboard: false });

$("input[name='time'][value='selectTime'],#selectTime").click(function() {
	let tt = new Date();
	today.innerText =
		tt.getFullYear() +
		" 年 " +
		(tt.getMonth() + 1) +
		" 月 " +
		tt.getDate() +
		" 日";
	modal1.show();
});

// 挑選時間


let date_now = new Date();
let hour = date_now.getHours();

let minute = parseInt(date_now.getMinutes()) + 30;
if (minute >= 60) {
	minute = minute - 60;
	hour = hour + 1;
}



let endTime30 = $('#company_end_time').val();
let endTime1 = endTime30.substring(0, 2);
let endTime2 = endTime30.substring(3, 5);
endTime2 = endTime2 - 30;
if (endTime2 < 0) {
	endTime2 = endTime2 + 60;
	endTime1 = endTime1 - 1;
} else if (endTime2 == 0) {
	endTime2 = "00"
}
endTime30 = `${endTime1}:${endTime2}`



$('.timepicker').timepicker({
	timeFormat: 'HH:mm:ss',
	interval: 15,
	minTime: `${hour}:${minute.length == 1 ? `0${minute}` : minute}`,
	maxTime: endTime30,
	defaultTime: `${hour}:${minute.length == 1 ? `0${minute}` : minute}`,
	startTime: $('#company_start_time').val(),
	dynamic: false,
	dropdown: true,
	scrollbar: true,
	zindex: 9999999
});


let storage = sessionStorage;

function timesave() {
	//如果選取時間合法，將存進localstorage並且關閉modal
	if (document.getElementById("appt-time").validity.valid == true) {
		storage.setItem("time", $("#appt-time").val());
		$("#selectTimeModal").modal("hide");
	}
}

if (storage.getItem("time") != null) {
	$("#selectTime").html(`${storage.getItem("time")}<span
  class="border border-1 rounded-1 p-1 border-dark mx-2"
  style="font-size: x-small"
  >編輯</span
>`);
	$("input[name='time'][value='now']").attr("checked", ""); //設定不打勾
	$("input[name='time'][value='selectTime']").attr("checked", true); //設定打勾

}

// 點飲料標題跳出修改飲品
const modifyModal = document.getElementById("modifyModal");
const modal2 = new bootstrap.Modal(modifyModal, { keyboard: false });

$(".itemtile").click(function(e) {

	$('#itemkeyyy').attr("value", e.target.parentElement.firstElementChild.value);

	$.ajax({
		url: "http://localhost:8080/whattodrink/_03_ListDrinks/DrinkDetail",
		type: "POST",
		data: { productId: e.target.previousElementSibling.value },
		dataType: "json",
		success(res) {
			addon = document.getElementById('addon');
			while (addon.hasChildNodes()) {
				addon.removeChild(addon.firstChild);
			}
			icebtn = document.getElementById('icebtn');
			while (icebtn.hasChildNodes()) {
				icebtn.removeChild(icebtn.firstChild);
			}

sweetbtn = document.getElementById('sweetbtn');
			while (sweetbtn.hasChildNodes()) {
				sweetbtn.removeChild(sweetbtn.firstChild);
			}


			$('#productname').text(res.drinkNameAndCapacity[0] + res.drinkNameAndCapacity[1]);

			$('#productId').val(e.target.previousElementSibling.value);

			let message1 = '';
			for (let i = 0; i < res.toppingIdJson.length; i++) {
				message1 += `<input type="checkbox" class="btn-check" name="addon"  value="${res.toppingIdJson[i]}" id="addon${i}" autocomplete="off" />
          <label class="btn btn-outline-dark" for="addon${i}">`;
				message1 += res.toppingNameJson[i];
				message1 += `+`;
				message1 += res.toppingPriceJson[i];
				message1 += `</label>`;
			}




			let message2 = '';
			for (let i = 0; i < res.tempIdJson.length; i++) {
				message2 += `<input type="radio" class="btn-check" name="ice" value="${res.tempIdJson[i]}" id="ice${i}" autocomplete="off"/>
        <label class="btn btn-outline-dark" for="ice${i}">`;
				message2 += res.tempNameJson[i];
				message2 += `</label>`;
			}


			let message3 = '';
			for (let i = 0; i < res.sugarIdJson.length; i++) {
				message3 += `<input type="radio" class="btn-check" name="sweet" value="${res.sugarIdJson[i]}" id="sweet${i}" autocomplete="off"/>
            <label class="btn btn-outline-dark" for="sweet${i}">`;
				message3 += res.sugarNameJson[i];
				message3 += `</label>`;
			}

			$('#sweetbtn').append(message3);
			$('#icebtn').append(message2);
			$('#addon').append(message1);

			$('#note').val((e.target.parentElement.parentElement.nextElementSibling.lastElementChild.lastElementChild.children[6].innerText).replace(/\s+/g, ''));
			$('#message').val((e.target.parentElement.parentElement.nextElementSibling.lastElementChild.lastElementChild.children[8].innerText).replace(/\s+/g, ''));

			console.log(e.target.parentElement.previousElementSibling.firstElementChild.value);
			document.getElementById('quantity').value = $(`#${e.target.parentElement.firstElementChild.value}quantityoption`).val();

			let ice = e.target.previousElementSibling.previousElementSibling.value;
			let sweet = e.target.previousElementSibling.previousElementSibling.previousElementSibling.value;

			document.querySelector(`input[name="ice"][value="${ice}"]`).setAttribute("checked", "true");
			document.querySelector(`input[name="sweet"][value="${sweet}"]`).setAttribute("checked", "true");

			let hh = e.target.parentElement.firstElementChild.value;
			let toppingidarray = $(`#${hh}topping_id`).text();
			let finaltoppingidarray = toppingidarray.split(',');

			for (let i = 0; i < finaltoppingidarray.length; i++) {
				console.log(finaltoppingidarray[i]);
				document.querySelector(`input[name="addon"][value="${finaltoppingidarray[i]}"]`).setAttribute("checked", "true");
			}


		}
	});


	modal2.show();



});





//傳送修改後的飲品


$("#submitbtn").click(function(e) {

	modal2.hide();

	let selected = [];
	$("[name=addon]:checkbox:checked").each(function() {
		selected.push($(this).val());
	});

	$.ajax({
		url: "http://localhost:8080/whattodrink/_04_ShoppingCart/UpdateItemServlet",
		type: "POST",
		data: {
			cartKey: e.target.previousElementSibling.value,
			productId: $('#productId').val(),
			tempId: $('[name=ice]:checked').val(),
			sugarId: $('[name=sweet]:checked').val(),
			addon: JSON.stringify(selected),
			note: $('#note').val(),
			message: $('#message').val(),
			quantity: $('#quantity').val()
		},
		dataType: "json",
		success(res) {
			//          alert('成功傳向後端!');
			console.log(res);
			if (res[0] == 0) {


				$(`#${e.target.previousElementSibling.value}sugar_ID`).attr("value", $('[name=sweet]:checked').val());

				$(`#${e.target.previousElementSibling.value}temp_ID`).attr("value", $('[name=ice]:checked').val());
				if ($('#note').val() == "") {
					$(`#${e.target.previousElementSibling.value}note`).html("&nbsp;");
				} else {
					$(`#${e.target.previousElementSibling.value}note`).text($('#note').val());
				}

				if ($('#message').val() == "") {
					$(`#${e.target.previousElementSibling.value}message`).html("&nbsp;");
				} else {
					$(`#${e.target.previousElementSibling.value}message`).text($('#message').val());
				}


				$(`#${e.target.previousElementSibling.value}quantity`).attr("value", $('#quantity').val());
				$(`#${e.target.previousElementSibling.value}quantityoption`).val($('#quantity').val());
				$(`#${e.target.previousElementSibling.value}item_cal`).attr("value", res[2]);
				$(`#${e.target.previousElementSibling.value}topping_id`).text(selected);
				$(`#${e.target.previousElementSibling.value}temp_level`).text($('[name=ice]:checked').next().text());
				$(`#${e.target.previousElementSibling.value}sugar_level`).text($('[name=sweet]:checked').next().text());
				$(`#${e.target.previousElementSibling.value}itemprice`).text(`$${res[1]}`);

				let finaltopping11 = "";
				for (let i = 0; i < selected.length; i++) {
					let ss = $(`[name="addon"][value=${selected[i]}]`).next().text();
					ss = ss.split('+');
					ss = ss[0];
					finaltopping11 += ss;
					finaltopping11 += '\xa0';
				}

				$(`#${e.target.previousElementSibling.value}topping_name`).text(finaltopping11);
				$('#subtotal').text(`總計$${res[3]}`);





			} else {
				location.reload();

			}
		}


	});
});



// 購物車加減

$(function() {
	$(".add").click(function() {
		var t = $(this).parent().find("input[class*=text_box]");
		t.val(parseInt(t.val()) + 1);

	});


	$(".min").click(function() {

		var t = $(this).parent().find("input[class*=text_box]");
		t.val(parseInt(t.val()) - 1);

		if (parseInt(t.val()) < 1) {
			t.val(1);
		}


	});
});

// 提醒先登入
const loginModal = document.getElementById("loginModal");
const modal3 = new bootstrap.Modal(loginModal, { keyboard: false });


//點擊去付款
const gotopay = document.getElementById("gotopay");
gotopay.addEventListener("click", function() {
	let tt = new Date();
	tt = tt.getFullYear() + "-" + `${(tt.getMonth() + 1).toString().length == 1 ? `0${tt.getMonth() + 1}` : tt.getMonth() + 1}` + "-" + `${tt.getDate().toString().length == 1 ? `0${tt.getDate()}` : tt.getDate()}`;

	var result = "";
	$("input[name=time]:checked").each(function() {
		result = $(this).val();
	});

	if (result == "selectTime") {
		result = tt + " " + storage.getItem("time");
	}
	$.ajax({
		url: "http://localhost:8080/whattodrink/_05_Order/PaymentServlet",
		type: "POST",
		data: {
			scheduled_time: result
		},
		dataType: "json",
		success(res) {
			if (res == "nologin") {
				modal3.show();
			} else {
				window.location.assign("http://localhost:8080/whattodrink/_04_ShoppingCart/payment.jsp");

			}
		}

	});

});


function LoginRegister() {
	window.location.assign("http://localhost:8080/whattodrink/_01_Register/c_01_register/LoginRegister.jsp");
}


