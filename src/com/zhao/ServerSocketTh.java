package com.zhao;

import java.io.*;
import java.net.Socket;

/**
 * 废弃了！！！！！
 *
 */

/**
 * Created by zhaoxudong on 2017/7/14.
 * 服务器有连接时启动这个线程和网站进行通信
 * Server.java会调用getSigntogateway()方法获取命令标识
 */
public class ServerSocketTh extends Thread{
    Socket socket;
    int signtogateway;//这个进程结束要返回一个标识，这个标识是HTML传过来的，然后返回主进程，让主进程发送到网关
    public ServerSocketTh(Socket socket){
        this.socket=socket;
    }

    //执行线程操作，响应客户端的请求
    @Override
    public void run() {
        try {
            InputStream is=null;//输入流
            OutputStream os=null;//输出流
            is=socket.getInputStream();//获取输入流
            os=socket.getOutputStream();//获取输出流
            byte[] buff=new byte[5];
            is.read(buff);
            String signfromhtml =new String(buff);
            System.out.println(signfromhtml);
            signtogateway=Integer.valueOf(signfromhtml.trim());
            System.out.println("该线程收到用户的命令："+signtogateway);
            System.out.println("正在关闭socket");
            socket.shutdownInput();//关闭输入流

            is.close();
            os.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getSigntogateway() throws IOException {
        return signtogateway;
    }
}
