package wangkaisheng.weChat.web.controller.provider;


import com.fasterxml.jackson.databind.ObjectMapper;
import wangkaisheng.weChat.dto.ServiceResult;
import wangkaisheng.weChat.service.contants.ServiceMessage;
import wangkaisheng.weChat.web.controller.contants.RequestMethod;
import wangkaisheng.weChat.web.controller.contants.Status;
import wangkaisheng.weChat.web.controller.contants.WebPage;
import wangkaisheng.weChat.po.Groups;
import wangkaisheng.weChat.po.Message;
import wangkaisheng.weChat.po.User;
import wangkaisheng.weChat.util.BeanUtils;
import wangkaisheng.weChat.web.controller.annotation.Action;
import wangkaisheng.weChat.web.controller.annotation.ActionProvider;


import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static wangkaisheng.weChat.service.contants.ServiceMessage.*;

/**
 * @author Administrator
 */
@ActionProvider(path = "/user")
public class UserProvider extends Provider{

    private final int AUTO_LOGIN_AGE = 60 * 60 * 24 *7;
    /**
     * @param req
     * @param resp
     * @description 用户注册信息的基本判断与传递
     * @throws ServletException
     * @throws IOException
     */
    @Action(method = RequestMethod.REGIST_DO)
    public void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        req.setCharacterEncoding("utf-8");
        String checkcodeServer =(String) session.getAttribute("CHECKCODE_SERVER");
        ServiceResult result;
        String verified = req.getParameter("verified");
        if(!verified.equalsIgnoreCase(checkcodeServer)){
            result = new ServiceResult(Status.ERROR,ServiceMessage.VERIFIED_ISERROR.message, null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.REGIST_JSP.toString()).forward(req,resp);
            return;
        }
        req.removeAttribute("verified");
        Map<String, String[]> parameterMap = req.getParameterMap();
        if (parameterMap.get("username")[0]==null||"".equals(parameterMap.get("username")[0])){
            result = new ServiceResult(Status.ERROR,USERNAME_ISEMPTY.message,null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.REGIST_JSP.toString()).forward(req,resp);
            return;
        }
        if (parameterMap.get("password")[0]==null||"".equals(parameterMap.get("password")[0])){
            result = new ServiceResult(Status.ERROR,PASSWORD_ISEMPTY.message,null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.REGIST_JSP.toString()).forward(req,resp);
            return;
        }
        if (parameterMap.get("password2")[0]==null||"".equals(parameterMap.get("password2")[0])){
            result = new ServiceResult(Status.ERROR,PASSWORD_ISEMPTY.message,null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.REGIST_JSP.toString()).forward(req,resp);
            return;
        }
        if (!parameterMap.get("password2")[0].equals(parameterMap.get("password")[0])) {
            result = new ServiceResult(Status.ERROR, PASSWORD_NOSAME.message,null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.REGIST_JSP.toString()).forward(req,resp);
            return;
        }
        if (parameterMap.get("name")[0]==null||"".equals(parameterMap.get("name")[0])){
            result = new ServiceResult(Status.ERROR,NAME_ISEMPTY.message,null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.REGIST_JSP.toString()).forward(req,resp);
            return;
        }
        if (parameterMap.get("phone")[0]==null||"".equals(parameterMap.get("phone")[0])){
            result = new ServiceResult(Status.ERROR,PHONE_ISEMPTY.message,null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.REGIST_JSP.toString()).forward(req,resp);
            return;
        }
        if (parameterMap.get("email")[0]==null||"".equals(parameterMap.get("email")[0])){
            result = new ServiceResult(Status.ERROR,EMAIL_ISEMPTY.message,null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.REGIST_JSP.toString()).forward(req,resp);
            return;
        }

        User user = (User) BeanUtils.toObject(parameterMap, User.class);
        ServiceResult serviceResult = userService.addUser(user);

