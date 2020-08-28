# SmartHome
物联网智能家居系统的网站和服务器
网站和服务器写在一起，先运行服务器再运行网站即可。

**博客地址：https://blog.csdn.net/qq_29668759/article/details/99687387**

# 物联网应用开发-智能家居项目
记大三上学期结束，以智能家居项目结束了大学前2年半愉快的生活。

---
## 1.项目成员
- 赵旭东
- 郑普元
- 胡旸
- 甄洪磊
---
## 2.成果
（1）全部成果
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190816232737278.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5NjY4NzU5,size_16,color_FFFFFF,t_70)
（2）数据实时展示、控制命令发送网站
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190816232850748.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5NjY4NzU5,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190816232911920.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5NjY4NzU5,size_16,color_FFFFFF,t_70)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190816233002138.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5NjY4NzU5,size_16,color_FFFFFF,t_70)
（3）网关-开发板
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190816233400368.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5NjY4NzU5,size_16,color_FFFFFF,t_70)
（4）传感器节点
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190816233504422.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5NjY4NzU5,size_16,color_FFFFFF,t_70)

---
## 3.项目背景
  目前通常把智能家居被定义为利用电脑、网络和综合布线技术，通过家庭信息管理平台将与家居生活有关的各种子系统有机地结合的一个系统。也就是说，首先，它们都要在一个家居中建立一个通讯网络，为家庭信息提供必要的通路，在家庭网络的操作系统的控制下，通过相应的硬件和执行机构，实现对所有家庭网络上的家电和设备的控制和监测。其次，它们都要通过一定的媒介平台，构成与外界的通讯通道，以实现与家庭以外的世界沟信息，满足远程控制/监测和交换信息的需求。最后，它们的最终目的都是为满足人们对安全、舒适、方便和符合绿色环境保护的需求。
  
---
## 4.总体设计思路
写一个控制各种传感器的网站，前端能适配到PC端和移动端。网站后台把用户命令发送到服务器，服务器进行判断发送到网关，网关进行判断进行相应的操作。网关接收协调器的各种信息上传到服务器，服务器把数据存入数据库。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190816233817966.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5NjY4NzU5,size_16,color_FFFFFF,t_70)

---
## 5.模块设计思路
该系统在开发时分为PC、移动端前端模块，服务器模块，网站后台模块，数据处理模块，数据存储模块，网关模块，ZigBee网络模块。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190816233903336.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3FxXzI5NjY4NzU5,size_16,color_FFFFFF,t_70)

--- 
## 6.关键技术
Bootstrap框架、ECharts框架、Ajax、Socket、QT

