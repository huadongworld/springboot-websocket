<script>
    var websocket = null;
    if('WebSocket' in window) {
        websocket = new WebSocket('ws://localhost:8080/webSocket/huadongworld');
    }else {
        alert('该浏览器不支持websocket!');
    }

    websocket.onopen = function (event) {
        console.log('建立连接');
    };

    websocket.onclose = function (event) {
        console.log('连接关闭');
    };

    websocket.onmessage = function (event) {
        console.log('收到消息:' + event.data)
    };

    websocket.onerror = function () {
        alert('websocket通信发生错误！');
    };

    window.onbeforeunload = function () {
        websocket.close();
    }
</script>