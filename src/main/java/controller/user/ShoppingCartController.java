package controller.user;

import dao.ProductDAO;
import entity.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet({
        "/addtocart"
})
public class ShoppingCartController extends HttpServlet {

    ProductDAO productDAO;

    public ShoppingCartController() {
        productDAO = new ProductDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        switch (action) {
            case "add":
                addToCart(request, response);
            case "view":
                viewCart(request, response);
                break;
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

        Product product = productDAO.findById(productId);

        session.setAttribute("product", product);
        session.setAttribute("numOfProduct", numOfProduct);
        session.setAttribute("totalPrice", product.getPrice() * numOfProduct);
        System.out.println(productName + "\n" + productId + "\n" + productColor + "\n" + productSize + "\n" + numOfProduct);

        request.setAttribute("views", "/views/user/product-detail.jsp");
    }

    public void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
