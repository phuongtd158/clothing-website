package controller.user;

import dao.*;
import entity.*;
import utils.CookieUtil;
import utils.JpaUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {
        "/home",
        "/product",
        "/shopping-cart",
        "/about",
        "/contact",
        "/detail",
        "/search",
        "/filter"
})
public class HomeController extends HttpServlet {

    private ProductDAO productDAO;
    private ColorDAO colorDAO;
    private SizeDAO sizeDAO;
    private ProductSizeDAO productSizeDAO;
    private ProductColorDAO productColorDAO;
    private CategoryDAO categoryDAO;

    public HomeController() {
        this.productDAO = new ProductDAO();
        this.colorDAO = new ColorDAO();
        this.sizeDAO = new SizeDAO();
        this.productSizeDAO = new ProductSizeDAO();
        this.productColorDAO = new ProductColorDAO();
        this.categoryDAO = new CategoryDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        String action = request.getParameter("action");
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
        } else if (uri.contains("search")) {
            doGetSearchProduct(request, response);
        } else if (uri.contains("filter")) {
            doGetFilterProduct(request, response);
        }

        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }


    protected void doGetHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> listProduct = productDAO.findALl();
        HttpSession session = request.getSession();


        request.setAttribute("listProduct", listProduct);
        request.setAttribute("title", "Trang chủ");
        request.setAttribute("views", "/views/user/home.jsp");
    }

    protected void doGetProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("search-product");

        List<Product> listProduct = productDAO.findALl();
        List<Categories> listCategories = categoryDAO.findAll();

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("listCategories", listCategories);
        request.setAttribute("title", "Sản phẩm");
        request.setAttribute("views", "/views/user/product.jsp");
    }

    protected void doGetSearchProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("search-product");
        List<Product> listProduct = productDAO.findByName(name);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("title", "Sản phẩm");
        request.setAttribute("views", "/views/user/product.jsp");
    }

    protected void doGetFilterProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("cateId"));
        List<Categories> listCategories = categoryDAO.findAll();

        System.out.println(id);
        List<Product> listProduct = productDAO.findByCategoryId(id);
        listProduct.forEach(p -> {
            System.out.println(p.toString());
        });
        request.setAttribute("listCategories", listCategories);
        request.setAttribute("listProduct", listProduct);
        request.setAttribute("title", "Sản phẩm");
        request.setAttribute("views", "/views/user/product.jsp");
    }

    protected void doGetProductDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int productId = Integer.parseInt(request.getParameter("id"));
            StringBuilder builderColor = new StringBuilder();
            StringBuilder builderSize = new StringBuilder();

            Product product = productDAO.findById(productId);
            List<ProductSize> listSizeByProductId = productSizeDAO.findSizesByProductId(productId);
            List<ProductColor> listColorByProductId = productColorDAO.findColorsByProductId(productId);

            if (listColorByProductId != null) {
                listColorByProductId.forEach(color -> {
                    builderColor.append(color.getColorByColorId().getColorName()).append(", ");
                });
            }
            if (listSizeByProductId != null) {
                listSizeByProductId.forEach(size -> {
                    builderSize.append(size.getSizeBySizeId().getSizeName()).append(", ");
                });
            }

            request.setAttribute("productDetail", product);
            request.setAttribute("builderColor", builderColor.length() > 0
                    ? builderColor.substring(0, builderColor.length() - 2) : "");
            request.setAttribute("builderSize", builderSize.length() > 0
                    ? builderSize.substring(0, builderSize.length() - 2) : "");
            request.setAttribute("sizeByProductId", listSizeByProductId);
            request.setAttribute("colorByProductId", listColorByProductId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.setAttribute("title", "Chi tiết sản phẩm");
        request.setAttribute("views", "/views/user/product-detail.jsp");
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
//        HttpSession session = request.getSession();
//        HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
//
//        session.setAttribute("cart", cart);
        request.setAttribute("title", "Giỏ hàng");
        request.setAttribute("views", "/views/user/shopping-cart.jsp");
    }

    protected void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Đăng nhập");
        request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
    }


}
