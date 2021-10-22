
// 左右滑動設定
$(document).on("ready", function() {
	$(".regular").slick({
		dots: true,
		infinite: true,
		autoplay: true,
		speed: 1000,
		slidesToShow: 3,
		slidesToScroll: 1,
		responsive: [
			{
				breakpoint: 1100,
				settings: {
					slidesToShow: 2,
					infinite: true,
				},
			},
			{
				breakpoint: 700,
				settings: {
					slidesToShow: 1,
					slidesToScroll: 1,
					infinite: true,
				},
			},
		],
	});

	$(".regular1").slick({
		dots: true,
		infinite: true,
		autoplay: true,
		speed: 1000,
		slidesToShow: 4,
		slidesToScroll: 1,
		responsive: [
			{
				breakpoint: 1024,
				settings: {
					slidesToShow: 3,
					infinite: true,
				},
			},
			{
				breakpoint: 600,
				settings: {
					slidesToShow: 2,
					infinite: true,
				},
			},
			{
				breakpoint: 380,
				settings: {
					slidesToShow: 1,
					slidesToScroll: 1,
					infinite: true,
				},
			},
		],
	});

	$(".regular2").slick({
		infinite: true,
		slidesToShow: 11,
		slidesToScroll: 2,
		autoplay: false,
		speed: 1000,
		arrows: false,
		responsive: [
			{
				breakpoint: 1024,
				settings: {
					slidesToShow: 6,
					slidesToScroll: 2,
				},
			},
			{
				breakpoint: 600,
				settings: {
					slidesToShow: 5,
					slidesToScroll: 1,
				},
			},
			{
				breakpoint: 380,
				settings: {
					slidesToShow: 4,
					slidesToScroll: 1,
				},
			},
		],
	});
});



	//複製邀請碼右方彈跳文字
	var popoverTriggerList = [].slice.call(
		document.querySelectorAll('[data-bs-toggle="popover"]')
	);
	var popoverList = popoverTriggerList.map(function(popoverTriggerEl) {
		return new bootstrap.Popover(popoverTriggerEl);
	});




// 我的最愛
function change(heart) {
	console.log(heart.id);
	if (heart.className == "far fa-heart fa-lg heart") {
		console.log('空心轉實心');
		$.ajax({
			url: "http://localhost:8080/whattodrink/AddDeleteMyFavoriteServlet",
			type: "post",
			data: {
				company_id: heart.id
			},
			success(res) {
				console.log(res);
				heart.className = "fas fa-heart fa-lg heart";


			}
		});

	} else {
		console.log('實星轉空星');
		$.ajax({

			url: "http://localhost:8080/whattodrink/AddDeleteMyFavoriteServlet",
			type: "post",
			data: {
				company_id: heart.id
			},
			success(res) {
				console.log(res);
				heart.className = "far fa-heart fa-lg heart";

			}
		});

	}
}



