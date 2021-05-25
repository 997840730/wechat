package wangkaisheng.weChat.dao;

import wangkaisheng.weChat.po.*;

import java.util.List;

public interface UserDao{

    User getUserByUsername(User user);

    boolean insrtUser(User user);


    User getUsersById(Friends f);


    boolean modify(User user);


    boolean addfriend(Friends friends);

}
