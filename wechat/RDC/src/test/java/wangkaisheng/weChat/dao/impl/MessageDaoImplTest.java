package wangkaisheng.weChat.dao.impl;

import org.junit.jupiter.api.Test;
import wangkaisheng.weChat.po.Readed;

class MessageDaoImplTest extends MessageDaoImpl {

    @Test
    void insertRead() {
        Readed read = new Readed();
        read.setUid(1);
        read.setMid(3);
        super.insertRead(read);
    }
}