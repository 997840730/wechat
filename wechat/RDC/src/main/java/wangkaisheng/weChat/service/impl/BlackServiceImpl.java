package wangkaisheng.weChat.service.impl;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.BlackList;
import wangkaisheng.weChat.po.Friends;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.BlackService;
import wangkaisheng.weChat.service.Service;
import wangkaisheng.weChat.web.controller.contants.Status;

import java.util.LinkedList;
import java.util.List;

import static wangkaisheng.weChat.service.contants.ServiceMessage.*;
import static wangkaisheng.weChat.service.contants.ServiceMessage.OFFBLACK_ERROR;

public class BlackServiceImpl extends Service implements BlackService {

    @Override
    public List<User> findBlacklist(User user) {
        BlackList blackList = new BlackList();
        blackList.setUid(user.getWechatId());
        List<BlackList> blackList1 = blackDao.getBlackList(blackList);
        List<User> list = new LinkedList<>();
        Friends friends = new Friends();
        for (BlackList f: blackList1){
            friends.setFid(f.getBid());
            User temp = userDao.getUsersById(friends);
            list.add(temp);
            temp.setPassword(null);
        }
        return list;
    }

    @Override
    public ServiceResult addblack(BlackList blackList) {
        ServiceResult serviceResult=null;
        Friends friends = new Friends();
        friends.setUid(blackList.getUid());
        friends.setFid(blackList.getBid());
        if (blackDao.addblacks(blackList)&&friendDao.offFriend(friends)){
            serviceResult = new ServiceResult(Status.SUCCESS,ADDBLACK_SUCCESS.message,null);
        }else {
            serviceResult = new ServiceResult(Status.ERROR,ADDBLACK_ERROR.message,null);
        }
        return serviceResult;
    }

    @Override
    public ServiceResult offblack(BlackList blackList) {
        ServiceResult serviceResult = null;
        Friends friends = new Friends();
        friends.setFid(blackList.getBid());
        friends.setUid(blackList.getUid());
        if (blackDao.deleteblack(blackList)&&friendDao.addfriend(friends)){
            serviceResult = new ServiceResult(Status.SUCCESS,OFFBLACK_SUCCESS.message,null);
        } else {
            serviceResult = new ServiceResult(Status.ERROR,OFFBLACK_ERROR.message,null);
        }
        return serviceResult;
    }
}
