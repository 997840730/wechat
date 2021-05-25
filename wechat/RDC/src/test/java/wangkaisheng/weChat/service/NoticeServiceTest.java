package wangkaisheng.weChat.service;

import org.junit.jupiter.api.Test;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.impl.NoticeServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

class NoticeServiceTest extends NoticeServiceImpl {

    @Test
    void findnotice() {
        User user = new User();
        user.setWechatId(1);
        ServiceResult findnotice = super.findnotice(user);
        System.out.println(findnotice.getData());
    }

    @Test
    void findnoticed() {
    }

    @Test
    void testFindnoticed() {

    }
}