package wangkaisheng.weChat.dao;

import wangkaisheng.weChat.po.Friends;
import wangkaisheng.weChat.po.User;

import java.util.List;

public interface FriendDao {

    List<Friends> getFriends(Friends friends);

    boolean addfriend(Friends friends);

    boolean offFriend(Friends friends);

    List<User> searchFriend(User user);
}
