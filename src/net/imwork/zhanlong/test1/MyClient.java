package net.imwork.zhanlong.test1;

import java.io.*;
import java.net.Socket;

/**
 * 这是一个客户端程序，可以连接服务器
 *
 */
public class MyClient
{
    public MyClient()
    {
        try
        {
            Socket s = new Socket("127.0.0.1", 9999);

            OutputStream os = s.getOutputStream();
            PrintWriter ps = new PrintWriter(os);
            ps.print("我是客户端，你好，hello");
            ps.flush();
            s.shutdownOutput();

            InputStream is = s.getInputStream();
            System.out.println(is);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            System.out.println(br.readLine());

            s.close();
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
