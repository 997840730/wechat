package wangkaisheng.weChat.dao.impl;

import wangkaisheng.weChat.dao.FriendDao;
import wangkaisheng.weChat.po.Friends;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.util.Impl.BaseUtilesImpl;

import java.util.LinkedList;
import java.util.List;

import static wangkaisheng.weChat.util.BeanUtils.toList;

/**
 * @author Administrator
 */
public class FriendDaoImpl extends BaseUtilesImpl implements FriendDao {
    private List<Object> list = new LinkedList<>();
    @Override
    public List<Friends> getFriends(Friends friends) {
        list.clear();
        String sql = super.select(friends);
        toList(friends,list);
        return super.query(sql, list.toArray(), Friends.class);
    }
    @Override
    public boolean addfriend(Friends friends) {
        list.clear();
        String sql = super.insert(friends);
        toList(friends,list);
        return addAndcancelAndmodify(sql,list.toArray(),Friends.class);
    }
    @Override
    public boolean offFriend(Friends friends) {
        list.clear();
        String sql = super.delete(friends);
        toList(friends,list);
        return super.addAndcancelAndmodify(sql,list.toArray(),Friends.class);
    }

    @Override
    public List<User> searchFriend(User user) {
        list.clear();
        String sql = super.vague(user);
        toList(user,list);
        sql = sql.replace("?",(String)list.get(0));
        return super.query(sql,null,User.class);
    }
}
