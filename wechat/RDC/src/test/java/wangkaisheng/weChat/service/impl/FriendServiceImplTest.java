package wangkaisheng.weChat.service.impl;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.FriendService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FriendServiceImplTest extends FriendServiceImpl {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findFriends() {
    }

    @Test
    void searchFriend() {
        User user = new User();
        user.setWechatId(2);
        ServiceResult serviceResult = super.searchFriend("1", user);
        System.out.println((List<User>)serviceResult.getData());
    }

    @Test
    void testSearchFriend() {
        User user = new User();
        user.setWechatId(1);
        ServiceResult result = super.searchFriend("3", user);
        System.out.println(result);
    }

    @Test
    void testFindFriends() {
        User user = new User();
        user.setWechatId(7);
        List<User> friends = findFriends(user);
        System.out.println(friends);
    }

    @Test
    void testAddfriend() {
    }
}