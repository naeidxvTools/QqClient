package net.imwork.zhanlong.qq.client.model;


import net.imwork.zhanlong.qq.common.Message;
import net.imwork.zhanlong.qq.common.User;
import net.imwork.zhanlong.qq.client.tools.ClientConServerThread;
import net.imwork.zhanlong.qq.client.tools.ManageClientConServerThread;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class QqClientConServer
{
    public Socket socket;

    // 发送第一次请求
    public boolean sendLoginInfoToServer(Object o)
    {
        boolean b = false;
        try
        {
            socket = new Socket("127.0.0.1", 9999);

            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(o);

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Message message = (Message) ois.readObject();

            if (message.getMesType().equals("1"))
            {
                ClientConServerThread clientConServerThread = new ClientConServerThread(socket);
                clientConServerThread.start();
                ManageClientConServerThread.addClientConServerThread(((User)o).getUserId(), clientConServerThread);

                b = true;
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        return b;
    }
}
