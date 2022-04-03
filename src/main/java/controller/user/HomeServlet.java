package controller;

import utils.JpaUtil;

import javax.persistence.EntityManager;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = {
        "/home",
        "/product",
        "/shopping-cart",
        "/about",
        "/contact",
        "/detail",
        "/login"
})
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = JpaUtil.getEntityManager();
        String uri = request.getRequestURI();

        if (uri.contains("home")) {
            request.setAttribute("title", "Trang chủ");
            request.setAttribute("views", "/views/user/home.jsp");
        } else if (uri.contains("product")) {
            request.setAttribute("title", "Sản phẩm");
            request.setAttribute("views", "/views/user/product.jsp");
        } else if (uri.contains("detail")) {
            request.setAttribute("title", "Chi tiết sản phẩm");
            request.setAttribute("views", "/views/user/product-detail.jsp");
        } else if (uri.contains("about")) {
            request.setAttribute("title", "Giới thiệu");
            request.setAttribute("views", "/views/user/about.jsp");
        } else if (uri.contains("contact")) {
            request.setAttribute("title", "Liên hệ");
            request.setAttribute("views", "/views/user/contact.jsp");
        } else if (uri.contains("shopping-cart")) {
            request.setAttribute("title", "Giỏ hàng");
            request.setAttribute("views", "/views/user/shopping-cart.jsp");
        } else if (uri.contains("login")) {
            request.setAttribute("title", "Đăng nhập");
            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
            return;
        }
        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
    }
}
