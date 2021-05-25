package wangkaisheng.weChat.dao.impl;

import wangkaisheng.weChat.dao.MessageDao;
import wangkaisheng.weChat.po.Message;
import wangkaisheng.weChat.po.Readed;
import wangkaisheng.weChat.util.Impl.BaseUtilesImpl;

import java.util.LinkedList;
import java.util.List;

import static wangkaisheng.weChat.util.BeanUtils.toList;

/**
 * @author Administrator
 */
public class MessageDaoImpl extends BaseUtilesImpl implements MessageDao {
    private List<Object> list = new LinkedList<>();
    @Override
    public boolean insertMessage(Message mess) {
        list.clear();
        String sql = super.insert(mess);
        toList(mess,list);
        return super.addAndcancelAndmodify(sql, list.toArray(), Message.class);
    }

    @Override
    public List<Message> getMessages(Message message) {
        list.clear();
        String sql = super.select(message);
        toList(message,list);
        return super.query(sql,list.toArray(),Message.class);
    }

    @Override
    public void insertRead(Readed read) {
        list.clear();
        String sql = super.insert(read);
        toList(read,list);
        super.addAndcancelAndmodify(sql,list.toArray(), Readed.class);
    }

    @Override
    public List<Message> findfriendMessages(Message message) {
        list.clear();
        String sql = super.select(message);
        toList(message,list);
        return query(sql,list.toArray(),Message.class);
    }

    @Override
    public void updateMessage(Message m) {
        list.clear();
        String sql = super.update(m);
        toList(m,list);
        addAndcancelAndmodify(sql,list.toArray(),Message.class);
    }

    @Override
    public Message findmessage(Message mess) {
        list.clear();
        String sql = super.select(mess);
        toList(mess,list);
        return query(sql,list.toArray(),Message.class).get(0);
    }

    @Override
    public List<Readed> findread(Readed readed) {
        list.clear();
        String sql = super.select(readed);
        toList(readed,list);
        return query(sql,list.toArray(),Readed.class);
    }

    @Override
    public Message findmessageByread(Message message) {
        list.clear();
        String sql = super.select(message);
        toList(message,list);
        return query(sql,list.toArray(),Message.class).get(0);
    }

    @Override
    public void deleteReaded(Readed readed) {
        list.clear();
        String sql = super.delete(readed);
        toList(readed,list);
        System.out.println(sql);
        System.out.println(list);
        addAndcancelAndmodify(sql,list.toArray(),Readed.class);
    }
}
