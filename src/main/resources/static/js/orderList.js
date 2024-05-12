// 날짜별 검색
// document.querySelector("#searchOrders").addEventListener("click", function (event) {
//     event.stopPropagation();
//     event.preventDefault();
//
//     let storeId = event.target.getAttribute('data-store-id');
//     let startDate = document.getElementById('startDate').value;
//     let endDate = document.getElementById('endDate').value;
//
//     // 리스트 위에 날짜 바뀌게 하게
//     let startArr = startDate.split("-");
//     let endArr = endDate.split("-");
//
//     document.getElementById("startYear").textContent = startArr[0];
//     document.getElementById("startMonth").textContent = startArr[1];
//     document.getElementById("startDay").textContent = startArr[2];
//
//     document.getElementById("endYear").textContent = endArr[0];
//     document.getElementById("endMonth").textContent = endArr[1];
//     document.getElementById("endDay").textContent = endArr[2];
//
//     let xhr = new XMLHttpRequest();
//     xhr.open('GET', `/stores/${storeId}/orders/history/date?startDate=` + startDate + '&endDate=' + endDate, true);
//     xhr.setRequestHeader('Content-Type', 'application/json');
//     xhr.onreadystatechange = function () {
//         if (xhr.readyState === XMLHttpRequest.DONE) {
//             if (xhr.status === 200) {
//                 let response = JSON.parse(xhr.responseText);
//
//                 renderOrderHistories(response);
//                 console.log(response);
//             } else {
//                 console.error('오류 발생: ' + xhr.status);
//             }
//         }
//     };
//     xhr.send();
// });

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
            console.log(data);
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
                <tr>
                    <!-- TODO: 주문번호 로직 짜서 그거 적용시켜야 함. -->
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

// 오늘, 어제, 그제
$(".towDaysAgo, .yesterday, .today").click(function (event) {
    event.preventDefault();

    let dateOffset = $(this).hasClass("towDaysAgo") ? 2 : ($(this).hasClass("yesterday") ? 1 : 0);
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
