package wangkaisheng.weChat.web.listener;

import wangkaisheng.weChat.web.controller.provider.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.util.concurrent.ConcurrentHashMap;

@WebListener
public class ServletListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private static final ConcurrentHashMap<String, Provider> providerMap = new ConcurrentHashMap<>();

    public ServletListener() {
    }


    @Override
    public void contextInitialized(ServletContextEvent sce) {
        /**
         * 用来初始化provider的容器
         */
        //注册服务
        providerMap.put("userProvider", new UserProvider());
        providerMap.put("friendProvider",new FriendProvider());
        providerMap.put("blackProvider",new BlackProvider());
        providerMap.put("messageProvider",new MessageProvider());
        providerMap.put("momentProvider",new MomentProvider());
        providerMap.put("groupProvider",new GroupProvider());
        providerMap.put("noticeProvider",new NoticeProvider());
        ServletContext sc = sce.getServletContext();
        sc.setAttribute("providerMap", providerMap);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    @Override
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is added to a session. */
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is removed from a session. */
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent sbe) {
        /* This method is called when an attribute is replaced in a session. */
    }
}
