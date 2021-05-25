package wangkaisheng.weChat.util;

import java.util.UUID;

public class UUIDutils {
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-","");
    }
}
