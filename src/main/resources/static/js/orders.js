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
            <button type="button" style="border: 1px solid #65c6d3; border-radius:50%; background-color: #65c6d3; margin-right: 3px; width: 10px;height: 10px;" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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

            htmlContent += `          
                        </tbody>
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
            $('#orderDetailModal .modal-body').html(htmlContent);
            $('#orderDetailModal').modal('show');
        },
        error: function (error) {
            console.error("Error fetching order details: ", error);
            alert('주문 상세 정보를 가져오는데 실패했습니다.');
        }
    });
});