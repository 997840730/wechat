package wangkaisheng.weChat.web.controller.provider;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.*;
import wangkaisheng.weChat.util.BeanUtils;
import wangkaisheng.weChat.web.controller.annotation.Action;
import wangkaisheng.weChat.web.controller.annotation.ActionProvider;
import wangkaisheng.weChat.web.controller.contants.RequestMethod;
import wangkaisheng.weChat.web.controller.contants.Status;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import static wangkaisheng.weChat.util.JSONutil.responseByJson;

/**
 * @author Administrator
 */
@ActionProvider(path = "/group")
public class GroupProvider extends Provider{
    @Action(method = RequestMethod.LISTGROUP_DO)
    public void listgroup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        List<Groups> groups = groupService.findGroups((User) req.getSession().getAttribute("wechat"));
        responseByJson(resp,groups);
    }


    @Action(method = RequestMethod.SEARCHGROUP_DO)
    public void searchgroup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        ServiceResult result =groupService.searchGroup(req.getParameter("search"), (User) req.getSession().getAttribute("wechat"));
        responseByJson(resp, result);
    }

    @Action(method = RequestMethod.CREATEGROUP_DO)
    public void creategroup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        Groups groups = new Groups();
        groups.setGroupUser(Integer.parseInt(req.getParameter("groupUser")));
        groups.setGroupname(req.getParameter("groupname"));
        groups.setIntroduce(req.getParameter("introduce"));
        ServiceResult result =groupService.creatdGroup(groups);
        responseByJson(resp,result);
    }


    @Action(method = RequestMethod.ADDGROUP_DO)
    public void addgroup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        User user = (User) req.getSession().getAttribute("wechat");
        String noticeid = req.getParameter("id");
        String sendid = req.getParameter("sendid");
        String name = req.getParameter("name");
        String userid = req.getParameter("userid");
        String groupname = req.getParameter("username");

        Noticed noticed = new Noticed();
        noticed.setUserid(Integer.parseInt(sendid));
        noticed.setSendid(user.getWechatId());
        noticed.setName(name);
        noticed.setContant("对方已同意您加入群聊！");
        noticed.setGroupname(groupname);


        Notice notice = new Notice();
        notice.setId(Integer.valueOf(noticeid));
        Member member = new Member();
        member.setUid(Integer.valueOf(sendid));
        member.setGid(Integer.valueOf(userid));
        ServiceResult result = groupService.insertGroup(member);
        ServiceResult result1 = noticeService.insertnoticed(noticed);
        ServiceResult result2 = noticeService.deleteNoticeById(notice);
        responseByJson(resp,result);
    }

    @Action(method = RequestMethod.OFFGROUP_DO)
    public void offgroup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        User user = (User) req.getSession().getAttribute("wechat");
        String noticeid = req.getParameter("id");
        String sendid = req.getParameter("sendid");
        String name = req.getParameter("name");
        String groupname = req.getParameter("username");

        Noticed noticed = new Noticed();
        noticed.setUserid(Integer.parseInt(sendid));
        noticed.setSendid(user.getWechatId());
        noticed.setName(name);
        noticed.setContant("对方已拒绝您加入群聊！");
        noticed.setGroupname(groupname);

        Notice notice = new Notice();
        notice.setId(Integer.valueOf(noticeid));
        ServiceResult result1 = noticeService.insertnoticed(noticed);
        ServiceResult result2 = noticeService.deleteNoticeById(notice);
        responseByJson(resp,new ServiceResult(Status.SUCCESS,"拒绝成功！",null));
    }
}
