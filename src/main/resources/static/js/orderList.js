// 날짜별 검색
document.querySelector("#searchOrders").addEventListener("click", function (event) {
    event.stopPropagation();
    event.preventDefault();

    let storeId = event.target.getAttribute('data-store-id');
    let startDate = document.getElementById('startDate').value;
    let endDate = document.getElementById('endDate').value;

    // 리스트 위에 날짜 바뀌게 하게
    let startArr = startDate.split("-");
    let endArr = endDate.split("-");

    document.getElementById("startYear").textContent = startArr[0];
    document.getElementById("startMonth").textContent = startArr[1];
    document.getElementById("startDay").textContent = startArr[2];

    document.getElementById("endYear").textContent = endArr[0];
    document.getElementById("endMonth").textContent = endArr[1];
    document.getElementById("endDay").textContent = endArr[2];

    let xhr = new XMLHttpRequest();
    xhr.open('GET', `/stores/${storeId}/orders/history/date?startDate=` + startDate + '&endDate=' + endDate, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.onreadystatechange = function () {
        if (xhr.readyState === XMLHttpRequest.DONE) {
            if (xhr.status === 200) {
                let response = JSON.parse(xhr.responseText);

                renderOrderHistories(response);
                console.log(response);
            } else {
                console.error('오류 발생: ' + xhr.status);
            }
        }
    };
    xhr.send();
});

function renderOrderHistories(orders) {
    let html = "";
    if (orders.length > 0) {
        orders.forEach(order => {
            html += `
                <tr>
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
}

// 모달 열기 함수
function openModal(orderId) {
    var modal = document.getElementById('orderDetailModal');
    modal.style.display = 'block';

    // 여기에 주문 상세 내용을 가져오는 로직을 추가하세요
    var detailContent = document.getElementById('detailContent');
    detailContent.innerHTML = "주문 상세 내용을 가져와서 여기에 표시하세요";
}

// 모달 닫기 함수
function closeModal() {
    var modal = document.getElementById('orderDetailModal');
    modal.style.display = 'none';
}

// 주문 기록 검색 날짜 지정
$("button[type='submit']").click(function () {
    searchOrders();
});

function searchOrders() {

    // let startDate = $("#startDate").val();
    // let endDate = $("#endDate").val();
    //
    // let startArr = startDate.split("-");
    // let endArr = endDate.split("-");
    //
    // $("#startYear").text(startArr[0]);
    // $("#startMonth").text(startArr[1]);
    // $("#startDay").text(startArr[2]);
    //
    // $("#endYear").text(endArr[0]);
    // $("#endMonth").text(endArr[1]);
    // $("#endDay").text(endArr[2]);
}

// 오늘, 어제, 그제
$(document).ready(function () {
    $(".2daysAgo, .1daysAgo, .today").click(function () {
        let dateOffset = $(this).hasClass("2daysAgo") ? 2 : ($(this).hasClass("1daysAgo") ? 1 : 0);
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
});