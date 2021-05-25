package wangkaisheng.weChat.service;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.BlackList;
import wangkaisheng.weChat.po.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface BlackService {
    List<User> findBlacklist(User user);

    ServiceResult addblack(BlackList blackList);

    ServiceResult offblack(BlackList blackList);
}
