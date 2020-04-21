function connect() {
    const socket = new SockJS(`/ws/connect`);
    const stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/sub/group/1234', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.send("/pub/get/msg", {}, JSON.stringify({type:'ENTER', roomId:"1234", sender:"duck"}));
    });
}

function showGreeting(message) {
    console.log("DD"+ message);
}

$(function () {
    $("button").click(function () {
        connect();
    });
});