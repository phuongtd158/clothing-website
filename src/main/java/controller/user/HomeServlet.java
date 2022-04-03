package controller.user;

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
        String uri = request.getRequestURI();

        if (uri.contains("home")) {
            doGetHome(request, response);
        } else if (uri.contains("product")) {
            doGetProduct(request, response);
        } else if (uri.contains("detail")) {
            doGetProductDetail(request, response);
        } else if (uri.contains("about")) {
            doGetAboutUs(request, response);
        } else if (uri.contains("contact")) {
            doGetContact(request, response);
        } else if (uri.contains("shopping-cart")) {
            doGetShoppingCart(request, response);
        } else if (uri.contains("login")) {
            doGetLogin(request, response);
            return;
        }
        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    protected void doGetHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Trang chủ");
        request.setAttribute("views", "/views/user/home.jsp");
    }

    protected void doGetProductDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Chi tiết sản phẩm");
        request.setAttribute("views", "/views/user/product-detail.jsp");
    }

    protected void doGetProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Sản phẩm");
        request.setAttribute("views", "/views/user/product.jsp");
    }

    protected void doGetContact(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Liên hệ");
        request.setAttribute("views", "/views/user/contact.jsp");
    }

    protected void doGetAboutUs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Giới thiệu");
        request.setAttribute("views", "/views/user/about.jsp");
    }

    protected void doGetShoppingCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Giỏ hàng");
        request.setAttribute("views", "/views/user/shopping-cart.jsp");
    }

    protected void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Đăng nhập");
        request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
    }
}
