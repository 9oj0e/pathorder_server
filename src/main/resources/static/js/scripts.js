/*!
    * Start Bootstrap - SB Admin v7.0.7 (https://startbootstrap.com/template/sb-admin)
    * Copyright 2013-2023 Start Bootstrap
    * Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-sb-admin/blob/master/LICENSE)
    */

// top-nav 시계
function setClock() {
    let dateInfo = new Date();
    let hour = modifyNumber(dateInfo.getHours());
    let min = modifyNumber(dateInfo.getMinutes());
    let sec = modifyNumber(dateInfo.getSeconds());
    let year = dateInfo.getFullYear();
    let month = dateInfo.getMonth() + 1;
    let date = dateInfo.getDate();
    $("#time").html(hour + ":" + min + ":" + sec);
    $("#date").html(year + "년 " + month + "월 " + date + "일");
}

function modifyNumber(time) {
    if (parseInt(time) < 10) {
        return "0" + time;
    } else
        return time;
}

$(function () {
    setClock();
    setInterval(setClock, 1000);
});

// 매장 운영 상태 변경 버튼
function storeStatus() {
    let isOpen = true;
    $("#changeStoreStatusBtn").click(function () {
        isOpen = !isOpen;
        if (isOpen) {
            $("#storeStateColor").css("backgroundColor", "rgb(93, 232, 50)");
            $("#storeStateText").text("영업중");
            $(".icon").html('<i class="fa-solid fa-door-open"></i>');
            $("#storeStatusBtnText").text("매장 닫기");
        } else {
            $("#storeStateColor").css("backgroundColor", "grey");
            $("#storeStateText").text("영업종료");
            $(".icon").html('<i class="fa-solid fa-door-closed"></i>');
            $("#storeStatusBtnText").text("매장 열기");
        }

    });
}

$(document).ready(function () {
    storeStatus();
});

document.addEventListener("DOMContentLoaded", function (event) {
    const sessionStoreId = document.getElementById('sessionStoreId').dataset.id;

    function updatePendingOrderCount() {
        fetch(`/stores/${sessionStoreId}/pending-order-count`)
            .then(response => response.json())
            .then(data => {
                console.log("댄이야");
                console.log(data);
                const pendingOrderCountElement = document.getElementById('pendingOrderCount');

                if (data.body === 0) {
                    pendingOrderCountElement.classList.add('hidden-count');
                } else {
                    pendingOrderCountElement.classList.remove('hidden-count');
                }

                pendingOrderCountElement.innerHTML = data.body;
            })
            .catch(error => {
                console.error('데이터 가져오기 오류:', error);
            });
    }

    updatePendingOrderCount();

    setInterval(updatePendingOrderCount, 50000);
});

// 주문 상세 모달
// $(document).ready(function () {
//     $('#orderDetailModal').on('shown.bs.modal', function () {
//         $('#orderDetaiInput').focus();
//     })
// });
// $(document).ready(function () {
//     // 메뉴 추가 모달의 옵션 추가 기능
//     $('#addOptionInAdd').on('click', function () {
//         let newOptionDiv = $('<div class="mb-3 d-flex align-items-center"></div>');
//         let newOptionInput = $('<input type="text" class="form-control" name="option[]">');
//         let deleteButton = $('<button type="button" class="btn btn-danger ms-2">-</button>');
//
//         deleteButton.on('click', function () {
//             $(this).parent().remove();
//         });
//
//         newOptionDiv.append(newOptionInput).append(deleteButton);
//         $('#optionFieldsInAdd').append(newOptionDiv);
//     });
//
//     // 메뉴 수정 모달의 옵션 추가 기능
//     $('#addOptionInEdit').on('click', function () {
//         let newOptionDiv = $('<div class="mb-3 d-flex align-items-center"></div>');
//         let newOptionInput = $('<input type="text" class="form-control" name="option[]">');
//         let deleteButton = $('<button type="button" class="btn btn-danger ms-2">-</button>');
//
//         deleteButton.on('click', function () {
//             $(this).parent().remove();
//         });
//
//         newOptionDiv.append(newOptionInput).append(deleteButton);
//         $('#optionFieldsInEdit').append(newOptionDiv);
//     });
// });