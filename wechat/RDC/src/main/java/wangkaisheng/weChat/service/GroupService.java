package wangkaisheng.weChat.service;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Groups;
import wangkaisheng.weChat.po.Member;
import wangkaisheng.weChat.po.User;

import java.util.List;

/**
 * @author Administrator
 */
public interface GroupService {

    List<Groups> findGroups(User user);

    ServiceResult searchGroup(String parameter,User user);

    ServiceResult creatdGroup(Groups groups);

    ServiceResult insertGroup(Member member);
}
