package net.imwork.zhanlong.test3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer extends JFrame
{
    JTextArea jta = null;
    JTextField jtf = null;
    JButton jb = null;
    JPanel jp = null;
    JScrollPane jsp = null;

    PrintStream ps = null;

    public MyServer()
    {
        jta = new JTextArea();
        jta.setEnabled(false);
        jsp = new JScrollPane(jta);

        jtf = new JTextField(15);

        jtf.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String info = jtf.getText();
                jta.append("服务器对客户端说： " + info + "\r\n");
                ps.println(info);
                jtf.setText("");
            }
        });
        jb = new JButton("发送");

        // 监听按钮点击事件
        jb.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (e.getSource() == jb)
                {
                    String info = jtf.getText();
                    jta.append("服务器对客户端说： " + info + "\r\n");
                    ps.println(info);
                    jtf.setText("");
                }
            }
        });

        jp = new JPanel();
        jp.add(jtf);
        jp.add(jb);

        this.add(jsp,"Center");
        this.add(jp, "South");

        this.setTitle("服务端：qq聊天");
        this.setSize(400,300);
        this.setLocation(100, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        try
        {
            // 服务器监听
            ServerSocket serverSocket = new ServerSocket(9999);

            // 等待连接
            Socket socket = serverSocket.accept();

            // 先接收客户端发来的信息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 输出信息
            ps = new PrintStream(socket.getOutputStream(),true);

            // 发送给客户端信息
            while (true)
            {
                // 接收从客户端发来的信息
                String mess = br.readLine();
                jta.append("客户端对服务器说： " + mess + "\r\n");

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
