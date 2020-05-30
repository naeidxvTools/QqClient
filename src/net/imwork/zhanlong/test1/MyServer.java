package net.imwork.zhanlong.test1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 这是第一个服务端程序，让他在9999端口监听
 * 可以接收客户端的信息
 */
public class MyServer
{
    public MyServer()
    {
        try
        {
            // 在9999号端口进行监听
            ServerSocket ss = new ServerSocket(9999);
            System.out.println("我是服务器，在9999端口监听....");

            // 等待某个客户端来连接
            Socket s = ss.accept();

            InputStream is = s.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            System.out.println(br.readLine());

            OutputStream os = s.getOutputStream();
            PrintStream ps = new PrintStream(os);
            ps.print("我是服务器，你好，hello");
            s.shutdownOutput();


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {

        new MyServer();


    }
}
