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
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = JpaUtil.getEntityManager();
        String uri = request.getRequestURI();

        if (uri.contains("home")) {
            request.setAttribute("title", "Trang chủ");
            request.setAttribute("views", "/views/user/home.jsp");
        } else if (uri.contains("product")) {
//            String jpql = "select  u from ProductDetails u group by u.productId";
//            TypedQuery<ProductDetails> query = entityManager.createQuery(jpql, ProductDetails.class);
//            List<ProductDetails> list = query.getResultList();
//            request.setAttribute("products", list);
            request.setAttribute("title", "Sản phẩm");
            request.setAttribute("views", "/views/user/product.jsp");
        } else if (uri.contains("detail")) {
//            int id = Integer.parseInt(request.getParameter("productId"));
//
//            String jpql = "select  u from ProductDetails u where u.productId = :id group by u.productId";
//            String jpql1 = "select  u from ProductDetails u where u.productId = :id ";
//
//            TypedQuery<ProductDetails> query = entityManager.createQuery(jpql, ProductDetails.class);
//            TypedQuery<ProductDetails> query1 = entityManager.createQuery(jpql1, ProductDetails.class);
//
//            query.setParameter("id", id);
//
//            ProductDetails productDetail = query.getSingleResult();
//
//            query1.setParameter("id", id);
//            List<ProductDetails> list = query1.getResultList();
//
//            System.out.println("productDetail.toString() = " + productDetail.toString());
//            request.setAttribute("productDetail", productDetail);
//            request.setAttribute("list", list);
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
