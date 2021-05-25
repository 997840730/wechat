package wangkaisheng.weChat.dao.impl;

import wangkaisheng.weChat.dao.GroupDao;
import wangkaisheng.weChat.po.Groups;
import wangkaisheng.weChat.po.Member;
import wangkaisheng.weChat.util.Impl.BaseUtilesImpl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.StreamSupport;

import static wangkaisheng.weChat.util.BeanUtils.toList;

/**
 * @author Administrator
 */
public class GroupDaoImpl extends BaseUtilesImpl implements GroupDao {
    private List<Object> list = new LinkedList<>();
    @Override
    public Groups getGroups(Member member) {
        Groups group = new Groups();
        list.clear();
        group.setGroupId(member.getGid());
        String sql = super.select(group);
        toList(group,list);
        return  super.query(sql,list.toArray(),Groups.class).get(0);
    }

    @Override
    public List<Groups> searchGroup(Groups groups) {
        list.clear();
        toList(groups,list);
        String sql = super.vague(groups);
        sql = sql.replace("?",(String)list.get(0));
        return  super.query(sql,null,Groups.class);
    }

    @Override
    public boolean insertGroup(Groups groups) {
        list.clear();
        toList(groups,list);
        String sql = super.insert(groups);
        Date date = new Date();
        list.add(new java.sql.Date(date.getTime()));
        return super.addAndcancelAndmodify(sql,list.toArray(),Groups.class);
    }

    @Override
    public boolean findGroup(Groups groups) {
        list.clear();
        groups.setDate(null);
        toList(groups,list);
        String sql = super.select(groups);
        return !query(sql,list.toArray(),Groups.class).isEmpty();
    }

    @Override
    public Groups findGroupByid(Groups groups) {
        list.clear();
        String sql = super.select(groups);
        toList(groups,list);
        return query(sql,list.toArray(),Groups.class).get(0);
    }

    @Override
    public boolean insertMember(Member member) {
        list.clear();
        String sql = super.insert(member);
        toList(member,list);
        return addAndcancelAndmodify(sql,list.toArray(),Member.class);
    }
}
