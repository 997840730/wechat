package wangkaisheng.weChat.dao;

import wangkaisheng.weChat.po.Member;

import java.util.List;

public interface MemberDao {
    List<Member> getMember(Member member);
}
