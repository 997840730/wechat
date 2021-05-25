package wangkaisheng.weChat.service.impl;

import org.junit.jupiter.api.Test;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Notice;
import wangkaisheng.weChat.po.Noticed;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.NoticeService;

import static org.junit.jupiter.api.Assertions.*;

class NoticeServiceImplTest extends NoticeServiceImpl {

    @Test
    void insertnotice() {
        Notice notice = new Notice();
        notice.setUserid(1);
        notice.setType(0);
        notice.setSendid(3);
        ServiceResult insertnotice = super.insertnotice(notice);
        System.out.println(insertnotice.getMessage());
    }

    @Test
    void testFindnotice() {
        User user  =new User();
        user.setWechatId(4);
        ServiceResult findnotice = super.findnotice(user);
        System.out.println(findnotice.getMessage());
    }

    @Test
    void testFindnoticed() {
        User user  =new User();
        user.setWechatId(4);
        ServiceResult findnotice = super.findnoticed(user);
        System.out.println(findnotice.getData());
    }

    @Test
    void testInsertnoticed() {
        Noticed noticed = new Noticed();
        noticed.setContant("对方已同意添加您为好友");
        noticed.setUserid(4);
        noticed.setSendid(1);
        noticed.setName("秋");
        noticed.setGroupname("null");
        ServiceResult insertnoticed = super.insertnoticed(noticed);
        System.out.println(insertnoticed);
    }

}