package wangkaisheng.weChat.dao;

import wangkaisheng.weChat.po.Groups;
import wangkaisheng.weChat.po.Member;

import java.util.List;

public interface GroupDao {
    Groups getGroups(Member member);

    List<Groups> searchGroup(Groups groups);

    boolean insertGroup(Groups groups);

    boolean findGroup(Groups groups);

    Groups findGroupByid(Groups groups);

    boolean insertMember(Member member);
}