--- 
## 7.网站关键代码
### (1) 前端与后台交互：Ajax
```
var xmlHttp;
  function lockdoor(){
//    向后台发送标识
    //使用方法创建一个对象XmlHttp
    xmlHttp=createXMLHttp();
    //要给服务器发送数据
    var url="order?sign=1";
    xmlHttp.open("GET",url,true);
    //4.绑定回调方法
    xmlHttp.onreadystatechange=lockdoorcallback;
    xmlHttp.send(null);
  }
  function lockdoorcallback(){
  }
```
### (2) 网站后台与前端数据传输：Selvet+Ajax
用户在前端点击按钮，发送一个标识Sign到网站后台，后台对这个Sign进行判断，判断如果是一个命令，那么直接调用AppConnectServer类的connect(）发送至服务器。
前端每5s向后台发送“10110”请求各个传感器节点的信息，后台接收到后立刻返回一个封装了各个传感器信息的字符串给前端。
```
public class Background extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String sign=request.getParameter("sign");//标识这是用户的那种操作
        Integer Sign=Integer.valueOf(sign);//转换后的标识
        if(Sign==110){                          //继电器模块
            System.out.println("用户想开空调");
            response.getWriter().write(1);
            AppConnectServer appConnectServer=new AppConnectServer(Sign);
            appConnectServer.connect();
        }else if(Sign==111){
```
### (3) 服务器和网关交互：Socket(java)<-->Socket(QT)
服务器一直监听8899端口，网关开机后根据用户输入的服务器的IP和Port创建一个新线程SocketTh向服务器发起Socket请求，请求成功后建立Socket长连接，网关收集到的各个传感器的数据通过这个Socket长连接发送至服务器。
```
public void connect(){
        try {
            Socket socket=new Socket("localhost",8889);
            System.out.println("网站已连接服务器");
            OutputStream os=socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
            pw.write(String.valueOf(sign));
            pw.flush();
            socket.shutdownOutput();//关闭输出流
            pw.close();
            os.close();
            socket.close();
        }
```

--- 
## 8.服务器关键代码
服务器初始化时会开一个线程ServertoGateway，这个线程负责连接数据库和监听8899端口等待网关请求建立Socket长连接，收到网关的数据后存入数据库。主线程监听8889端口等待网站后台Socket请求，一旦有请求会开一个线程ServerSocketCa进行和网站后台的数据交换并把数据返回。主线程拿到返回的数据发送至网关。
### (1)等待socket请求：主线程
主线程在8889端口等待请求，一旦有socket请求，开一个线程去处理这个请求。
```
public static void main(String[] args){
        try {
            ServertoGateway toGatewayTh=new ServertoGateway();。
            toGatewayTh.start();
            ServerSocket serverSocket=new ServerSocket(8889);
            Socket server_socket_th=null;
            while (true){
                server_socket_th=serverSocket.accept();
                ExecutorService exec = Executors.newCachedThreadPool();
                ServerSocketCa serverSocketCa=new ServerSocketCa(server_socket_th);
                Future f=exec.submit(serverSocketCa);
                int ordertogateway=Integer.valueOf(f.get().toString());
                System.out.println("线程返回了用户传递来的命令："+ordertogateway);
                SendOrdertoGate sendnow=new SendOrdertoGate();
                sendnow.sendtogateway(ordertogateway);
            }
        } 
```
### (2) 和网关建立Socket长连接：线程ServertoGateway
主线程初始化时创建这个线程，该线程在8899端口等待网关的Socket请求，一旦有请求则建立并保持Socket长连接。
```
@Override
    public void run() {
        try {
            serverSocket_toGateway = new ServerSocket(8899);
            socket_gateway=serverSocket_toGateway.accept();
            System.out.println("网关已连接");
            while(true){
                if(socket_gateway!=null){
                    InputStream is=null;//输入流
                    try {
                        socket_gateway.setKeepAlive(true);//检查两边是否连接
                        is=socket_gateway.getInputStream();//获取输入流
                        byte[] buff=new byte[20];
                        is.read(buff);
                        String tempdata =new String(buff);
                        System.out.println("正在接收数据:"+tempdata);
```
### (3) 接收网站后台数据：线程ServerSocketCa
服务器一直监听8889端口，一旦后台发来了新的连接请求则开一个新的线程ServerSocketCa去处理这个请求，数据收发完毕后会返回主线程程一个Sign标识，服务器再把这个标识发送到网关。
```
@Override
    public Object call() throws Exception {
        try {
            InputStream is=null;//输入流
            OutputStream os=null;//输出流
            is=socket_th.getInputStream();//获取输入流
            os=socket_th.getOutputStream();//获取输出流
            byte[] buff=new byte[5];
            is.read(buff);
            String signfromhtml =new String(buff);
            System.out.println(signfromhtml);
            signtogateway=Integer.valueOf(signfromhtml.trim());
            socket_th.shutdownInput();//关闭输入流
            is.close();
            os.close();
        }
```
### (4) 发送用户命令到网关：线程SendOrderToGateWay
向网关发起一次Socke请求，并传送用户命令。
```
@Override
    public void run() {
        try {
            System.out.println("向网关发起Socket请求的线程初始化完毕！");
            System.out.println("向网关发起请求！");
            OutputStream os=new Socket("192.168.134.110",9999).getOutputStream();
            os.write(temp.getBytes("UTF-8"));
            os.close();
}
```
### (5) 数据存储: JDBC
服务器接收到网关上传的各个传感器的实时数据后上传到MySql数据库进行存储。
```
try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,NAME,PASSWORD);
                   }
private static final String URL="jdbc:mysql://127.0.0.1:3306/ihome";
    private static final String NAME="root";
    private static final String PASSWORD="root";
    private static Connection conn=null;
 String sql=""+ " insert into ihome"+ " (temp,hump,light,smoke,infray,shake)"+
                                " values("+Integer.valueOf(temp)+","+Integer.valueOf(hump)+","+Double.valueOf(light)+","+
                                Integer.valueOf(smoke)+","+Integer.valueOf(infray)+","+Double.valueOf(shake)+")";
java.sql.PreparedStatement ptmt=conn.prepareStatement(sql);
ptmt.execute();
```
--- 
## 9.网关-开发板关键代码
### (1) 和服务器保持Socket长连接：线程SocketTh
```
void SocketTh::run(){
    socket=new QTcpSocket();
    socket->connectToHost(QHostAddress(connectIP), connectPORT);
    isconnect=socket->waitForConnected();
    qDebug()<<"connect sucessful";
}
```
### (2) 接收服务器发送的用户命令：主线程
```
Gatewayserver = new QTcpServer();
    qDebug()<<"lisening 9999";
    Gatewayserver->listen(QHostAddress::Any, 9999);
    connect(Gatewayserver, SIGNAL(newConnection()), this, SLOT(acceptConnection()));

void MainWindow::acceptConnection(){
    qDebug()<<"server ask connect";
    Gatewayclient = Gatewayserver->nextPendingConnection();
    connect(Gatewayclient, SIGNAL(readyRead()), this, SLOT(readMsg()));
}
void MainWindow::readMsg(){
    QString msg;
    msg=Gatewayclient->readAll();
    if(msg.length()!=0){
        int order=msg.toInt();
        if(order==110){//开继电器
            changeRelay(relays->getState());
        }else if(order==111){//关继电器
            changeRelay(relays->getState());
        }else if(order==120){//电机正转
            changeMotor(1);
        }else if(order==121){//电机到转
            changeMotor(2);
        }
    }
}
```

---
## 8.总结
通过这次实训对多线程、Socket通信、JDBC、Ajax等技术有了一个感性的认识，第一次正在在项目中应用多线程、socket等技术遇到了不少问题，不过还是一一解决了，收获很多，不足之处是许多细节没有考虑很全面，比如Socket掉线重连等等都没有解决。

闲来无事睡不着，更新下当年的心血之作。代码虽青涩，想象力丰富。

