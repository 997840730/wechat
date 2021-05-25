package wangkaisheng.weChat.service.impl;

import org.junit.jupiter.api.Test;
import wangkaisheng.weChat.dao.impl.MessageDaoImpl;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Message;
import wangkaisheng.weChat.po.Readed;
import wangkaisheng.weChat.service.MessageService;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MessageServiceImplTest extends MessageServiceImpl {

    @Test
    void findfriendMessages() {
        Message message = new Message();
        message.setReaded(0);
        message.setType("private");
        message.setSenderId(1);
        message.setChatId(3);
    }

    @Test
    void updataMessage() {
        Message message = new Message();
        message.setReaded(0);
        message.setType("private");
        message.setSenderId(1);
        message.setChatId(3);
        ServiceResult serviceResult = super.findfriendMessages(message);
        List<Message> data = (List<Message>) serviceResult.getData();
        super.updataMessage(data);
    }

    @Test
    void testFindread() {
        Readed readed =new Readed();
        readed.setUid(3);
        readed.setGid(1);
        List<Readed> findread = super.findread(readed);
        ServiceResult result = super.findgroupMessage(findread);
        System.out.println(result);
    }

    @Test
    void testFindgroupMessage() {
    }

    @Test
    void testDeleteRead() {
        List<Readed>list = new LinkedList<>();
        Readed readed = new Readed();
        readed.setGid(1);
        readed.setUid(3);
        readed.setMid(35);
        readed.setId(9);
        list.add(readed);
        super.deleteRead(list);
    }
}