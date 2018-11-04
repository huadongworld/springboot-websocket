# springboot-websocket

极简易课堂对话系统

直接启动`WebsocketApplication`类，就可以按以下步骤访问了

先进入系统，用网页打开如下网址：

`http://localhost:8080/dialog/enter/老师-赵老六`

`http://localhost:8080/dialog/enter/学生-张三`

...

修改地址最后字段，老师以`老师-`开头，学生以`学生-`开头，后台会以`前缀`识别身份

打开后的效果如下：

 ![index](https://github.com/huadongworld/springboot-websocket/raw/master/src/pic/总界面.png)
 
 多播，在老师界面框输入：`同学们，能听见吗？`发送之后，所有同学都会收到此信息，效果如下：
 
 ![multicast](https://github.com/huadongworld/springboot-websocket/raw/master/src/pic/多播.png)
 
 单播，在任意一个同学界面框输入：`老师，我有问题！`发送之后，只有老师会收到此信息，效果如下：
 
 ![unicast](https://github.com/huadongworld/springboot-websocket/raw/master/src/pic/单播.png)
 
 其实springboot集成的websocket实现起来非常的简单，这个demo也没有太多余的代码，前台只有一个简单的页面，
 
 后台主要就是需要`配置WebSocketConfig`，还有`WebSocketServer`这个类，这里是websocket的核心，
 
 包括`建立连接、断开连接、发送消息、连接异常等`的处理，主要任务就是`如何保存和处理所有连接用户的信息`，
 
 具体的单播、多播其实就是对单一用户、多个用户、所有用户推送信息而已。