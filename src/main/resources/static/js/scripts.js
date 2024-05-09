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

// 주문 기록 검색 날짜 지정

$("button[type='submit']").click(function () {
    searchOrders();
});

function searchOrders() {

    let startDate = $("#startDate").val();
    let endDate = $("#endDate").val();

    let startArr = startDate.split("-");
    let endArr = endDate.split("-");

    $("#startYear").text(startArr[0]);
    $("#startMonth").text(startArr[1]);
    $("#startDay").text(startArr[2]);

    $("#endYear").text(endArr[0]);
    $("#endMonth").text(endArr[1]);
    $("#endDay").text(endArr[2]);
};


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

// 주문 상세 모달
$('button[data-order-id]').click(function () {
    let orderId = $(this).data('order-id');
    let storeId = $(this).data('store-id');

    $.ajax({
        url: `/stores/${storeId}/orders/${orderId}`,
        type: 'GET',
        success: function (data) {

            let htmlContent = `
                   <div class="container-fluid" style="padding: 0;">
                    <div class="modal-header" style="background-color: navy; padding: 10px;">
                        <h5 class="modal-title" style="color: white; font-size: 15px;">주문 상세내역</h5>
                        <button type="button"
                                style="border: 1px solid #65c6d3; border-radius:50%; background-color: #65c6d3; margin-right: 3px; width: 10px;height: 10px;"
                                class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div style="padding: 20px;">
                        <div class="modal-top d-flex row-cols-2" style="height: 600px;">
                            <!-- 주문 정보 -->
                            <div class="modal-top-left" style="margin-left: 10px; margin-right: 20px;">
                                <div style="margin-bottom: 10px;">
                                    <!-- TODO: 하드코딩된 부분! -->
                                    <b>1번</b>
                                </div>
                                <div>
                                    <div style="margin-bottom: 10px; font-weight: bold;">
                                        <div class="d-flex">
                                            <div class="title" style="margin-right: 5px; width: 78px;">주문일시:
                                            </div>
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
                                        <!-- TODO: 하드코딩된 부분! -->
                                        <div>바로 받으러 갈게요.</div>
                                    </div>
                                </div>
                            </div>
                            <!-- 주문 메뉴 -->
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
            for (let i = 0; i < data.body.orderMenuList.length; i++) {
                let menu = data.body.orderMenuList[i];
                htmlContent += `
                                                <tr class="menu-column" style="font-weight: bold; border-top: 1px solid #6b6868;">
                                                    <td>${menu.name}</td>
                                                    <td style="text-align: center;">${menu.qty}</td>
                                                    <td style="text-align: end;">${menu.totalPrice.toLocaleString()}원</td>
                                                </tr>`;

                for (let j = 0; j < menu.orderMenuOptionList.length; j++) {
                    let option = menu.orderMenuOptionList[j];
                    htmlContent += `
                                            <tr class="option-column" style="font-size: 12px; color: #6b6868;  border-top: 2px dotted #e6e6e6;">
                                                <td>${option.name}</td>
                                                <td style="text-align: center;"></td>
                                                <td style="text-align: end;">${option.price.toLocaleString()}원</td>
                                            </tr>`;
                }
            }
            // 메뉴 반복문 끝

            htmlContent += `           </tbody>
                                    </table>
                                </div>
                                <table class="table footer-table"
                                       style="border-bottom: 1px solid rgba(255, 255, 255, 0.0);">
                                    <tr>
                                        <td colspan="3" style="color: orangered; font-weight: bold;">결제금액</td>
                                        <td style="text-align: end; font-weight: bold;">${data.body.totalPrice}원</td>
                                    </tr>
                                </table>
                            </div>
                        </div>
                        <div class="modal-footer d-flex justify-content-center">
                            <div style="margin-right: 20px;">
                                <button type="button" class="btn btn-outline-secondary">🧾 주문인쇄</button>
                                <button type="button" class="btn btn-outline-secondary">🖨️ 영수증인쇄</button>
                            </div>
                            <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">✖️
                                결제취소
                            </button>
                        </div>
                    </div>
                </div>`;
            console.log(data);
            $('#orderDetailModal .modal-body').html(htmlContent);
            $('#orderDetailModal').modal('show');
        },
        error: function (error) {
            console.error("Error fetching order details: ", error);
            alert('주문 상세 정보를 가져오는데 실패했습니다.');
        }
    });
});

// 메뉴 상세보기 모달
$('button[data-menu-id]').click(function (event) {

    let menuId = event.currentTarget.dataset.menuId;
    let storeId = event.currentTarget.dataset.storeId;
    let sessionStoreId = event.currentTarget.dataset.sessionStoreId;

    $.ajax({
        url: `/stores/${storeId}/menus/${menuId}`,
        type: 'GET',
        success: function (data) {

            // 메뉴 정보 추가
            let menuContent =

                $('#menuForm').html(render(data, sessionStoreId, menuId));
            $('#menuModal').modal('show');
        }
        ,
        error: function (error) {
            console.error("Error fetching order details: ", error);
            alert('주문 상세 정보를 가져오는데 실패했습니다.');
        }
    });
});


function render(data, sessionStoreId, menuId) {

    let html = ``;

    html += `
              <form action="/stores/${sessionStoreId}/menus/${menuId}" method="post" id="menuEditForm" data-menu-id="${menuId}"
                  data-store-id="${sessionStoreId}">
                <div style="display: grid; grid-template-columns: 1fr 2fr; grid-column-gap: 5%; padding: 30px">
                        <div class="card mt-5" style="width:300px">
                            <div class="card-header" id="inputStatusChangeBtns">
                                <div class="edit-btn" id="editBtn">
                                    <button type="button" class="btn btn-outline-dark">수정</button>
                                </div>
                                <div class="complete-btns hidden" id="completeBtns">
                                    <!-- 버튼에 이거 넣어야 함. data-menu-id="menuId" -->
                                    <button type="submit" class="btn btn-outline-dark" data-menu-id="${menuId}" data-edit-target="${menuId}"  data-store-id="${sessionStoreId}">
                                        저장
                                    </button>
                                    <button type="button" class="btn btn-outline-danger">
                                        취소
                                    </button>
                                </div>
                            </div>
                            <div class="card-body" style="height: 200px;">
                                <img class="card-img" src="https://www.mysavings.com/img/link/large/113816.jpg"
                                     style="height: 100%; width: 100%;">
                            </div>
                            <div id="menu" class="card-footer">
                                <table class="table">
                                    <tbody>
                                        <tr>
                                            <th>분류</th>
                                            <td><input type="text" value="${data.body.category}" id="category" name="category" readonly/></td>
                                        </tr>
                                        <tr>
                                            <th>이름</th>
                                            <td><input type="text" value="${data.body.name}" id="name" name="name"  readonly/></td>
                                        </tr>
                                        <tr>
                                            <th>가격</th>
                                             <td><input type="text" value="${data.body.price}" id="price" name="price" readonly/></td>
                                        </tr>
                                        <tr>
                                        <th>설명</th>
                                        <td><input type="text" value="${data.body.description}" id="description" name="description" style="width: 100%" readonly/>
                                        </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                    <div>
                        <table class="table option">
                            <thead>
                            <tr>
                                <th>
                                    <h4>필수 옵션</h4>
                                </th>
                                
                            </tr>
                            </thead>
                            <tbody id="required-menu-option">`;

                    for (let i = 0; i < data.body.menuOptionList.length; i++) {
                        let option = data.body.menuOptionList[i];
                        if (option.required === true) {
                            html += `<tr>
                                        <input type="hidden" value="true" id="optionRequired" name="optionRequired"/>
                                        <th><input type="text" value="${option.name}" id="optionName" name="optionName" readonly/></th>
                                        <td><td><input type="text" value="${option.price}" id="optionPrice" name="optionPrice" readonly/></td></td>
                                    </tr>`;
                        }
                    }

                    html += `       
                                    </tbody>
                                </table>
                                <table class="table option mb-5">
                                    <thead>
                                    <tr>
                                        <th>
                                            <h4>선택 옵션</h4>
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody id="optional-menu-option">
                                         </tr>`;

                    for (let i = 0; i < data.body.menuOptionList.length; i++) {
                        let option = data.body.menuOptionList[i];
                        if (option.required === false) {
                            html += `<tr>
                                        <input type="hidden" value="false" id="optionRequired" name="optionRequired"/>
                                        <th><input type="text" value="${option.name}" id="optionName" name="optionName" readonly/></th>
                                        <td><input type="text" value="${option.price}" id="optionPrice" name="optionPrice" readonly/></td>
                                    </tr>`;
                        }
                    }

                    html += `             </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </form>`;

                    return html;
}

// 메뉴 수정 토글 버튼
$(document).on("click", "#inputStatusChangeBtns", function () {
    $(this).find("#editBtn").toggleClass("hidden");
    $(this).find("#completeBtns").toggleClass("hidden");
    $("input").each(function () {
        let isReadOnly = $(this).prop('readOnly');
        $(this).prop('readOnly', !isReadOnly);
        $(this).css("background-color", "#ffffff");
    });
});


// 메뉴 수정
$(document).on("submit", "#menuEditForm", function (e) {
    e.preventDefault();
    let form = this;
    let formData = new FormData(this);
    let menuOptions = [];
    for (let i = 0; i < formData.getAll("optionName").length; i++) {
        let option = {
            price: formData.getAll("optionPrice").at(i),
            name: formData.getAll("optionName").at(i),
            required: formData.getAll("optionRequired").at(i),
        }
        menuOptions.push(option);
    }
    let data = {
        price: formData.get("price"),
        name: formData.get("name"),
        category: formData.get("category"),
        description: formData.get("description"),
        menuOptions: menuOptions
    }
    let menuId = $(this).data('menu-id');
    let storeId = $(this).data('store-id');

    $.ajax({
        type: "PUT",
        url: `/stores/${storeId}/menus/${menuId}`,
        data: JSON.stringify(data),
        contentType: 'application/json',
        success: function (response) {
            // 데이터 전송 성공 시 실행될 코드
            console.log("성공: ", response);
            $(form).prop('readonly', !isReadOnly);
            $(form).find("#editBtn").toggleClass("hidden");
            $(form).find("#completeBtns").toggleClass("hidden");
        },
        error: function (error) {
            console.error("Error fetching menu details: ", error);
            alert('메뉴 상세 정보를 가져오는데 실패했습니다.');
        }
    });
});

$(document).ready(function () {
    $("#btnClose").on("click", function () {
        $("#editBtn").removeClass("hidden");
        $("#completeBtns").addClass("hidden");
    });
});