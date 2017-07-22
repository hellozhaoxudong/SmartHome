package com.zhao;

import jdk.internal.dynalink.beans.StaticClass;
import sun.applet.Main;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * 历经各种各种sb操作，终于搞定向网关发送命令
 *
 * Created by zhaoxudong on 2017/7/19.
 */
public class SendOrdertoGate{
    Socket sockettogateway;
    OutputStream os;
    public SendOrdertoGate(){

    }
    public void sendtogateway(int sign){
        try {
            sockettogateway=new Socket("192.168.134.110",9999);//和服务器建立连接，服务器运行在本机
            System.out.println("已取得和网关9999端口的连接");
            os=sockettogateway.getOutputStream();
            os.write(String.valueOf(sign).getBytes("UTF-8"));
            os.close();
            System.out.println("已发送");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}