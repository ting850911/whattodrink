if (location.pathname.match('ByPrice')) {
	$("#price").attr("checked", true);
}
if (location.pathname.match('ByCal')) {
	$("#calories").attr("checked", true);
}

if (location.pathname.match('ByTag') || location.pathname.match('ByNative')) {
	$("#distance").attr("checked", true);
}



//複製邀請碼右方彈跳文字
var popoverTriggerList = [].slice.call(
	document.querySelectorAll('[data-bs-toggle="popover"]')
);
var popoverList = popoverTriggerList.map(function(popoverTriggerEl) {
	return new bootstrap.Popover(popoverTriggerEl);
});


//判斷是否為營業時間
function checkAuditTime(beginTime,endTime) {
	let nowDate=new Date();
	let beginDate=new Date(nowDate);
	let endDate=new Date(nowDate);
	
	let beginIndex=beginTime.lastIndexOf("\:");
	let beginHour =beginTime.substring(0,beginIndex);
	let beginMinute=beginTime.substring(beginIndex+1,beginIndex.length);
	
	beginDate.setHours(beginHour,beginMinute,0,0);
	
	
	let endIndex=endTime.lastIndexOf("\:");
	let endHour =endTime.substring(0,endIndex);
	let endMinute=endTime.substring(endIndex+1,endIndex.length);
	
	endDate.setHours(endHour,endMinute,0,0);
	
	if(nowDate.getTime()-beginDate.getTime()>=0&&nowDate.getTime()<=endDate.getTime()){
		return true;
	}else{
		return false;
	}
		
		
	}
	
const openinghoursModal = document.getElementById("openinghoursModal");
const OpeninghoursModal = new bootstrap.Modal(openinghoursModal, { keyboard: false });





//飲料名稱點擊選配料JS

const addCartAlert = document.getElementById('addCartAlert');
const addCartAlertModal = new bootstrap.Modal(addCartAlert, { keyboard: true });


const clearModal = document.getElementById("clearModal");
const modal3 = new bootstrap.Modal(clearModal, { keyboard: false });

$(".allshop img").click(function(e) {

let startTime=e.target.previousElementSibling.previousElementSibling.previousElementSibling.value;	
let endTime=e.target.previousElementSibling.previousElementSibling.value;	


if(checkAuditTime(startTime,endTime)==false){
	$('#openinghoursModalmodalbody').empty();
	$('#openinghoursModalmodalbody').text(`營業時間為${startTime}~${endTime}`);
	OpeninghoursModal.show();
}else{
	console.log(e.target.previousElementSibling.value);
	$.ajax({
		url: "http://localhost:8080/whattodrink/_03_ListDrinks/DrinkDetail",
		type: "POST",
		data: { productId: e.target.previousElementSibling.value },
		dataType: "json",
		success(res) {
			console.log(res);
			
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

			$('#quantity').val(1);

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

			$("input[name=ice]")[0].checked = true;
			$("input[name=sweet]")[0].checked = true;
		}
	});


	addCartAlertModal.show();
	}
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


// 新增飲品送出



$("#submitbtn").click(function() {

	addCartAlertModal.hide();
	selected = [];
	$("[name=addon]:checkbox:checked").each(function() {
		selected.push($(this).val());
	});

	$.ajax({
		url: "http://localhost:8080/whattodrink/_04_ShoppingCart/AddToCartServlet",
		type: "POST",
		data: {
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
			//alert('成功傳向後端!');
			console.log(res);
			if (res == "differentCompany") {


				addCartAlertModal.hide();
				modal3.show();
			}


		}
	});



});

//清空購物車start
$("#clearbtn").click(function() {
	modal3.hide();
	$.ajax({
		url: "http://localhost:8080/whattodrink/_04_ShoppingCart/deleteCartServlet",
		type: "POST",
		data: {
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
			alert('更換成功!');

		}
	});

})

//清空購物車end



//左方排序

let tt = $('#tagNameOrKeyword').val();
let mm = $('#searchMethod').val();
function goprice() {

	let gg = `http://localhost:8080/whattodrink/RetrieveDrinksByPriceServlet?searchMethod=${mm}&tagNameOrKeyword=${tt}`;

	window.location.assign(gg);

};

function gocal() {
	let gg = `http://localhost:8080/whattodrink/RetrieveDrinksByCalServlet?searchMethod=${mm}&tagNameOrKeyword=${tt}`;
	window.location.assign(gg);
};

function godistance() {
	let tag = `http://localhost:8080/whattodrink/_03_listDrinks/RetrieveDrinksByTag?tagName=${tt}`;
	let native = `http://localhost:8080/whattodrink/_03_listDrinks/RetrieveDrinksByNative?keyword=${tt}`;

	if (location.search.match('tagSearch')) {
		window.location.assign(tag);
	} else if (location.search.match('nativeSearch')) {
		window.location.assign(native);
	}
};
