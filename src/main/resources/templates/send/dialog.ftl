<html>
    <head>
        <meta charset="utf-8">
        <title>服务端</title>
        <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <script>
        var websocket = null;
        if('WebSocket' in window) {
            websocket = new WebSocket('ws://localhost:8888/webSocket/${userName}');
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
    <body>
        <div class="container">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <h1 class="text-center text-success">
                        简    易    对    话    系    统
                    </h1>
                    <br>
                    <div class="row clearfix">
                        <div class="col-md-4 column">
                            <form role="form">
                                <div class="form-group">
                                    <label for="exampleInputPassword1">${userName}：</label><input type="text" class="form-control" id="exampleInputPassword1" />
                                </div>
                                <button type="submit" class="btn btn-default btn-success">发送</button>
                        </div>
                        <div class="col-md-8 column">
                            <div style="color: red;">唐唐：徒儿们，救我！</div>
                            <div style="color: red;">沙僧：二师兄，师父被妖怪抓走了！</div>
                            <div style="color: red;">八戒：猴哥，师父被妖怪抓走了！</div>
                            <div style="color: red;">悟空：呔，妖怪休走！！！</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>