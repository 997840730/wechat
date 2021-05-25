package wangkaisheng.weChat.web.servlet;

import wangkaisheng.weChat.web.controller.provider.Provider;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static wangkaisheng.weChat.web.controller.provider.Provider.toErrorPage;

/**
 * @author Administrator
 * @description
 */
@WebServlet("/wechat/*")
public class weChatServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        Map<String, Provider> providerMap = (Map<String, Provider>) getServletContext().getAttribute("providerMap");
        String url = request.getRequestURI();
        Set<String> keys = providerMap.keySet();
        for (String key : keys) {
            String path = providerMap.get(key).getPath();
            if (url.substring(14).equalsIgnoreCase(path)) {
                providerMap.get(key).doAction(request, response);
                return;
            }
        }
    }
}
