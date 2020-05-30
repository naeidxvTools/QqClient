package net.imwork.zhanlong.qq.client.model;

import net.imwork.zhanlong.qq.common.User;

public class QqClientUser
{
    public boolean checkUser(User user)
    {
        return new QqClientConServer().sendLoginInfoToServer(user);
    }
}
