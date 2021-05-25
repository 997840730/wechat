package wangkaisheng.weChat.dao.impl;

import org.junit.jupiter.api.Test;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Notice;
import wangkaisheng.weChat.po.Noticed;
import wangkaisheng.weChat.service.impl.NoticeServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class NoticeDaoImplTest extends NoticeServiceImpl {

    @Test
    void insertnoticed() {
        Noticed noticed = new Noticed();
        noticed.setSendid(1);
        noticed.setUserid(4);
        noticed.setContant("啦啦啦啦");
        noticed.setGroupname("111");
        noticed.setName("null");
        ServiceResult insertnoticed = super.insertnoticed(noticed);
        System.out.println(insertnoticed);
    }

    @Test
    void selectnotice() {
    }

    @Test
    void testInsertnotice() {
    }

    @Test
    void deleteNotice() {
    }
}