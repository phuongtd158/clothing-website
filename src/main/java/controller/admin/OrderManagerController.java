package controller.admin;

import dao.CategoryDAO;
import dao.OrderDAO;
import dao.OrderDetailDAO;
import entity.Categories;
import entity.OrderDetails;
import entity.Orders;
import org.apache.commons.beanutils.BeanUtils;
import utils.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/admin/order/list",
        "/admin/order/update",
        "/admin/order/detail"
})
public class OrderManagerController extends HttpServlet {

    private OrderDAO orderDAO;
    private OrderDetailDAO orderDetailDAO;

    public OrderManagerController() {
        this.orderDAO = new OrderDAO();
        this.orderDetailDAO = new OrderDetailDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("list")) {
            this.index(request, response);
        } else if (uri.contains("detail")) {
            this.detail(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Orders> listOrder = orderDAO.findALl();
        //int count = orderDAO.countCategory();

        // request.setAttribute("count", count);
        request.setAttribute("listOrder", listOrder);
        request.setAttribute("viewAdmin", "/views/admin/order/home.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("orderId"));
        try {
            List<OrderDetails> listOrderDetails = orderDetailDAO.findByOrderId(id);

            request.setAttribute("listOrderDetails", listOrderDetails);
            request.setAttribute("viewAdmin", "/views/admin/order/order-detail.jsp");
            request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("orderId"));
        int status = Integer.parseInt(request.getParameter("status"));
        try {
            Orders order = orderDAO.findById(id);
            order.setStatus(status);
            orderDAO.update(order);
            response.sendRedirect("/Assignment_Java4/admin/order/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/order/list");
        }
    }
}
