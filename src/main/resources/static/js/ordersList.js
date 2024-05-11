// 조회 버튼 클릭 시 호출되는 함수
function searchOrders() {
    // 조회 로직을 여기에 추가
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