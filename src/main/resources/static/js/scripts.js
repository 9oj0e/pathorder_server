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
    }
    else
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

// 주문 상세 모달
$('#orderDetailModal').on('shown.bs.modal', function () {
    $('#orderDetaiInput').focus();
})

$(document).ready(function () {
    // 메뉴 추가 모달의 옵션 추가 기능
    $('#addOptionInAdd').on('click', function () {
        let newOptionDiv = $('<div class="mb-3 d-flex align-items-center"></div>');
        let newOptionInput = $('<input type="text" class="form-control" name="option[]">');
        let deleteButton = $('<button type="button" class="btn btn-danger ms-2">-</button>');

        deleteButton.on('click', function () {
            $(this).parent().remove();
        });

        newOptionDiv.append(newOptionInput).append(deleteButton);
        $('#optionFieldsInAdd').append(newOptionDiv);
    });

    // 메뉴 수정 모달의 옵션 추가 기능
    $('#addOptionInEdit').on('click', function () {
        let newOptionDiv = $('<div class="mb-3 d-flex align-items-center"></div>');
        let newOptionInput = $('<input type="text" class="form-control" name="option[]">');
        let deleteButton = $('<button type="button" class="btn btn-danger ms-2">-</button>');

        deleteButton.on('click', function () {
            $(this).parent().remove();
        });

        newOptionDiv.append(newOptionInput).append(deleteButton);
        $('#optionFieldsInEdit').append(newOptionDiv);
    });
});

// 주문 기록 검색 날짜 지정
$(document).ready(function () {
    $("button[type='submit']").click(function () {
        searchOrders();
    });

    function searchOrders() {
        // 시작 날짜와 종료 날짜 값을 가져옵니다.
        var startDate = $("#startDate").val();
        var endDate = $("#endDate").val();

        // 날짜 값을 '-'를 기준으로 나누어 배열로 만듭니다.
        var startArr = startDate.split("-");
        var endArr = endDate.split("-");

        // 시작 날짜와 종료 날짜를 페이지에 표시합니다.
        $("#startYear").text(startArr[0]);
        $("#startMonth").text(startArr[1]);
        $("#startDay").text(startArr[2]);

        // endYear 의 id를 수정해야 함을 주의하세요.
        $("#endYear").text(endArr[0]);
        $("#endMonth").text(endArr[1]);
        $("#endDay").text(endArr[2]);
    }
});

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
