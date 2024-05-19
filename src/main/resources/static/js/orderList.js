// 날짜별 검색
document.querySelector("#searchOrders").addEventListener("click", function (event) {
    event.stopPropagation();
    event.preventDefault();

    let storeId = event.target.getAttribute('data-store-id');
    let startDate = document.getElementById('startDate').value;
    let endDate = document.getElementById('endDate').value;

    // 리스트 위에 날짜 바뀌게 하기
    let startArr = startDate.split("-");
    let endArr = endDate.split("-");
    document.getElementById("startYear").textContent = startArr[0];
    document.getElementById("startMonth").textContent = startArr[1];
    document.getElementById("startDay").textContent = startArr[2];
    document.getElementById("endYear").textContent = endArr[0];
    document.getElementById("endMonth").textContent = endArr[1];
    document.getElementById("endDay").textContent = endArr[2];

    fetch(`/stores/${storeId}/orders/history/date?startDate=${startDate}&endDate=${endDate}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('오류 발생: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            renderOrderHistories(data);
        })
        .catch(error => {
            console.error('오류 발생:', error);
        });
});

function renderOrderHistories(order) {
    let html = "";
    if (order.orderList.length > 0) {
        order.orderList.forEach(order => {
            html += `
                <tr class="order-row" data-order-id="${order.orderId}" data-store-id="${storeId}" style="cursor: pointer;">
                    <td>${order.orderId}</td>
                    <td>${order.status}</td>
                    <td>${order.createdAt}</td>
                    <td>${order.totalPrice}</td>
                    <td>${order.customerNickname}</td>
                    <td>${order.orderMenus}</td>
                </tr>
            `;
        });
    } else {
        html += `
            <tr>
                <td colspan="5">주문 내역이 없습니다.</td>
            </tr>
        `;
    }
    document.getElementById('orderHistories').innerHTML = html;

    // 버튼 누르면 모달 열리게
    document.querySelectorAll('.order-row').forEach(row => {
        row.addEventListener('click', function () {
            const orderId = this.dataset.orderId;
            const storeId = this.dataset.storeId;
            showOrderDetailModal(orderId, storeId);
        });
    });
}

// 주문 상세 모달
function showOrderDetailModal(orderId, storeId) {
    fetch(`/stores/${storeId}/orders/${orderId}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 응답이 실패했습니다.');
            }
            return response.json();
        })
        .then(data => {
            let htmlContent = `
<div class="container-fluid" style="padding: 0;">
    <div class="modal-header" style="background-color: navy; padding: 10px;">
        <h5 class="modal-title" style="color: white; font-size: 15px;">주문 상세내역</h5>
        <button type="button" style="border: 1px solid #65c6d3; border-radius:50%; background-color: #65c6d3; margin-right: 3px; width: 10px;height: 10px;" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
    </div>
    <div style="padding: 20px;">
        <div class="modal-top d-flex row-cols-2" style="height: 600px;">
            <div class="modal-top-left" style="margin-left: 10px; margin-right: 20px;">
                <div style="margin-bottom: 10px;">
                    <b>${data.body.orderId}번</b>
                </div>
                <div>
                    <div style="margin-bottom: 10px; font-weight: bold;">
                        <div class="d-flex">
                            <div class="title" style="margin-right: 5px; width: 78px;">주문일시:</div>
                            <div class="content">${data.body.createdAt}</div>
                        </div>
                        <div class="d-flex">
                            <div class="title" style="margin-right: 5px;width: 78px; ">주 문 자:</div>
                            <div class="content">${data.body.customerNickname}</div>
                        </div>
                        <div class="d-flex">
                            <div class="title" style="margin-right: 5px;width: 78px;">휴대전화:</div>
                            <div class="content">${data.body.customerTel}</div>
                        </div>
                        <div class="d-flex">
                            <div class="title" style="margin-right: 5px;width: 78px;">주문상태:</div>
                            <div class="content">${data.body.status}</div>
                        </div>
                    </div>
                    <div class="d-flex">
                        <div style="color: orangered; margin-right: 5px;">수령 예정 시각:</div>
                        <div>바로 받으러 갈게요.</div>
                    </div>
                </div>
            </div>
            <div class="row modal-top-right" style="position: relative;">
                <div style="height: 550px;">
                    <table class="table">
                        <thead>
                        <tr style="color: orangered; border-bottom: 1px solid black;">
                            <th scope="col">메뉴</th>
                            <th scope="col" style="text-align: center;">수량</th>
                            <th scope="col" style="text-align: end;">금액</th>
                        </tr>
                        </thead>
                        <tbody style="border-bottom: 1px solid #6b6868;">`;

            // 메뉴 반복문 시작
            data.body.orderMenuList.forEach(menu => {
                htmlContent += `
                        <tr class="menu-column" style="font-weight: bold; border-top: 1px solid #6b6868;">
                            <td>${menu.name}</td>
                            <td style="text-align: center;">${menu.qty}</td>
                            <td style="text-align: end;">${menu.totalPrice.toLocaleString()}원</td>
                        </tr>`;
                menu.orderMenuOptionList.forEach(option => {
                    htmlContent += `
                        <tr class="option-column" style="font-size: 12px; color: #6b6868;  border-top: 2px dotted #e6e6e6;">
                            <td>${option.name}</td>
                            <td style="text-align: center;"></td>
                            <td style="text-align: end;">${option.price.toLocaleString()}원</td>
                        </tr>`;
                });
            });
            // 메뉴 반복문 끝

            htmlContent += `          
                        </tbody>
                    </table>
                </div>
                <table class="table footer-table" style="border-bottom: 1px solid rgba(255, 255, 255, 0.0);">
                    <tr>
                        <td colspan="3" style="color: orangered; font-weight: bold;">결제금액</td>
                        <td style="text-align: end; font-weight: bold;">${data.body.totalPrice.toLocaleString()}원</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="modal-footer d-flex justify-content-center">
            <div style="margin-right: 20px;">
                <button type="button" class="btn btn-outline-secondary">🧾 주문인쇄</button>
                <button type="button" class="btn btn-outline-secondary">🖨️ 영수증인쇄</button>
            </div>
            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">✖️ 결제취소</button>
        </div>
    </div>
</div>`;
            document.querySelector('#orderDetailModal .modal-body').innerHTML = htmlContent;
            new bootstrap.Modal(document.getElementById('orderDetailModal')).show();
        })
        .catch(error => {
            console.error("Error fetching order details: ", error);
            alert('주문 상세 정보를 가져오는데 실패했습니다.');
        });
}


