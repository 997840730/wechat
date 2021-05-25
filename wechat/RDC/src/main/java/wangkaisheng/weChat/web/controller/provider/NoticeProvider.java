package wangkaisheng.weChat.web.controller.provider;

import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.Notice;
import wangkaisheng.weChat.po.Noticed;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.po.contant.Type;
import wangkaisheng.weChat.web.controller.annotation.Action;
import wangkaisheng.weChat.web.controller.annotation.ActionProvider;
import wangkaisheng.weChat.web.controller.contants.RequestMethod;
import wangkaisheng.weChat.web.controller.contants.Status;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static wangkaisheng.weChat.util.JSONutil.responseByJson;

/**
 * @author Administrator
 */
@ActionProvider(path = "/notice")
public class NoticeProvider extends Provider {

    @Action(method = RequestMethod.ADDFRIENDNOTICE_DO)
    public void addfriendnotice(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("wechat");
        Notice notice = new Notice();
        notice.setSendid(Integer.parseInt(req.getParameter("sendid")));
        notice.setUserid(Integer.parseInt(req.getParameter("userid")));
        notice.setName(user.getName());
        notice.setType(0);
        notice.setGid(Integer.parseInt(req.getParameter("userid")));
        ServiceResult result = noticeService.insertnotice(notice);
        responseByJson(resp, result);
    }

    @Action(method = RequestMethod.ADDGROUPNOTICE_DO)
    public void addgroupnotice(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("wechat");
        Notice notice = new Notice();
        notice.setSendid(Integer.parseInt(req.getParameter("sendid")));
        notice.setUserid(Integer.parseInt(req.getParameter("userid")));
        notice.setName(user.getName());
        notice.setType(1);
        ServiceResult result = noticeService.insertnotice(notice);
        responseByJson(resp, result);
    }

    @Action(method = RequestMethod.NOTICELIST_DO)
    public void noticelist(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("wechat");
        ServiceResult result = noticeService.findnotice(user);
        responseByJson(resp, result);
    }

    @Action(method = RequestMethod.NOTICEDLIST_DO)
    public void noticedlist(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = (User) req.getSession().getAttribute("wechat");
        ServiceResult result = noticeService.findnoticed(user);
        responseByJson(resp, result);
    }

    @Action(method = RequestMethod.OFFNOTICED_DO)
    public void offnoticed(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String noticedid = req.getParameter("noticedid");
        Noticed noticed = new Noticed();
        noticed.setId(Integer.valueOf(noticedid));
        ServiceResult result = noticeService.offnoticed(noticed);
        responseByJson(resp, result);
    }
}