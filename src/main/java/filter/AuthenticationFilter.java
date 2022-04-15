package filter;

import entity.Cart;
import entity.Users;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

@WebFilter(urlPatterns = {
//        "/admin/*",
//        "/admin/user/*",
//        "/admin/product/*",
//        "/admin/category/*",
//        "/history",
//        "/order-detail"
})
public class AuthenticationFilter implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
//        HttpServletRequest httpRequest = (HttpServletRequest) request;
//        HttpServletResponse httpResponse = (HttpServletResponse) response;
//        HttpSession session = httpRequest.getSession();
//        Users user = (Users) session.getAttribute("user");
//        HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
//        if (user == null) {
//            httpResponse.sendRedirect("/Assignment_Java4/login");
//            return;
//        }
        chain.doFilter(request, response);
    }
}
