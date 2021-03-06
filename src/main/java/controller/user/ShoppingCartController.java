package controller.user;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.UserDAO;
import entity.*;
import utils.CookieUtil;
import utils.ValidateUtil;

import javax.persistence.criteria.Order;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet({
        "/addtocart",
        "/viewcart",
        "/checkout",
        "/history",
        "/order-detail",
        "/delete-all-item",
        "/delete-item",
        "/update-item"

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
        } else if (uri.contains("history")) {
            history(request, response);
        } else if (uri.contains("order-detail")) {
            historyDetail(request, response);
        } else if (uri.contains("delete-all-item")) {
            deleteAllCartItems(request, response);
        } else if (uri.contains("delete-item")) {
            deleteOneItem(request, response);
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
        } else if (uri.contains("update-item")) {
            updateItem(request, response);
        }
    }

    public void addToCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String productName = request.getParameter("productName");
        int productId = Integer.parseInt(request.getParameter("productId"));
        int productColor = Integer.parseInt(request.getParameter("color"));
        int productSize = Integer.parseInt(request.getParameter("size"));
        int numOfProduct = Integer.parseInt(request.getParameter("numProduct"));
        double totalPrice = 0;
        int count = 0;
        try {

            if (productColor == 0 || productSize == 0) {
                session.setAttribute("errorMess", "Ph???i ch???n ?????y ????? m??u s???c v?? k??ch th?????c");
                response.sendRedirect("/Assignment_Java4/detail?id=" + productId);
                return;
            }

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
                count++;
            }

            session.setAttribute("cart", cart);
            session.setAttribute("count", count);
            session.setAttribute("numOfProduct", numOfProduct);
            session.setAttribute("totalPrice", totalPrice);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("views", "/views/user/product-detail.jsp");
        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    public void updateItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        int key = Integer.parseInt(request.getParameter("key"));
        Cart productCart;
        double totalPrice = 0;
        try {
            HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
            if (cart.containsKey(key)) {
                productCart = cart.get(key);
                if (quantity < 0) {
                    session.setAttribute("errorQuantityMess", "S??? l?????ng ph???i l???n h??n 0");
                    response.sendRedirect("/Assignment_Java4/shopping-cart");
                    return;
                }
                if (quantity == 0) {
                    cart.remove(key);
                }
                productCart.setQuantity(quantity);
            }
            for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
                totalPrice = totalPrice + entry.getValue().getQuantity() * entry.getValue().getProduct().getPrice();
            }

            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("successMess", "C???p nh???t s??? l?????ng th??nh c??ng");
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Java4/shopping-cart");
    }

    public void deleteAllCartItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        try {
            HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
            if (cart != null) {
                cart.clear();
                session.removeAttribute("cart");
                session.removeAttribute("totalPrice");
                session.removeAttribute("numOfProduct");
                session.removeAttribute("count");
            }
            request.setAttribute("views", "/views/user/shopping-cart.jsp");
            request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteOneItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int key = Integer.parseInt(request.getParameter("key"));
        try {
            HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
            for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
                if (entry.getKey() == key) {
                    cart.remove(key);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("views", "/views/user/shopping-cart.jsp");
        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    public void viewCart(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession();
        HashMap<Integer, Cart> cart = (HashMap<Integer, Cart>) session.getAttribute("cart");
        for (Map.Entry<Integer, Cart> entry : cart.entrySet()) {
            System.out.println("Name: " + entry.getValue().getProduct().getProductName() + "  " +
                    "Quantity:" + entry.getValue().getQuantity());
        }
        session.setAttribute("cart", cart);
    }

    public void checkOut(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        double totalMoney = (Double) session.getAttribute("totalPrice");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("phoneNumber");
        String fullName = request.getParameter("fullName");
        try {

            if (ValidateUtil.checkTrong(address, phoneNumber, fullName)) {
                session.setAttribute("errorMess", "Kh??ng ???????c ????? tr???ng th??ng tin");
                response.sendRedirect("/Assignment_Java4/shopping-cart");
                return;
            }
            if (ValidateUtil.checkPhoneNumber(phoneNumber)) {
                session.setAttribute("errorMess", "S??? ??i???n tho???i kh??ng ????ng ?????nh d???ng");
                response.sendRedirect("/Assignment_Java4/shopping-cart");
                return;
            }

            Users user = (Users) session.getAttribute("user");
            Orders order = new Orders();

            order.setUsersByUserId(user);
            order.setFullname(fullName);
            order.setPhoneNumber(phoneNumber);
            order.setAddress(address);
            order.setEmail(user.getEmail());
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
            session.removeAttribute("totalPrice");
            session.removeAttribute("numOfProduct");
            session.removeAttribute("count");
            request.setAttribute("views", "/views/user/checkout.jsp");
            request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void history(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("user");

        List<Orders> listOrder = orderDAO.findALlByUserId(user.getId());

        request.setAttribute("listOrder", listOrder);
        request.setAttribute("views", "/views/user/history.jsp");
        request.setAttribute("title", "L???ch s??? mua h??ng");
        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    public void historyDetail(HttpServletRequest request, HttpServletResponse response) throws
            ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("orderId"));
        List<OrderDetails> listOrderDetails = orderDetailDAO.findByOrderId(id);

        request.setAttribute("listOrderDetails", listOrderDetails);
        request.setAttribute("views", "/views/user/history-detail.jsp");
        request.setAttribute("title", "Chi ti???t ????n h??ng");
        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }
}
