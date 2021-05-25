package wangkaisheng.weChat.service.impl;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Friends;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.FriendService;
import wangkaisheng.weChat.service.Service;
import wangkaisheng.weChat.service.contants.ServiceMessage;
import wangkaisheng.weChat.web.controller.contants.Status;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Administrator
 */
public class FriendServiceImpl extends Service implements FriendService {
    @Override
    public List<User> findFriends(User user) {
        Friends friend = new Friends();
        friend.setUid(user.getWechatId());
        List<Friends> friends = friendDao.getFriends(friend);

        List<User> list = new LinkedList<>();
        for (Friends f: friends){
            User temp = userDao.getUsersById(f);
            list.add(temp);
            temp.setPassword(null);
        }
        return list;
    }

    @Override
    public ServiceResult searchFriend(String parameter,User myuser) {
        User user = new User();
        user.setUsername(parameter);
        List<User> list =  friendDao.searchFriend(user);
        user.setUsername(null);
        user.setName(parameter);
        List<User> list1 = friendDao.searchFriend(user);
        for (User u:list1){
            for (int i=0;i<list.size();i++){
                if (u.getWechatId().equals(list.get(i).getWechatId())){
                    list.remove(i);
                }
            }
        }
        list.addAll(list1);
        List<User> friends = findFriends(myuser);
        for (int i = 0;i<list.size();i++){
            for (User temp:friends){
                if (myuser.getWechatId().equals(list.get(i).getWechatId())||temp.getWechatId().equals(list.get(i).getWechatId())){
                    list.remove(i);
                    i--;
                    break;
                }
            }
        }
        if (list.isEmpty()){
            return new ServiceResult(Status.ERROR, ServiceMessage.USER_NO.message,null);
        }else {
            return new ServiceResult(Status.SUCCESS, ServiceMessage.USER_YES.message,list);
        }
    }

    @Override
    public ServiceResult addfriend(Friends friends) {
        Friends friends1 = new Friends();
        friends1.setFid(friends.getUid());
        friends1.setUid(friends.getFid());
        if (userDao.addfriend(friends)&&userDao.addfriend(friends1)){
            return new ServiceResult(Status.SUCCESS, ServiceMessage.ADDFRIEND_SUCCESS.message,null);
        }else {
            return new ServiceResult(Status.ERROR,ServiceMessage.ADDFRIEND_ERROR.message,null);
        }

    }
}
