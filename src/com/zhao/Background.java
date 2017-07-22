package com.zhao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by zhaoxudong on 2017/7/13.
 * 这是网站的后台，用户点击，在这里处理
 */
public class Background extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取客户端发送来的状态标识sign，用户名，密码
        String sign=request.getParameter("sign");//标识这是用户的那种操作
        Integer Sign=Integer.valueOf(sign);//转换后的标识

        if(Sign==110){                                                          //继电器模块
            System.out.println("用户想开空调");
            response.getWriter().write(1);
            AppConnectServer appConnectServer=new AppConnectServer(Sign);
            appConnectServer.connect();
        }else if(Sign==111){
            System.out.println("用户想关空调");
            response.getWriter().write(1);//向前台发送标识，标识员工登陆状态
            AppConnectServer appConnectServer=new AppConnectServer(Sign);
            appConnectServer.connect();
        }else if(Sign==120){                                                    //电机模块
            System.out.println("用户想开窗帘");
            response.getWriter().write(1);//向前台发送标识，标识员工登陆状态
            AppConnectServer appConnectServer=new AppConnectServer(Sign);
            appConnectServer.connect();
        }else if(Sign==121){
            System.out.println("用户想关窗帘");
            response.getWriter().write(1);//向前台发送标识，标识员工登陆状态
            AppConnectServer appConnectServer=new AppConnectServer(Sign);
            appConnectServer.connect();

        }else if(Sign==10110){                                                    //前端请求节点数据
            //前端需要最新的信息
            //链接数据库部分
            System.out.printf("正在更新界面");
            String URL="jdbc:mysql://127.0.0.1:3306/ihome";
            String NAME="root";
            final String PASSWORD="root";
            Connection conn=null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                //2.获得数据库的连接
                conn= DriverManager.getConnection(URL,NAME,PASSWORD);
                System.out.println("数据库连接成功");
                String sql=""+
                        "select * from ihome_update ";
                java.sql.PreparedStatement ptmt=conn.prepareStatement(sql);
                ResultSet rs=ptmt.executeQuery();
                rs.next();
                String temp_send_html=rs.getString(1);
                String hump_send_html=rs.getString(2);
                String light_send_html=rs.getString(3);
                String smoke_send_html=rs.getString(4);
                String infray_send_html=rs.getString(5);
                String shake_send_html=rs.getString(6);
                String msg_to_html=temp_send_html+"-"+hump_send_html+"-"+light_send_html+"-"+smoke_send_html+"-"+
                        infray_send_html+"-"+shake_send_html;

                response.getWriter().write(msg_to_html);//向前台发送数据

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }



        }

    }
}
