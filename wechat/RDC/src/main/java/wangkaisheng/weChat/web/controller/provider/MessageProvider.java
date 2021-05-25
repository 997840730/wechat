package wangkaisheng.weChat.web.controller.provider;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Message;
import wangkaisheng.weChat.po.Readed;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.web.controller.annotation.Action;
import wangkaisheng.weChat.web.controller.annotation.ActionProvider;
import wangkaisheng.weChat.web.controller.contants.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static wangkaisheng.weChat.util.JSONutil.responseByJson;

/**
 * @author Administrator
 */
@ActionProvider(path = "/message")
public class MessageProvider extends Provider {
    @Action(method = RequestMethod.FRIENDMESSAGE_DO)
    public void friendmessage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("wechat");
        String id = req.getParameter("id");
        Message message = new Message();
        message.setReaded(0);
        message.setType("private");
        message.setSenderId(Integer.parseInt(id));
        message.setChatId(user.getWechatId());
        ServiceResult result =  messageService.findfriendMessages(message);
        List<Message> data = (List<Message>) result.getData();
        if (data!=null&&!data.isEmpty()){
            messageService.updataMessage(data);
        }
        responseByJson(resp,result);
    }

    @Action(method = RequestMethod.GROUPSMESSAGE_DO)
    public void groupsmessage(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String id = req.getParameter("id");
        User user = (User) req.getSession().getAttribute("wechat");
        Readed readed = new Readed();
        readed.setGid(Integer.valueOf(id));
        readed.setUid(user.getWechatId());
        List<Readed> readeds = messageService.findread(readed);
        System.out.println(readeds);
        ServiceResult result = messageService.findgroupMessage(readeds);
        messageService.deleteRead(readeds);
        responseByJson(resp,result);
    }
}
