package net.imwork.zhanlong.test3;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class MyClient extends JFrame
{
    JTextArea jta = null;
    JTextField jtf = null;
    JButton jb = null;
    JPanel jp = null;
    JScrollPane jsp = null;

    PrintStream ps = null;

    public MyClient()
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
                jta.append("客户端对服务器说： " + info + "\r\n");
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
                    jta.append("客户端对服务器说： " + info + "\r\n");
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

        this.setTitle("客户端：qq聊天");
        this.setSize(400,300);
        this.setLocation(600, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        try
        {
            Socket socket = new Socket("127.0.0.1", 9999);

            // 先接收服务端发来的信息
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // 输出信息
            ps = new PrintStream(socket.getOutputStream(),true);

            // 发送给服务端信息
            while (true)
            {
                // 接收从服务端发来的信息
                String mess = br.readLine();
                jta.append("服务器对客户端说： " + mess + "\r\n");

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
