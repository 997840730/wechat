package wangkaisheng.weChat.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Administrator
 */
@WebFilter(filterName = "LoginFilter")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig config) throws ServletException {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        if (uri.contains("login.jsp")||uri.contains("/checkCodeServlet")){
            chain.doFilter(request, response);
        }else {
            req.setAttribute("message","请登录后在进入页面！");
            req.getRequestDispatcher("/index.jsp").forward(request,response);
        }
    }
}
