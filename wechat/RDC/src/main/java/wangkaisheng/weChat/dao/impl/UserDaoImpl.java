package wangkaisheng.weChat.dao.impl;

import wangkaisheng.weChat.dao.UserDao;
import wangkaisheng.weChat.po.*;
import wangkaisheng.weChat.util.Impl.BaseUtilesImpl;

import java.util.LinkedList;
import java.util.List;

import static wangkaisheng.weChat.util.BeanUtils.toList;

/**
 * @author Administrator
 */
public class UserDaoImpl extends BaseUtilesImpl implements UserDao {
    private List<Object> list = new LinkedList<>();
    @Override
    public User getUserByUsername(User user) {
        list.clear();
        toList(user,list);
        String sql =super.select(user);
        List<User> query = super.query(sql, list.toArray(), User.class);
        if(query.isEmpty()){
            return null;
        }else {
            return query.get(0);
        }
    }

    @Override
    public boolean insrtUser(User user) {
        list.clear();
        toList(user, list);
        String sql =super.insert(user);
        return super.addAndcancelAndmodify(sql,list.toArray(), User.class);
    }

    @Override
    public User getUsersById(Friends friends) {
        list.clear();
        User user = new User();
        user.setWechatId(friends.getFid());
        String sql = super.select(user);
        toList(user,list);
        List<User> query = super.query(sql,list.toArray(),User.class);
        if(query.isEmpty()){
            return null;
        }else {
            return query.get(0);
        }
    }



    @Override
    public boolean modify(User user) {
        list.clear();
        String sql = super.update(user);
        toList(user,list);
        return super.addAndcancelAndmodify(sql,list.toArray(),User.class);
    }

    @Override
    public boolean addfriend(Friends friends) {
        list.clear();
        String sql = super.insert(friends);
        toList(friends,list);
        return super.addAndcancelAndmodify(sql,list.toArray(),Friends.class);
    }

}
