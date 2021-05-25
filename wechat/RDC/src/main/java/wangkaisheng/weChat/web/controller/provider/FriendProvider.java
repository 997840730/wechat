package wangkaisheng.weChat.web.controller.provider;

import com.alibaba.fastjson.JSON;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.factory.ProxyFactory;
import wangkaisheng.weChat.po.Friends;
import wangkaisheng.weChat.po.Notice;
import wangkaisheng.weChat.po.Noticed;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.service.UserService;
import wangkaisheng.weChat.service.impl.UserServiceImpl;
import wangkaisheng.weChat.web.controller.annotation.Action;
import wangkaisheng.weChat.web.controller.annotation.ActionProvider;
import wangkaisheng.weChat.web.controller.contants.RequestMethod;
import wangkaisheng.weChat.web.controller.contants.Status;
import wangkaisheng.weChat.web.controller.contants.WebPage;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import static wangkaisheng.weChat.service.contants.ServiceMessage.OFFFRIEND_SUCCESS;
import static wangkaisheng.weChat.service.contants.ServiceMessage.PASSWORD_ISEMPTY;
import static wangkaisheng.weChat.util.JSONutil.responseByJson;

/**
 * @author Administrator
 */
@ActionProvider(path = "/friend")
public class FriendProvider extends Provider {

    @Action(method = RequestMethod.LISTFRIEND_DO)
    public void listfriend(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        responseByJson(resp,friendService.findFriends((User) req.getSession().getAttribute("wechat")));
    }

    @Action(method = RequestMethod.SEARCHFRIEND_DO)
    public void searchfriend(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        User user = (User) req.getSession().getAttribute("wechat");
        ServiceResult result = friendService.searchFriend(req.getParameter("search"),user);
        System.out.println(result);
        responseByJson(resp, result);
    }


    @Action(method = RequestMethod.ADDFRIEND_DO)
    public void addfriend(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        User user = (User) req.getSession().getAttribute("wechat");
        String sendid = req.getParameter("sendid");
        String userid = req.getParameter("userid");
        Friends friends = new Friends();
        friends.setUid(Integer.parseInt(sendid));
        friends.setFid(Integer.parseInt(userid));
        Noticed noticed = new Noticed();
        noticed.setSendid(Integer.parseInt(sendid));
        noticed.setName(user.getName());
        noticed.setUserid(Integer.parseInt(userid));
        noticed.setContant("对方已同意添加您为好友！");
        noticed.setGroupname("null");
        String noticeid = req.getParameter("noticeid");
        Notice notice = new Notice();
        notice.setId(Integer.valueOf(noticeid));
        System.out.println(noticed);
        ServiceResult result2 = noticeService.deleteNoticeById(notice);
        ServiceResult result1 = noticeService.insertnoticed(noticed);
        ServiceResult result = friendService.addfriend(friends);
        responseByJson(resp,result);
    }


    @Action(method = RequestMethod.OFFFRIEND_DO)
    public void offfriend(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        User user = (User) req.getSession().getAttribute("wechat");
        String sendid = req.getParameter("sendid");
        String userid = req.getParameter("userid");
        Noticed noticed = new Noticed();
        noticed.setSendid(Integer.parseInt(sendid));
        noticed.setName(user.getName());
        noticed.setUserid(Integer.parseInt(userid));
        noticed.setContant("对方已拒绝添加您为好友！");
        noticed.setGroupname("null");
        String noticeid = req.getParameter("noticeid");
        Notice notice = new Notice();
        notice.setId(Integer.valueOf(noticeid));
        ServiceResult result2 = noticeService.deleteNoticeById(notice);
        ServiceResult result1 = noticeService.insertnoticed(noticed);
        responseByJson(resp,new ServiceResult(Status.SUCCESS, OFFFRIEND_SUCCESS.message,null));
    }
}