        if(Status.ERROR.equals(serviceResult.getStatus())){
            req.setAttribute("message",serviceResult.getMessage());
            req.setAttribute("data",serviceResult.getData());
            req.getRequestDispatcher("/"+WebPage.REGIST_JSP.toString()).forward(req,resp);
        }else {
            req.setAttribute("message",serviceResult.getMessage());
            req.setAttribute("data",serviceResult.getData());
            req.getRequestDispatcher("/"+WebPage.LOGIN_JSP.toString()).forward(req,resp);
        }
    }


    /**
     * @param req
     * @param resp
     * @description 负责用户登录的基本信息验证与传递
     * @throws IOException
     * @throws ServletException
     */
    @Action(method = RequestMethod.LOGIN_DO)
    public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        String checkcodeServer =(String) session.getAttribute("CHECKCODE_SERVER");
        ServiceResult result;
        String verified = req.getParameter("verified");
        if(!verified.equalsIgnoreCase(checkcodeServer)){
            result = new ServiceResult(Status.ERROR,ServiceMessage.VERIFIED_ISERROR.message, null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.LOGIN_JSP.toString()).forward(req,resp);
            return;
        }
        req.removeAttribute("verified");
        Map<String, String[]> parameterMap = req.getParameterMap();
        if (parameterMap.get("username")[0]==null||"".equals(parameterMap.get("username")[0])){
            result = new ServiceResult(Status.ERROR,USERNAME_ISEMPTY.message,null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.LOGIN_JSP.toString()).forward(req,resp);
            return;
        }
        if (parameterMap.get("password")[0]==null||"".equals(parameterMap.get("password")[0])){
            result = new ServiceResult(Status.ERROR,PASSWORD_ISEMPTY.message,null);
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.LOGIN_JSP.toString()).forward(req,resp);
            return;
        }
        User user = (User) BeanUtils.toObject(parameterMap, User.class);
        result = userService.checkPassword(user);
        if (Status.ERROR.equals(result.getStatus())) {
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            req.getRequestDispatcher("/"+WebPage.LOGIN_JSP.toString()).forward(req,resp);
            } else {
            //校验密码成功时，给会话中添加用户信息
            user = (User) result.getData();
            req.setAttribute("message",result.getMessage());
            req.setAttribute("data",result.getData());
            //加载群聊列表
            List<Groups> groups = groupService.findGroups(user);
            req.setAttribute("groups",groups);
            //加载信息列表
            Map<String,List<Message>> messages = messageService.findMessages(user);
            req.setAttribute("messages",messages);
            //加载黑名单
            List<User> blacklist = blackService.findBlacklist(user);
            req.setAttribute("blacklist",blacklist);

            //判断是否记住密码
            if (parameterMap.get("auto_login")!=null){
                setAutoLoginCookie(resp,req,user);
            }
            //将用户信息放在服务器
            req.getSession().setAttribute("wechat",result.getData());
            req.getRequestDispatcher("/"+WebPage.HOME_JSP.toString()).forward(req,resp);
        }
    }

    /**
     * @param resp
     * @param req
     * @description 将用户账号与密码加载在客户端上，保持一周
     * @param user
     */
    private void setAutoLoginCookie(HttpServletResponse resp, HttpServletRequest req, User user) {
        Cookie cookie = new Cookie("user_username", String.valueOf(user.getUsername()));
        Cookie cookie1 = new Cookie("user_password",String.valueOf(user.getPassword()));
        cookie1.setMaxAge(AUTO_LOGIN_AGE);
        cookie1.setPath(req.getContextPath());
        cookie.setMaxAge(AUTO_LOGIN_AGE);
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);
        resp.addCookie(cookie1);
    }


    /**
     * @param req
     * @param resp
     * @description 用户修改资料的基本信息判断与传递
     * @throws IOException
     * @throws ServletException
     */
    @Action(method = RequestMethod.MODIFY_DO)
    public void modify(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException{
        HttpSession session = req.getSession(false);
        Map<String, String[]> parameterMap = req.getParameterMap();
        ServiceResult result;
        User user = null;
        User wechat =(User) req.getSession().getAttribute("wechat");
        String password = parameterMap.get("password")[0];
        String password2 = parameterMap.get("password2")[0];
        if (password==null||password2==null||"".equals(password)||"".equals(password2)){
            result = new ServiceResult(Status.ERROR,PASSWORD_ISEMPTY.message,wechat);
        }else if (!password.equals(password2)){
            result = new ServiceResult(Status.ERROR, PASSWORD_NOSAME.message,wechat);
        }else {
            user = (User) BeanUtils.toObject(parameterMap, User.class);
            user.setWechatId(wechat.getWechatId());
            user.setUsername(wechat.getUsername());
            if (user.getName()==null){
                user.setName(wechat.getName());
            }else {
                wechat.setName(user.getName());
            }

            if (user.getPassword()==null){
                user.setPassword(wechat.getPassword());
            }else {
                wechat.setPassword(user.getPassword());
            }

            if (user.getEmail()==null){
                user.setEmail(wechat.getEmail());
            }else {
                wechat.setEmail(user.getEmail());
            }
            if (user.getPhone()==null){
                user.setPhone(wechat.getPhone());
            }else {
                wechat.setPhone(user.getPhone());
            }
            result = userService.modifyUser(user);
        }
        req.setAttribute("message",result.getMessage());
        req.setAttribute("data",result.getData());
        req.getRequestDispatcher("/"+WebPage.HOME_JSP.toString()).forward(req,resp);
    }

}
