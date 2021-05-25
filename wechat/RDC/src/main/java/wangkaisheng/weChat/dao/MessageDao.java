package wangkaisheng.weChat.dao;

import wangkaisheng.weChat.po.Message;
import wangkaisheng.weChat.po.Readed;

import java.util.List;

public interface MessageDao {
    boolean insertMessage(Message mess);

    List<Message> getMessages(Message message);

    void insertRead(Readed read);

    List<Message> findfriendMessages(Message message);

    void updateMessage(Message m);

    Message findmessage(Message mess);

    List<Readed> findread(Readed readed);

    Message findmessageByread(Message message);

    void deleteReaded(Readed readed);
}
