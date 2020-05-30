package net.imwork.zhanlong.test2;

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

            // 先接收客户端发来的信息
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            // 接收控制台输入的信息
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));

            // 输出信息
            PrintStream ps = new PrintStream(s.getOutputStream(),true);

            // 发送给客户端信息
            while (true)
            {
                // 接收从客户端发来的信息
                String mess = br.readLine();
                System.out.println("客户端发来的信息： " + mess);

                // 客户端发来bye，结束聊天，服务器退出聊天
                if (mess.equals("bye"))
                {
                    System.out.println("对话结束！");
                    s.close();
                    break;
                }

                // 从控制台接收的信息发给客户端
                System.out.print("输入你要对客户端说的话： ");
                ps.println(br2.readLine());
                //s.shutdownOutput();
            }
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
