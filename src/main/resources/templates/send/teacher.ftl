<html>
    <head>
        <meta charset="utf-8">
        <title>老师-${userName}</title>
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
                        极&nbsp;&nbsp;&nbsp;&nbsp;简&nbsp;&nbsp;&nbsp;&nbsp;易&nbsp;&nbsp;&nbsp;&nbsp;语&nbsp;&nbsp;&nbsp;&nbsp;音&nbsp;&nbsp;&nbsp;&nbsp;系&nbsp;&nbsp;&nbsp;&nbsp;统
                    </h1>
                    <br>
                    <div class="row clearfix">
                        <div class="col-md-12 column">
                            <form role="form" method="post" action="/dialog/sendMsg">
                                <div class="form-group">
                                    <label for="exampleInputPassword1">${userName}：</label>
                                    <input type="text" class="form-control" name="msg" id="exampleInputPassword1" />
                                </div>
                                <button type="submit" class="btn btn-default btn-success">发送</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>