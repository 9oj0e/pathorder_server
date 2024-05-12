// 메뉴 상세보기 modal 열기
$('.load-menu-detail').click(function (event) {
    let menuId = event.currentTarget.dataset.menuId;
    let storeId = event.currentTarget.dataset.sessionStoreId;

    $.ajax({
        url: `/stores/${storeId}/menus/${menuId}`,
        type: 'GET',
        success: function (data) {
            let menuDetail = render(data, storeId, menuId);
            sessionStorage.setItem("menuDetail", menuDetail); // to reload
            $('#menuForm').html(menuDetail);
            $('#menuModal').modal('show');
        }
        ,
        error: function (error) {
            console.error("Error fetching order details: ", error);
            alert('주문 상세 정보를 가져오는데 실패했습니다.');
        }
    });
});

// 메뉴 상세보기 modal 닫기
$("#btnClose").on("click", function () {
    $("#editBtn").removeClass("hidden");
    $("#submitBtn").addClass("hidden");
    $("#cancelBtn").addClass("hidden");
    $("#editImg").addClass("hidden-edt");
    $("input").each(function () {
        // let isReadOnly = $(this).prop('readOnly');
        $(this).prop('readonly', true);
        $(this).removeClass("input-mode")
    });
});