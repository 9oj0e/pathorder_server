// sse
const evtSource = new EventSource("/connect");
evtSource.onopen = function(event) {
    console.log("connected")
}
evtSource.addEventListener("sse", function(event){
    console.log(event.data);
    alert(event.data);
})
