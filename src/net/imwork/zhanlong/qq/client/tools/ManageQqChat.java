package net.imwork.zhanlong.qq.client.tools;

import net.imwork.zhanlong.qq.client.view.QqChat;

import java.util.HashMap;

public class ManageQqChat
{
    private static HashMap<String, QqChat> map = new HashMap<>();

    public static void addQqChat(String loginIdAndFriendId, QqChat qqChat)
    {
        map.put(loginIdAndFriendId, qqChat);
    }

    public static QqChat getQqChat(String loginIdAndFriendId)
    {
        return map.get(loginIdAndFriendId);
    }

}
