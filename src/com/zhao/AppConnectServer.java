package com.zhao;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by zhaoxudong on 2017/7/14.
 * 移动端如果有请求，即用户点击了按钮
 * 调用这个类向服务器发起请求
 */
public class AppConnectServer {
    int sign=0;
    public AppConnectServer(int sign){
        this.sign=sign;
    }
    public void connect(){
        try {
            //1.创建客户Socket，指定服务器地址和端口
            Socket socket=new Socket("localhost",8889);//和服务器建立连接，服务器运行在本机
            System.out.println("网站已连接服务器");
            //2.获取输出流，向服务器发送消息
            OutputStream os=socket.getOutputStream();//字节输出流
            PrintWriter pw=new PrintWriter(os);//将输出流包装为打印流
            pw.write(String.valueOf(sign));
            pw.flush();

            socket.shutdownOutput();//关闭输出流
            pw.close();
            os.close();
            socket.close();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
