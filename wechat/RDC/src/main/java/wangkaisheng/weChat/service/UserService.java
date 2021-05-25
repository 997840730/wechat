package wangkaisheng.weChat.service;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.BlackList;
import wangkaisheng.weChat.po.Groups;
import wangkaisheng.weChat.po.Message;
import wangkaisheng.weChat.po.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    ServiceResult checkPassword(User user);

    ServiceResult addUser(User user);

    ServiceResult modifyUser(User user);

}
