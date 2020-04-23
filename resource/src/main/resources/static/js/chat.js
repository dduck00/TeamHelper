let stompClient;

function connect() {
    const socket = new SockJS('http://localhost:9000/ws/connect');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe(`/sub/group/${$('#groupId').val()}`, function (greeting) {
            JSON.parse(greeting.body);
        });
        stompClient.send("/pub/get/msg", {}, JSON.stringify({type: 'ENTER', roomId: $('#groupId').val(), sender: "duck"}));
    });7
}

function send(){
    stompClient.send("/pub/get/msg", {}, JSON.stringify({type:'TALK', roomId:$('#groupId').val(), sender:"duck", message:$("#message").val()}));
}

$(function () {
    $("#connectButton").click(() => {
        connect();
    });

    $("#sendButton").click(() => {
        send();
    })
});