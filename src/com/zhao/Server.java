package com.zhao;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by zhaoxudong on 2017/7/14.
 * 服务器
 * 在8889端口等待移动端连接
 * 如果有链接就调用ServerSocket。java处理这个链接，并返回一个命令标记
 */
public class Server {
    public static void main(String[] args){
        try {
            ServertoGateway toGatewayTh=new ServertoGateway();//这个线程就一直负责接收网关的信息就好。
            toGatewayTh.start();
            //1.创建一个服务端socket，即ServerSocket，指定绑定的端口，并监听此端口
            ServerSocket serverSocket=new ServerSocket(8889);
            //2.调用accept方法等待客户端的连接
            Socket server_socket_th=null;
            while (true){
                server_socket_th=serverSocket.accept();
                //创建一个新线程处理这个请求
                System.out.println("有新的客户端连接进来了");
                ExecutorService exec = Executors.newCachedThreadPool();
                ServerSocketCa serverSocketCa=new ServerSocketCa(server_socket_th);
                Future f=exec.submit(serverSocketCa);
                int ordertogateway=Integer.valueOf(f.get().toString());
                System.out.println("线程返回了用户传递来的命令："+ordertogateway);
                 //在这里已经获取到了要向网关发送的命令，直接调用另一个类，向网关发起请求，传送sign
                SendOrdertoGate sendnow=new SendOrdertoGate();
                sendnow.sendtogateway(ordertogateway);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
