package mk.ukim.finki.lab.web.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.lab.model.User;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@WebFilter
@Profile("Servlet")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        User user=(User)request.getSession().getAttribute("user");

        String path=request.getServletPath();

        if(user==null&&!path.equals("/register")&&!path.equals("/login")&&!path.equals("/main.css")){
            response.sendRedirect("/login");
        }
        else{
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
