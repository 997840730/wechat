package wangkaisheng.weChat.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Md5UtilsTest {

    @Test
    void getDigest() {
        String s = "kasadsad011223";
        String digest = Md5Utils.getDigest(s);
        System.out.println(digest);
    }
}