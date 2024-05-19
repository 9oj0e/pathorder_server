// sse
const evtSource = new EventSource("/connect");
evtSource.onopen = function(event) {
    console.log("서버 연결 완료, EventStream 생성")
}
evtSource.addEventListener("sse", function(event){
    console.log(event.data);
    alert(event.data);
})
evtSource.onerror = function() {
    console.log("EventStream 연결 애러")
    evtSource.removeEventListener("sse", function(event){
        console.log("EventStream 연결 해제");
    });
    evtSource.close();
}
