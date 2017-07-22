package com.zhao;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by zhaoxudong on 2017/7/15.
 * Java Socket 保持长连接测试 服务器端
 */
public class SocketAlwaysServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8888);
        Socket socket=serverSocket.accept();
        while (true){//保持长连接
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (socket != null) {
                InputStream is=null;//输入流
                OutputStream os=null;//输出流
                try {
                    String ip=socket.getInetAddress().toString().replace("/","");
                    System.out.println("客户端的Ip地址为："+ip);
                    socket.setKeepAlive(true);//检查两边是否连接
                    is=socket.getInputStream();//获取输入流
                    os=socket.getOutputStream();//获取输出流
                    byte[] buff=new byte[5];
                    is.read(buff);
                    String tempdata =new String(buff);
                    if (tempdata.equals("quit1")) {
                        System.out.println("不处理，继续：："+tempdata);
                        continue;
                    }
                    System.out.println("接收到的数据为："+tempdata);
                    os.write("world".getBytes());
                    os.flush();  //发送响应

                }catch (Exception e){

                }
            }

        }
    }
}
