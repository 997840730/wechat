package wangkaisheng.weChat.service.impl;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Member;
import wangkaisheng.weChat.po.Message;
import wangkaisheng.weChat.po.Readed;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.MessageService;
import wangkaisheng.weChat.service.Service;
import wangkaisheng.weChat.service.contants.ServiceMessage;
import wangkaisheng.weChat.web.controller.contants.Status;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MessageServiceImpl extends Service implements MessageService {
    @Override
    public Map<String, List<Message>> findMessages(User user) {
        List<Message> temp;
        Map<String,List<Message>> map = new LinkedHashMap<>();
        Message message = new Message();
        message.setReaded(0);
        message.setSenderId(user.getWechatId());
        temp =  messageDao.getMessages(message);
        if (!temp.isEmpty()){
            map.put("sprivate",temp);
        }
        message.setSenderId(null);
        message.setChatId(user.getWechatId());
        temp.clear();
        temp = messageDao.getMessages(message);
        if (!temp.isEmpty()){
            map.put("cprivate",temp);
        }
        message.setReaded(1);
        Member member = new Member();
        member.setUid(user.getWechatId());
        List<Member> members = memberDao.getMember(member);
        temp.clear();
        for (Member m:members){
            message.setChatId(m.getGid());
            temp.addAll(messageDao.getMessages(message));
        }
        if (!temp.isEmpty()){
            map.put("cpublic",temp);
        }
        temp.clear();
        message.setChatId(null);
        message.setSenderId(user.getWechatId());
        temp = messageDao.getMessages(message);
        if (!temp.isEmpty()){
            map.put("spublic",temp);
        }
        return map;
    }

    @Override
    public ServiceResult findfriendMessages(Message message) {
        List<Message> list =  messageDao.findfriendMessages(message);
        if (!list.isEmpty()){
            return new ServiceResult(Status.SUCCESS, ServiceMessage.FRIEND_MESSAGE_SUCCESS.message,list);
        }else {
            return new ServiceResult(Status.SUCCESS, ServiceMessage.FRIEND_MESSAGE_ERROR.message,null);
        }
    }

    @Override
    public void updataMessage(List<Message> data) {
        for (Message m:data){
            m.setReaded(1);
            System.out.println(m);
            messageDao.updateMessage(m);
        }
    }

    @Override
    public List<Readed> findread(Readed readed) {
        return messageDao.findread(readed);
    }

    @Override
    public ServiceResult findgroupMessage(List<Readed> readeds) {
        List<Message> list = new LinkedList<>();
        for (Readed readed: readeds){
            Message message = new Message();
            message.setId(readed.getMid());
            list.add(messageDao.findmessageByread(message));
        }
        if (list.isEmpty()){
            return new ServiceResult(Status.ERROR,ServiceMessage.FINDMESSAGE_READ_ERROR.message,null);
        }else {
            return new ServiceResult(Status.ERROR,ServiceMessage.FINDMESSAGE_READ_SUCCESS.message,list);
        }
    }

    @Override
    public void deleteRead(List<Readed> readeds) {
        Readed temp = new Readed();
        for (Readed readed:readeds){
            temp.setId(readed.getId());
            messageDao.deleteReaded(temp);
        }
    }
}
