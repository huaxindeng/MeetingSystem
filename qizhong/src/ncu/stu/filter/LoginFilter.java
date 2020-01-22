package ncu.stu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
     /*
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String uri = req.getRequestURI();
        //要注意排除掉css/js/图片/验证码等资源
        if(uri.contains("login.jsp")||uri.contains("addUser.jsp")||uri.contains("/util/")||uri.contains("/cs/")||uri.contains("/js/")){
            filterChain.doFilter(servletRequest,servletResponse);
        } else{
                Object user = req.getSession().getAttribute("user");
                Object admin = req.getSession().getAttribute("admin");
                if(user!=null||admin!=null){
                    filterChain.doFilter(servletRequest,servletResponse);
                }
                else{
                    req.setAttribute("login_msg","您尚未登陆，请登录");
                    req.getRequestDispatcher("/login.jsp").forward(req,servletResponse);
                }
            }
       */
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
