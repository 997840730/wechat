package wangkaisheng.weChat.service;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Friends;
import wangkaisheng.weChat.po.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface FriendService {

    List<User> findFriends(User user);

    ServiceResult searchFriend(String parameter,User user);

    ServiceResult addfriend(Friends friends);
}
