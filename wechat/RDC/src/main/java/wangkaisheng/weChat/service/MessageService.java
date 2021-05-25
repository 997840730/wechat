package wangkaisheng.weChat.service;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Message;
import wangkaisheng.weChat.po.Readed;
import wangkaisheng.weChat.po.User;

import java.util.List;
import java.util.Map;

public interface MessageService {
    Map<String, List<Message>> findMessages(User user);

    ServiceResult findfriendMessages(Message message);

    void updataMessage(List<Message> data);

    List<Readed> findread(Readed readed);

    ServiceResult findgroupMessage(List<Readed> readeds);

    void deleteRead(List<Readed> readeds);
}
