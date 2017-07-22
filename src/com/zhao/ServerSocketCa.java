package com.zhao;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Callable;

/**
 * Java线程：这是一个有返回值的线程！！！！！神奇！！
 *
 * Created by zhaoxudong on 2017/7/19.
 */
public class ServerSocketCa implements Callable{
    Socket socket_th;
    int signtogateway;//这个进程结束要返回一个标识，这个标识是HTML传过来的，然后返回主进程，让主进程发送到网关

    public ServerSocketCa(Socket socket_server_th){

        socket_th=socket_server_th;
    }
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
            System.out.println("该线程收到用户的命令："+signtogateway);

            System.out.println("正在关闭socket");
            socket_th.shutdownInput();//关闭输入流

            is.close();
            os.close();
            //socket.close();
        }catch (Exception e){
        }
        return signtogateway;
    }
}
