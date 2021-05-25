package wangkaisheng.weChat.service.impl;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.service.Service;
import wangkaisheng.weChat.web.controller.contants.Status;
import wangkaisheng.weChat.po.*;
import wangkaisheng.weChat.service.UserService;


import static wangkaisheng.weChat.service.contants.ServiceMessage.*;

/**
 * @author Administrator
 */
public class UserServiceImpl extends Service implements UserService {

    @Override
    public ServiceResult checkPassword(User user) {
        if(user==null){
            return new ServiceResult(Status.ERROR, PARAMETER_NOT_ENOUGHT.message,null);
        }
        User realUser = new User();
        realUser.setUsername(user.getUsername());
        try {
            realUser = userDao.getUserByUsername(realUser);
            //检查账号是否存在
            if (realUser == null) {
                return new ServiceResult(Status.ERROR, ACCOUNT_NOT_FOUND.message, user);
            }
            //检查密码是否正确
            if (user.getPassword() == null || !realUser.getPassword().equals(user.getPassword())) {
                return new ServiceResult(Status.ERROR, PASSWORD_INCORRECT.message, user);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new ServiceResult(Status.ERROR, DATABASE_ERROR.message, user);
        }
        return new ServiceResult(Status.SUCCESS, LOGIN_SUCCESS.message, realUser);
    }


    @Override
    public ServiceResult addUser(User user) {
        User userByUsername = new User();
        userByUsername.setUsername(user.getUsername());
        userByUsername = userDao.getUserByUsername(userByUsername);
        if (userByUsername!=null){
            return new ServiceResult(Status.ERROR,USERNAME_EMPTY.message,null);
        }else {
            userDao.insrtUser(user);
        }
        return new ServiceResult(Status.SUCCESS, REGIST_SUCCESS.message, user);
    }

    @Override
    public ServiceResult modifyUser(User user) {
        if (userDao.modify(user)){
            return new ServiceResult(Status.SUCCESS,USERMODIFY_SUCCESS.message,user);
        }else {
            return new ServiceResult(Status.ERROR,USERMODIFY_ERROR.message,null);
        }
    }

}
