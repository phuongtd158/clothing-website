package controller.user;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entity.*;
import utils.CookieUtil;

import javax.persistence.criteria.Order;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@WebServlet({
        "/addtocart",
        "/viewcart",
        "/checkout"

})
public class ShoppingCartController extends HttpServlet {

    ProductDAO productDAO;
    OrderDAO orderDAO;
    OrderDetailDAO orderDetailDAO;
    UserDAO userDAO;


    public ShoppingCartController() {
        this.productDAO = new ProductDAO();
        this.orderDAO = new OrderDAO();
        this.orderDetailDAO = new OrderDetailDAO();
        this.userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("viewcart")) {
            System.out.println(1);
            viewCart(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String uri = request.getRequestURI();
        if (action != null) {
            if (action.contains("add")) {
                addToCart(request, response);
            }
        }
        if (uri.contains("checkout")) {
            checkOut(request, response);
        }

        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productName = request.getParameter("productName");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int productColor = Integer.parseInt(request.getParameter("color"));
        int productSize = Integer.parseInt(request.getParameter("size"));
        int numOfProduct = Integer.parseInt(request.getParameter("numProduct"));
        double totalPrice = 0;
        try {
            Product product = productDAO.findById(productId);
            Cart productCart;

            HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
            if (cart == null) {
                cart = new HashMap<Integer, Cart>();
                productCart = new Cart(numOfProduct, product);

                cart.put(productId, productCart);
            } else {
                if (cart.containsKey(productId)) {
                    productCart = cart.get(productId);
                    productCart.setQuantity(productCart.getQuantity() + numOfProduct);
                } else {
                    productCart = new Cart(numOfProduct, product);
                    cart.put(productId, productCart);
                }
            }
            for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
                totalPrice = totalPrice + entry.getValue().getQuantity() * entry.getValue().getProduct().getPrice();
            }

            session.setAttribute("cart", cart);
            session.setAttribute("numOfProduct", numOfProduct);
            session.setAttribute("totalPrice", totalPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("views", "/views/user/product-detail.jsp");
    }

    public void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
        for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
            System.out.println("Name: " + entry.getValue().getProduct().getProductName() + "  " +
                    "Quantity:" + entry.getValue().getQuantity());
        }
        session.setAttribute("cart", cart);
    }

    public void checkOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        double totalMoney = (Double) session.getAttribute("totalPrice");
        try {

            Users user = (Users) session.getAttribute("user");
            System.out.println(user.toString());

            Orders order = new Orders();
            order.setUsersByUserId(user);
            order.setFullname(user.getFullName());
            order.setPhoneNumber(user.getPhoneNumber());
            order.setEmail(user.getEmail());
            order.setAddress(user.getAddress());
            order.setStatus(0);
            order.setTotalMoney(totalMoney);
            orderDAO.create(order);

            HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
            for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
                OrderDetails orderDetails = new OrderDetails();
                Product product = productDAO.findById(entry.getValue().getProduct().getId());
                orderDetails.setOrdersByOrderId(order);
                orderDetails.setProductByProductId(product);
                orderDetails.setQuantity(entry.getValue().getQuantity());
                orderDetails.setUnitPrice(entry.getValue().getQuantity() * entry.getValue().getProduct().getPrice());
                orderDetailDAO.create(orderDetails);

                product.setQuantity(product.getQuantity() - entry.getValue().getQuantity());
                productDAO.update(product);
            }

            session.removeAttribute("cart");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
