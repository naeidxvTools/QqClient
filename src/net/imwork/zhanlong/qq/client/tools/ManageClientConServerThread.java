package net.imwork.zhanlong.qq.client.tools;

import java.util.HashMap;

public class ManageClientConServerThread
{
    private static HashMap<String, ClientConServerThread> map = new HashMap<>();

    public static void addClientConServerThread(String qqId, ClientConServerThread clientConServerThread)
    {
        map.put(qqId, clientConServerThread);
    }

    public static ClientConServerThread getClientConServerThread(String qqId)
    {
        return map.get(qqId);
    }
}
