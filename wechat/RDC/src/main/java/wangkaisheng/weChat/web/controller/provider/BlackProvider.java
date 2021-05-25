package wangkaisheng.weChat.web.controller.provider;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.po.BlackList;
import wangkaisheng.weChat.po.Groups;
import wangkaisheng.weChat.po.Message;
import wangkaisheng.weChat.po.User;
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

import static wangkaisheng.weChat.util.JSONutil.responseByJson;

/**
 * @author Administrator
 */
@ActionProvider(path = "/black")
public class BlackProvider extends Provider {

    @Action(method = RequestMethod.LISTBLACK_DO)
    public void listblack(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        User user =(User) req.getSession().getAttribute("wechat");
        List<User> blacklist = blackService.findBlacklist(user);
        responseByJson(resp,blacklist);
    }

    /**
     * @param req
     * @param resp
     * @description 将好友加入黑名单
     * @throws IOException
     * @throws ServletException
     */
    @Action(method = RequestMethod.ADDBLACK_DO)
    public void addblack (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String blackid = req.getParameter("blackid");
        resp.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<String,Object>();
        ServiceResult result;
        BlackList blackList = new BlackList();
        User user =(User) req.getSession().getAttribute("wechat");
        blackList.setUid(user.getWechatId());
        blackList.setBid(Integer.valueOf(blackid));
        result = blackService.addblack(blackList);
        if (Status.ERROR.equals(result.getStatus())){
            map.put("isorno",false);
        }else {
            map.put("isorno",true);
        }
        List<User> friend =  friendService.findFriends(user);
        req.setAttribute("friends",friend);
        List<Groups> groups = groupService.findGroups(user);
        req.setAttribute("groups",groups);
        Map<String,List<Message>> messages = messageService.findMessages(user);
        req.setAttribute("messages",messages);
        List<User> blacklist = blackService.findBlacklist(user);
        req.setAttribute("blacklist",blacklist);
        //将map转为json，并且传递给客户端
        //将map转为json
        ObjectMapper mapper = new ObjectMapper();
        //并且传递给客户端
        mapper.writeValue(resp.getWriter(),map);
        req.getRequestDispatcher("/"+ WebPage.HOME_JSP.toString()).forward(req,resp);
    }


    /**
     * @param req
     * @param resp
     * @description 将好友拉出黑名单
     * @throws IOException
     * @throws ServletException
     */
    @Action(method = RequestMethod.OFFBLACK_DO)
    public void offblack (HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        String friendid = req.getParameter("friendid");
        resp.setContentType("application/json;charset=utf-8");
        Map<String,Object> map = new HashMap<String,Object>();
        ServiceResult result;
        BlackList blackList = new BlackList();
        User user =(User) req.getSession().getAttribute("wechat");
        blackList.setUid(user.getWechatId());
        blackList.setBid(Integer.valueOf(friendid));
        result = blackService.offblack(blackList);

        if (Status.ERROR.equals(result.getStatus())){
            map.put("isorno",false);
        }else {
            map.put("isorno",true);
        }
        List<User> friend =  friendService.findFriends(user);
        req.setAttribute("friends",friend);
        List<Groups> groups = groupService.findGroups(user);
        req.setAttribute("groups",groups);
        Map<String,List<Message>> messages = messageService.findMessages(user);
        req.setAttribute("messages",messages);
        List<User> blacklist = blackService.findBlacklist(user);
        req.setAttribute("blacklist",blacklist);
        //将map转为json，并且传递给客户端
        //将map转为json
        ObjectMapper mapper = new ObjectMapper();
        //并且传递给客户端
        mapper.writeValue(resp.getWriter(),map);
        req.getRequestDispatcher("/"+WebPage.HOME_JSP.toString()).forward(req,resp);
    }
}
