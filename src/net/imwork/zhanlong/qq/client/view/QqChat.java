package net.imwork.zhanlong.qq.client.view;

import net.imwork.zhanlong.qq.common.Message;
import net.imwork.zhanlong.qq.client.tools.ManageClientConServerThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class QqChat extends JFrame
{
    JTextArea jTextArea;
    JTextField jTextField;
    JButton jb;
    JPanel jPanel;

    public QqChat(String ownerId, String friend)
    {
        jTextArea = new JTextArea();
        jTextField = new JTextField(15);
        jb = new JButton("发送");

        jb.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                Message message = new Message();
                message.setSender(ownerId);
                message.setGetter(friend);
                message.setCon(jTextField.getText());
                message.setSendTime(new java.util.Date().toString());

                try
                {
                    Socket socket = ManageClientConServerThread.getClientConServerThread(ownerId).getSocket();
                    ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                    oos.writeObject(message);

                } catch (IOException ioException)
                {
                    ioException.printStackTrace();
                }

            }
        });

        jPanel = new JPanel();
        jPanel.add(jTextField);
        jPanel.add(jb);

        this.add(jTextArea, "Center");
        this.add(jPanel, "South");

        this.setSize(300, 200);
        this.setLocation(500, 200);
        this.setTitle(ownerId + " 正在和 " + friend + " 聊天");
        this.setIconImage((new ImageIcon("images/qq.gif")).getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);


    }

    public void showMessage(Message message)
    {
        //显示
        String info = message.getSender() + " 对 " + message.getGetter() + " 说：" + message.getCon() + "\r\n";

        this.jTextArea.append(info);
    }

//    @Override
//    public void run()
//    {
//        while (true)
//        {
//            try
//            {
//                // 阻塞
//                ObjectInputStream ois = new ObjectInputStream(QqClientConServer.socket.getInputStream());
//
//                Message message = (Message) ois.readObject();
//
//                // 显示
//                String info = message.getSender() + " 对 " + message.getGetter() + " 说：" + message.getCon() + "\r\n";
//
//                this.jTextArea.append(info);
//
//
//            } catch (Exception e)
//            {
//                e.printStackTrace();
//            }
//        }
//    }
}
