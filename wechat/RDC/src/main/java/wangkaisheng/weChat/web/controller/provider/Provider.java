package wangkaisheng.weChat.web.controller.provider;


import wangkaisheng.weChat.factory.ProxyFactory;
import wangkaisheng.weChat.service.*;
import wangkaisheng.weChat.service.impl.*;
import wangkaisheng.weChat.web.controller.contants.RequestMethod;
import wangkaisheng.weChat.web.controller.contants.WebPage;
import wangkaisheng.weChat.util.ControllerUtils;
import wangkaisheng.weChat.web.controller.annotation.Action;
import wangkaisheng.weChat.web.controller.annotation.ActionProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;


/**
 * @author Administrator
 */
public class Provider {

    protected static final UserService userService = (UserService) new ProxyFactory().getProxyInstance(new UserServiceImpl());
    protected static final FriendService friendService = (FriendService) new ProxyFactory().getProxyInstance(new FriendServiceImpl());
    protected static final GroupService groupService = (GroupService) new ProxyFactory().getProxyInstance(new GroupServiceImpl());
    protected static final MessageService messageService = (MessageService) new ProxyFactory().getProxyInstance(new MessageServiceImpl());
    protected static final BlackService blackService = (BlackService) new ProxyFactory().getProxyInstance(new BlackServiceImpl());
    protected static final NoticeService noticeService = (NoticeService) new ProxyFactory().getProxyInstance(new NoticeServiceImpl());
    protected static final MomentService momentService = (MomentService) new ProxyFactory().getProxyInstance(new MomentServiceImpl());


    public void doAction(HttpServletRequest req, HttpServletResponse resp){
        try {
            System.out.println(req.getParameter("method"));
            RequestMethod requestMethod = ControllerUtils.valueOf(req.getParameter("method"));
            //根据方法上的注解找到对应的Action方法并执行
            System.out.println(requestMethod.toString());
            if (RequestMethod.INVALID_REQUEST.equals(requestMethod)){
                toErrorPage("无效的访问链接，系统无法识别您的请求指向的服务内容!",req,resp);
                return;
            }

            Method[] methods = this.getClass().getMethods();
            for (Method m : methods) {
                Action action = m.getAnnotation(Action.class);
                if (action != null && action.method().equals(requestMethod)) {
                    m.invoke(this, req, resp);
                    return;
                }
            }
        } catch (ExceptionInInitializerError | Exception e) {
            e.printStackTrace();
        }
    }

    public static void toErrorPage(String message, HttpServletRequest req, HttpServletResponse resp) {
        req.setAttribute("message", message);
        try {
            req.getRequestDispatcher(WebPage.ERROR_JSP.toString()).forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    public String getPath() {
        return this.getClass().getAnnotation(ActionProvider.class).path();
    }
}
