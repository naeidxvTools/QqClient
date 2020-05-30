package net.imwork.zhanlong.test2;

import javax.sound.midi.Soundbank;
import java.io.*;
import java.net.Socket;

/**
 * 这是一个客户端程序，可以连接服务器
 */
public class MyClient
{
    public MyClient()
    {
        try (
                Socket s = new Socket("127.0.0.1", 9999);
        )
        {
            BufferedReader br2 = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter ps = new PrintWriter(s.getOutputStream(), true);

            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            while (true)
            {
                System.out.print("请输入你想对服务器说的话： ");

                // 从控制台接收的信息发给服务器
                String consoleMess = br2.readLine();
                ps.println(consoleMess);

                // 客户端如果从控制台输入bye，就退出聊天
                if (consoleMess.equals("bye"))
                {
                    System.out.println("对话结束！");
                    s.close();
                    break;
                }

                // 接收从服务器发来的信息
                String mess = br.readLine();
                System.out.println("服务器端发来的信息： " + mess);

            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        new MyClient();
    }


}
