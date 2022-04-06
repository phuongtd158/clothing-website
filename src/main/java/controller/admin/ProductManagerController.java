package controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({
        "/admin/product/list",
        "/admin/product/create",
        "/admin/product/store",
        "/admin/product/edit",
        "/admin/product/update",
        "/admin/product/delete"
})
public class ProductManagerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        System.out.println("uri = " + uri);
        if (uri.contains("list")) {
            this.index(request, response);
        } else if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
            System.out.println("edit");
            this.edit(request, response);
        } else if (uri.contains("delete")) {
            this.delete(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        if (uri.contains("store")) {
            this.store(request, response);
        } else if (uri.contains("update")) {
            this.update(request, response);
        }
    }

    protected void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewAdmin", "/views/admin/product/home.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewAdmin", "/views/admin/product/create.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("viewAdmin", "/views/admin/product/edit.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }
}
