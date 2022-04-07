package filter;

import entity.Users;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {
//        "/admin/*",
//        "/admin/user/*",
//        "/admin/product/*",
//        "/admin/category/*",
})
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpSession session = httpRequest.getSession();
//        HttpServletResponse httpRespone = (HttpServletResponse) response;
//        Users user = (Users) session.getAttribute("user");
//        if (user == null) {
//            httpRespone.sendRedirect("/Assignment_Java4/login");
//            return;
//        }
//        chain.doFilter(request, response);
    }
}
