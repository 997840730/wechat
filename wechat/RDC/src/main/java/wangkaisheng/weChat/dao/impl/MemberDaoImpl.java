package wangkaisheng.weChat.dao.impl;

import wangkaisheng.weChat.dao.MemberDao;
import wangkaisheng.weChat.po.Member;
import wangkaisheng.weChat.util.Impl.BaseUtilesImpl;

import java.util.LinkedList;
import java.util.List;

import static wangkaisheng.weChat.util.BeanUtils.toList;

public class MemberDaoImpl extends BaseUtilesImpl implements MemberDao {
    private List<Object> list = new LinkedList<>();
    @Override
    public List<Member> getMember(Member member) {
        list.clear();
        toList(member,list);
        String sql = super.select(member);
        return  super.query(sql, list.toArray(), Member.class);
    }
}
