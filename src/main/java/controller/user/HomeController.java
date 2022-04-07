package controller.user;

import dao.*;
import entity.Product;
import entity.ProductColor;
import entity.ProductSize;
import utils.CookieUtil;
import utils.JpaUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {
        "/home",
        "/product",
        "/shopping-cart",
        "/about",
        "/contact",
        "/detail"
})
public class HomeController extends HttpServlet {

    private ProductDAO productDAO;
    private ColorDAO colorDAO;
    private SizeDAO sizeDAO;
    private ProductSizeDAO productSizeDAO;
    private ProductColorDAO productColorDAO;

    public HomeController() {
        this.productDAO = new ProductDAO();
        this.colorDAO = new ColorDAO();
        this.sizeDAO = new SizeDAO();
        this.productSizeDAO = new ProductSizeDAO();
        this.productColorDAO = new ProductColorDAO();
    }

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
        }
        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }


    protected void doGetHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Product> listProduct = productDAO.findALl();
        HttpSession session = request.getSession();
        String cookie = CookieUtil.readCookie("cookie", request);
        System.out.println(cookie);
        if (cookie != null && !cookie.equals("")) {
            session.setAttribute("user", cookie);
        }

        request.setAttribute("listProduct", listProduct);
        request.setAttribute("title", "Trang chủ");
        request.setAttribute("views", "/views/user/home.jsp");
    }

    protected void doGetProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        List<Product> listProduct = productDAO.findALl();

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
        request.setAttribute("title", "Giỏ hàng");
        request.setAttribute("views", "/views/user/shopping-cart.jsp");
    }

    protected void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Đăng nhập");
        request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
    }


}
