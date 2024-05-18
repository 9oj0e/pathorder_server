// 주문 알림
const evtSource = new EventSource("/connect");
evtSource.onopen = function(event) {
    console.log("connected")
}
evtSource.onmessage = function(event) {
    // let data = JSON.parse(event.data);
    alert("새로운 주문이 도착했습니다");
};