// // 모달 열기 함수
// function openModal(orderId) {
//     var modal = document.getElementById('orderDetailModal');
//     modal.style.display = 'block';
//
//     // 여기에 주문 상세 내용을 가져오는 로직을 추가하세요
//     var detailContent = document.getElementById('detailContent');
//     detailContent.innerHTML = "주문 상세 내용을 가져와서 여기에 표시하세요";
// }
//
// // 모달 닫기 함수
// function closeModal() {
//     var modal = document.getElementById('orderDetailModal');
//     modal.style.display = 'none';
// }

// 주문 기록 검색 날짜 지정
$("button[type='submit']").click(function () {
    searchOrders();
});

// 오늘, 어제, 그제
$(".twoDaysAgo, .yesterday, .today").click(function (event) {
    event.preventDefault();

    let dateOffset = $(this).hasClass("twoDaysAgo") ? 2 : ($(this).hasClass("yesterday") ? 1 : 0);
    let today = new Date();
    let targetDate = new Date(today);
    targetDate.setDate(today.getDate() - dateOffset);

    let year = targetDate.getFullYear();
    let month = ('0' + (targetDate.getMonth() + 1)).slice(-2);
    let day = ('0' + targetDate.getDate()).slice(-2);

    // 시작 날짜와 종료 날짜 설정
    $("#startDate").val(year + '-' + month + '-' + day);
    $("#endDate").val(year + '-' + month + '-' + day);

    // 페이지의 다른 부분에 날짜 표시
    $("#startYear, #endYear").text(year);
    $("#startMonth, #endMonth").text(month);
    $("#startDay, #endDay").text(day);
});
