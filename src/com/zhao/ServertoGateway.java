package com.zhao;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by zhaoxudong on 2017/7/15.
 * 服务器和网关在8899端口保持一个长连接状态的一个进程
 */
public class ServertoGateway extends Thread{
    ServerSocket serverSocket_toGateway= null;
    Socket socket_gateway=null;

    //链接数据库部分
    private static final String URL="jdbc:mysql://127.0.0.1:3306/ihome";
    private static final String NAME="root";
    private static final String PASSWORD="root";
    private static Connection conn=null;
    static {
        //1.加载驱动程序
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn= DriverManager.getConnection(URL,NAME,PASSWORD);
            System.out.println("数据库连接成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ServertoGateway(){
        //连接mysql数据库
        System.out.println("和网关保持socket长连接的服务器进程已初始化完毕");
    }
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
                        String[] sourceStrArray = tempdata.split("-");
                        String temp=sourceStrArray[0];
                        String hump=sourceStrArray[1];
                        String light=sourceStrArray[2];
                        String smoke=sourceStrArray[3];
                        String infray=sourceStrArray[4];
                        String shake=sourceStrArray[5];


                        String sql=""+
                                " insert into ihome"+
                                " (temp,hump,light,smoke,infray,shake)"+
                                " values("+Integer.valueOf(temp)+","+Integer.valueOf(hump)+","+Double.valueOf(light)+","+
                                Integer.valueOf(smoke)+","+Integer.valueOf(infray)+","+Double.valueOf(shake)+")";
                        String updatesql=""+
                                "update ihome_update SET temp="+Integer.valueOf(temp)+",hump="+Integer.valueOf(hump)+
                                ",light="+Double.valueOf(light)+",smoke="+Integer.valueOf(smoke)+",infray="+Integer.valueOf(infray)+
                                ",shake="+Double.valueOf(shake)+")";

                        java.sql.PreparedStatement ptmt=conn.prepareStatement(sql);
                        ptmt.execute();
                        java.sql.PreparedStatement ptmt2=conn.prepareStatement(updatesql);
                        ptmt2.executeUpdate();
                        System.out.println("添加数据完毕");
                    }catch (Exception e){
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void sendtoGateway(int ordersign) throws IOException {
        System.out.println("正在向网关发送命令");
        //socket.setKeepAlive(true);//检查两边是否连接

        OutputStream os=socket_gateway.getOutputStream();
        String test="hello";
        System.out.println("已经向服务器发送了："+test+ordersign);
        os.write(test.getBytes("UTF-8"));
        os.flush();
        os.close();
    }
}
