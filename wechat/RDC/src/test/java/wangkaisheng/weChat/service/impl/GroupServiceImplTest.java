package wangkaisheng.weChat.service.impl;

import org.junit.jupiter.api.Test;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Groups;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.impl.GroupServiceImpl;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class GroupServiceImplTest extends GroupServiceImpl {

    @Test
    void findGroups() {
    }

    @Test
    void searchGroup() {
        Groups groups = new Groups();
        groups.setGroupname("啦啦啦啦");
        groups.setGroupUser(1);
        groups.setIntroduce("拉萨喝多了看啥老大牛");
        System.out.println(groups);
        ServiceResult serviceResult = super.creatdGroup(groups);
        System.out.println(serviceResult.getMessage());
    }

    @Test
    void testSearchGroup() {
        User user = new User();
        user.setWechatId(4);
        ServiceResult result = super.searchGroup("什么", user);
        System.out.println(result);
    }
}