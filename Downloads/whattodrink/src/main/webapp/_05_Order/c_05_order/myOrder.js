$(document).ready(function() {
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/whattodrink/C_myOrderHistory",
		dataType: "json",
		cache: false,
		async:false,
		success: function(response) {
			renderHTML(response);
		},
	});

	//過去的訂單
	function renderHTML(data) {
		var str = "";
		for (var i = 0; i < data.length-1; i++) {
			str += `
      <div class="row">
      <div class="col-sm-2">
        <figure class="figure">
          <a href="">
            <img
              class="ratio ratio-4x3"
              src="../../${data[i].picPath}"
            />
          </a>
        </figure>
      </div>
      <div class="col-sm-4 mb-2">
        <h5>${data[i].storeName}\・${data[i].branchName}</h5>
        <p class="text-secondary">${data[i].ordTime}</p>
      </div>
      <div class="col-sm-3 mb-3">
        <p style="margin: 0px 0px 8px 0px">
          <span>訂單狀態</span><span>&nbsp;${data[i].ordState}</span>
        </p>
        <p id="${data[i].ordId}" class="no_margin pp">
          <a
            style="text-decoration: none"
            role="button"
            class="me-2"
            >檢視電子明細</a
          >
        </p>
      </div>
      <div class="col-sm-3 my-auto d-flex flex-column align-items-end">
        <button type="submit" class="btn btn-dark mb-3 my-btn-width dd">
          重新訂購
        </button>
        <button type="button" class="btn btn-dark my-btn-width bb">
          ${data[i].review}
        </button>
      </div>
    </div>
    <hr class="my-4" style="background-color: black" />
    `;
		}
		oldOrd.insertAdjacentHTML("afterend", str);

		//重新訂購
		$(".dd").click(function(e) {
			let ordId =
				e.target.parentElement.previousElementSibling.lastElementChild.id;
			console.log(ordId);
			window.location
				.assign				 
				(`http://localhost:8080/whattodrink/C_myOrderBuyItAgain?order_id=${ordId}`);
		});

		//去評價跳轉
		$(".bb").click(function(e) {
			let ordId =
				e.target.parentElement.previousElementSibling.lastElementChild.id;
			console.log(ordId);
			if (e.target.innerText == "去評價") {
				window.location.assign(
					`http://localhost:8080/whattodrink/AddCommentsServlet?order_id=${ordId}`
				);
			}
		});

		//開啟評價modal
		$(".bb").click(function(e) {
			if (e.target.innerText == "已評價") {
				const modifyModal = document.getElementById("review");
				const reviewModal = new bootstrap.Modal(modifyModal, {
					keyboard: false,
				});
				console.log(
					e.target.parentElement.previousElementSibling.lastElementChild.id
				);
				$.ajax({
					type: "GET",
					url: "http://localhost:8080/whattodrink/C_myOrderPastComment",
					data: {
						reviewId:
							e.target.parentElement.previousElementSibling.lastElementChild.id,
					},
					dataType: "json",
					success: function(response) {
						commentStr = "";
						for (let i = 0; i < response.length; i++) {
							var stars = response[i].star;
							var stars2 = "";
							for (let j = 0; j < stars; j++) {
								stars2 += `<i class="text-warning fas fa-star"></i>`;
							}
							commentStr += `
                <div class="row mb-3 tr">
                <span class="d-none ss">${response[i].star}</span>
                  <div class="col-sm-6">
                    <img
                      class="ratio ratio-4x3"
                      src="../../${response[i].picPath}"
                      style="max-width: fit-content"
                    />
                  </div>
                  <div class="col-sm-6">
                    <p class="fw-bold fs-5 m-0">${response[i].itemName}</p>
                    <div class="rating_stars my-1">${stars2}
                    </div>
                    <p class="m-0">${response[i].feedback}</p>
                  </div>
                </div>
              `;
						}
						$("#review2").append(commentStr);
						$("#reviewClose").click(function() {
							$(".tr").remove();
						});
					},
				});

				reviewModal.show();
			}
		});
	}

	//目前的訂單
	$.ajax({
		type: "GET",
		url: "http://localhost:8080/whattodrink/C_myOrderCurrent",
		dataType: "json",
		success: function(response) {
			console.log(response);
			if (`${response[0].newOrd}` == 1) {
				$("#newOrd").removeClass("d-none");
				var str2 = "";
				for (let j = 1; j < response.length; j++) {
					str2 += `
          <div class="row">
              <div class="col-sm-2">
                <figure class="figure">
                  <a href="">
                    <img
                      class="ratio ratio-4x3"
                      src="../../${response[j].picPath}"
                    />
                  </a>
                </figure>
              </div>
              <div class="col-sm-4 mb-2">
                <h5>${response[j].storeName}\・${response[j].branchName}</h5>
                <p class="text-secondary">${response[j].ordTime}</p>
              </div>
              <div class="col-sm-3 mb-3">
                <p style="margin: 0px 0px 8px 0px">
                  <span>訂單狀態</span
                  ><span class="text-danger">&nbsp;${response[j].ordState}</span>
                </p>
                <p id="${response[j].ordId}" class="no_margin">
                  <a
                    style="text-decoration: none"
                    class="me-2 pp"
                    role="button"
                    >檢視電子明細<br
                  /></a>
                  <a
                    style="text-decoration: none"
                    class="me-2 number"
                    role="button"
                    >檢視號碼牌</a
                  >
                </p>
              </div>
            </div>
            <hr class="my-4" style="background-color: black" />
          `;
					
				}
				newOrd.insertAdjacentHTML("afterend", str2);
			}
				

			//開啟號碼牌modal
			const modifyModal = document.getElementById("number");
			const numberModal = new bootstrap.Modal(modifyModal, { keyboard: false });
			$(".number").click(function(e) {
				e.preventDefault();
				// console.log(e.target.parentElement.id);
				// 請求該訂單號碼牌
				$.ajax({
					type: "GET",
					url: "http://localhost:8080/whattodrink/C_myurrentOrderNumber",
					data: { newOrdId: e.target.parentElement.id },
					dataType: "json",
					success: function(response) {
						console.log(response);
						var strNewOrdNum = `
            <div
            style="
              border-radius: 15px;
              border: black 2px solid;
              padding: 20px;
              margin: 20px;
            "
            class="tr"
            id="textArea"
          >
            <div class="row mb-3">
              <div class="d-flex" style="justify-content: space-between">
                <a><img src= "../../images/logo.png" alt="logo" class="small-logo"></a>
                <a
                  role="button"
                  class="fs-5 text-dark"
                  ><i class="far fa-copy" onclick="copy()"></i
                ></a>
              </div>
            </div>
            <div class="row">
              <div>
                <p class="fw-bold">取餐時間</p>
                <hr style="background-color: black" />
                <p class="d-flex justify-content-center" id="t">${response.newOrdTime}</p>
              </div>
            </div>
            <div class="row">
              <div>
                <p class="fw-bold">取餐編號</p>
                <hr style="background-color: black" />
                <p
                  class="d-flex justify-content-center fs-1 fw-bold"
                  style="margin: 0px" 
                  id="n"
                >${response.newOrdId}</p>
              </div>
            </div>
          </div>
              `;
						showNum.insertAdjacentHTML("afterend", strNewOrdNum);
					},
				});
				numberModal.show();
				$("#numClose").click(function() {
					$(".tr").remove();
				});
			});

			//開啟訂單明細modal
			const receiptModal = new bootstrap.Modal($("#details"), {
				keyboard: false,
			});
			$(".pp").click(function(e) {
				console.log(e.target.parentElement.id);
				$.ajax({
					type: "GET",
					url: "http://localhost:8080/whattodrink/C_myOrderItem",
					data: { receipt: e.target.parentElement.id },
					dataType: "json",
					success: function(response) {
						console.log(response);
						var receiptStr = "";
						$("#ordTime").text(`${response[0].ordTime}`);
						$("#total").text(`${response[1].total}`);
						$("#payment").text(`${response[2].payment}`);
						for (let a = 3; a < response.length; a++) {
							receiptStr += `
                <tr class="tr">
                  <td>${response[a].quantity}</td>
                  <td class="h5">${response[a].itemName}</td>
                  <td>${response[a].capacity}</td>
                  <td>${response[a].sugar}</td>
                  <td>${response[a].temp}</td>
                  <td>${response[a].sticker}</td>
                  <td>${response[a].note}</td>
                </tr> 
            `;
						}
						details2.insertAdjacentHTML("afterend", receiptStr);
					},
				});
				receiptModal.show();
				$("#detailsClose").click(function() {
					$(".tr").remove();
				});
			});
		},
	});
});
