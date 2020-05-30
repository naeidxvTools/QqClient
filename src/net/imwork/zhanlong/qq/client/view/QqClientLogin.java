package net.imwork.zhanlong.qq.client.view;

import net.imwork.zhanlong.qq.common.User;
import net.imwork.zhanlong.qq.client.model.QqClientUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 说明：客户端登录界面
 */
public class QqClientLogin extends JFrame implements ActionListener
{
    // 北部
    JLabel jLabel;

    // 中部
    JTabbedPane jTabbedPane;
    JPanel jPanel2, jPanel3, jPanel4;
    JLabel jLabel2, jLabel3, jLabel4, jLabel5;
    JButton jb4;
    JTextField jTextField;
    JPasswordField jPasswordField;
    JCheckBox jcb1, jcb2;


    // 南部
    JPanel jPanel;
    JButton jb1, jb2, jb3;


    public QqClientLogin()
    {
        jLabel = new JLabel(new ImageIcon("images/tou.gif"));

        jPanel2 = new JPanel(new GridLayout(3, 3,10,10));
        jLabel2 = new JLabel("QQ号码", JLabel.CENTER);
        jLabel3 = new JLabel("QQ密码", JLabel.CENTER);
        jLabel4 = new JLabel("忘记密码", JLabel.CENTER);
        jLabel4.setForeground(Color.BLUE);
        jLabel5 = new JLabel("申请密码保护", JLabel.CENTER);
        jLabel5.setForeground(Color.BLUE);

        ImageIcon clean = new ImageIcon("images/clear.gif");
        jb4 = new JButton(clean);

        jTextField = new JTextField();
        jPasswordField = new JPasswordField();
        jcb1 = new JCheckBox("隐身登录");
        jcb2 = new JCheckBox("记住密码");
        jPanel2.add(jLabel2);
        jPanel2.add(jTextField);
        jPanel2.add(jb4);
        jPanel2.add(jLabel3);
        jPanel2.add(jPasswordField);
        jPanel2.add(jLabel4);
        jPanel2.add(jcb1);
        jPanel2.add(jcb2);
        jPanel2.add(jLabel5);
        jPanel3 = new JPanel();
        jPanel4 = new JPanel();
        jTabbedPane = new JTabbedPane();
        jTabbedPane.add(jPanel2, "QQ号码");
        jTabbedPane.add(jPanel3, "手机号码");
        jTabbedPane.add(jPanel4, "电子邮箱");

        ImageIcon icon1 = new ImageIcon("images/denglu.gif");
        ImageIcon icon2 = new ImageIcon("images/quxiao.gif");
        ImageIcon icon3 = new ImageIcon("images/xiangdao.gif");

        jb1 = new JButton(icon1);
        jb1.setPreferredSize(new Dimension(icon1.getIconWidth(), icon1.getIconHeight()));
        jb1.addActionListener(this);

        jb2 = new JButton(icon2);
        jb2.setPreferredSize(new Dimension(icon2.getIconWidth(), icon2.getIconHeight()));
        jb3 = new JButton(icon3);
        jb3.setPreferredSize(new Dimension(icon3.getIconWidth(), icon3.getIconHeight()));

        jPanel = new JPanel();
        jPanel.add(jb1);
        jPanel.add(new JPanel());
        jPanel.add(jb2);
        jPanel.add(new JPanel());
        jPanel.add(jb3);

        this.add(jLabel, "North");
        this.add(jTabbedPane, "Center");
        this.add(jPanel, "South");
        this.setTitle("Qq聊天");

        this.setSize(350, 240);
        this.setLocation(500, 260);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setResizable(false);
    }

    public static void main(String[] args)
    {
        new QqClientLogin();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        QqClientUser qqClientUser = new QqClientUser();
        User user = new User();
        user.setUserId(jTextField.getText().trim());
        user.setPassword(new String(jPasswordField.getPassword()));

//        if (qqClientUser.checkUser(user))
        if (true)
        {
            QqFriendList qqFriendList = new QqFriendList(user.getUserId());
            this.setEnabled(false);
            // 关闭到登录界面
            //this.dispose();
        } else
        {
            JOptionPane.showMessageDialog(this, "用户名密码错误");
        }
    }
}
