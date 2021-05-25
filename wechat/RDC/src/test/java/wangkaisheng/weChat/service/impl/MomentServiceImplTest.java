package wangkaisheng.weChat.service.impl;

import org.junit.jupiter.api.Test;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Imgs;
import wangkaisheng.weChat.po.Moments;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.util.UUIDutils;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MomentServiceImplTest extends MomentServiceImpl {

    @Test
    void addmoment() {
        Moments moments = new Moments();
        moments.setUid(1);
        moments.setUuid(UUIDutils.getUUID());
        ServiceResult addmoment = super.addmoment(moments);
        System.out.println(addmoment);
    }

    @Test
    void insertImgs() {
        Moments moments = new Moments();
        moments.setUid(1);
        String uu = UUIDutils.getUUID();
        moments.setUuid(uu);
        ServiceResult addmoment = super.addmoment(moments);
        Moments data = (Moments) addmoment.getData();
        Imgs imgs  =new Imgs();
        imgs.setImg("qweqwe");
        imgs.setMid(data.getId());
        super.insertImgs(imgs);
    }

    @Test
    void testListmoments() {
        User user  = new User();
        user.setWechatId(1);
        FriendServiceImpl friendService = new FriendServiceImplTest();
        List<User> friends = friendService.findFriends(user);
        friends.add(user);
        List<Moments> listmoments = super.listmoments(friends);
        System.out.println(listmoments);
        List<Moments> listmoment = super.listmoment(listmoments);
        System.out.println(listmoment);
    }

    @Test
    void testListmoment() {
        List<Moments> list = new LinkedList<>();
        Moments moments = new Moments();
        moments.setId(4);
        list.add(moments);
        List<Moments> listmoment = super.listmoment(list);
        System.out.println(listmoment);
    }

    @Test
    void testListmoments1() {
    }
}