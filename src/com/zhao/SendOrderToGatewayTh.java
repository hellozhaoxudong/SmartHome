package com.zhao;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * 这是向网关发起socket请求的线程，连接成功后发送order
 * Created by zhaoxudong on 2017/7/20.
 */
public class SendOrderToGatewayTh extends Thread{
    int sendtogateSign;
    Socket sendtogatesocket=null;
    DataOutputStream sendtogatesocketOS=null;
    public SendOrderToGatewayTh(int sendtogateSign) throws IOException {
        this.sendtogateSign=sendtogateSign;
    }
    @Override
    public void run() {
        try {
            System.out.println("向网关发起Socket请求的线程初始化完毕！");
            //1.创建客户Socket，指定服务器地址和端口
            System.out.println("向网关发起请求！");
            OutputStream os=new Socket("192.168.134.110",9999).getOutputStream();
            String temp=new String("111111");
            os.write(temp.getBytes("UTF-8"));
            os.close();
//            sendtogatesocket=new Socket("192.168.134.110",9999);//向网关发起请求
//            System.out.println("服务器向网关发起Socket请求成功！");
//            //2.获取输出流，向服务器发送消息
//            sendtogatesocketOS=new DataOutputStream(sendtogatesocket.getOutputStream());
//            sendtogatesocketOS.write(String.valueOf(sendtogateSign).getBytes());
//            sendtogatesocketOS.close();
//            System.out.println("已经发送消息");
//            sendtogatesocket.shutdownOutput();//关闭输出
//            sendtogatesocketOS.close();
//            sendtogatesocket.close();
        }catch (Exception e){

        }

    }
}
