package com.zhao;

import java.io.*;
import java.net.Socket;

/**
 * Created by zhaoxudong on 2017/7/15.
 * Java Socket 保持长连接测试 客户端
 */
public class SocketAlwaysClient {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",8888);
        if(socket.isConnected()){
            OutputStream os=null;
            InputStream is=null;
            while(true){
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    String in = br.readLine();
                    os = socket.getOutputStream();
                    os.write(in.getBytes());//"hello".getBytes());
                    os.flush();

                    is = socket.getInputStream();
                    byte[] resp = new byte[5];
                    is.read(resp);
                    System.out.println("response:" + new String(resp));
                }catch (Exception e){

                }

            }
        }
    }

}
