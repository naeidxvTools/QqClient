package net.imwork.zhanlong.qq.client.view;


import net.imwork.zhanlong.qq.client.tools.ManageQqChat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QqFriendList extends JFrame
{

    // 处理第一张卡片
    JPanel jphy1, jphy2, jphy3;
    JButton jphy_jb1, jphy_jb2, jphy_jb3;
    JScrollPane jsp1;

    // 处理第二张卡片（陌生人）
    JPanel jpmsr1, jpmsr2, jpmsr3;
    JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3;
    JScrollPane jsp2;

    JLabel[] jbls1, jbls2;

    // 把整个JFrame设置成CardLayout
    CardLayout cardLayout;

    public QqFriendList(String ownerId)
    {
        // 处理第一张卡片（显示好友列表）
        jphy_jb1 = new JButton("我的好友");
        jphy_jb2 = new JButton("陌生人");
        jphy_jb2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.show(getContentPane(), "2");
            }
        });
        jphy_jb3 = new JButton("黑名单");

        jphy1 = new JPanel(new BorderLayout());
        // 假定有50个好友
        jphy2 = new JPanel(new GridLayout(50, 1, 4, 4));

        // 给jphy2初始化50个好友
        jbls1 = new JLabel[50];
        for (int i = 0; i < jbls1.length; i++)
        {
            jbls1[i] = new JLabel(i + 1 + "", new ImageIcon("images/mm.jpg"), JLabel.LEFT);
            jbls1[i].addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseEntered(MouseEvent e)
                {
                    ((JLabel)e.getSource()).setForeground(Color.RED);
                }

                @Override
                public void mouseExited(MouseEvent e)
                {
                    ((JLabel)e.getSource()).setForeground(Color.BLACK);
                }

                @Override
                public void mouseClicked(MouseEvent e)
                {
                    if (e.getClickCount() == 2)
                    {
                        String friendNo = ((JLabel) e.getSource()).getText();
                        //System.out.println("你希望和 " + friendNo + " 聊天！");
                        QqChat qqChat = new QqChat(ownerId, friendNo);

                        ManageQqChat.addQqChat(ownerId + friendNo, qqChat);

                    }
                }
            });
//            jbls1[i].setEnabled(false);
            jphy2.add(jbls1[i]);
        }

        jphy3 = new JPanel(new GridLayout(2, 1));
        // 把两个按钮加入到jphy3
        jphy3.add(jphy_jb2);
        jphy3.add(jphy_jb3);

        jsp1 = new JScrollPane(jphy2);

        // 对jphy1初始化
        jphy1.add(jphy_jb1, "North");
        jphy1.add(jsp1, "Center");
        jphy1.add(jphy3, "South");

        // 处理第二张卡片
        jpmsr_jb1 = new JButton("我的好友");
        jpmsr_jb1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                cardLayout.show(getContentPane(), "1");
            }
        });
        jpmsr_jb2 = new JButton("陌生人");
        jpmsr_jb3 = new JButton("黑名单");
        jpmsr1 = new JPanel(new BorderLayout());
        // 假定有20个陌生人
        jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));
        jbls2 = new JLabel[20];
        for (int i = 0; i < jbls2.length; i++)
        {
            jbls2[i] = new JLabel(i + 1 + "", new ImageIcon("images/mm.jpg"), JLabel.LEFT);
            jpmsr2.add(jbls2[i]);
        }

        jpmsr3 = new JPanel(new GridLayout(2, 1));
        // 把两个按钮加入到jpmsr3
        jpmsr3.add(jpmsr_jb1);
        jpmsr3.add(jpmsr_jb2);

        jsp2 = new JScrollPane(jpmsr2);

        // 对jpmsr1初始化
        jpmsr1.add(jpmsr3, "North");
        jpmsr1.add(jsp2, "Center");
        jpmsr1.add(jpmsr_jb3, "South");

        this.setSize(260, 500);
        this.setLocation(800, 200);

        cardLayout = new CardLayout();
        this.setLayout(cardLayout);

        this.add(jphy1, "1");
        this.add(jpmsr1, "2");
        this.setTitle(ownerId);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

//    public static void main(String[] args)
//    {
//        new QqFriendList();
//    }
}

