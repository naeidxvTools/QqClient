package net.imwork.zhanlong.qq.client.tools;

import net.imwork.zhanlong.qq.common.Message;
import net.imwork.zhanlong.qq.client.view.QqChat;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ClientConServerThread extends Thread
{
    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }

    private Socket socket;

    public ClientConServerThread(Socket socket)
    {
        this.socket = socket;
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

                Message message = (Message) ois.readObject();

                String info = message.getSender() + " 对 " + message.getGetter() + " 说：" + message.getCon() + "\r\n";

                QqChat qqChat = ManageQqChat.getQqChat(message.getGetter() + message.getSender());

                qqChat.showMessage(message);

            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
