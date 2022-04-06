package controller.admin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({
        "/admin/attribute/list",
        "/admin/attribute/create",
        "/admin/attribute/store",
        "/admin/attribute/edit",
        "/admin/attribute/update",
        "/admin/attribute/delete"
})
public class AttributeManagerController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();

        if (uri.contains("list")) {
            this.index(request, response);
        } else if (uri.contains("create")) {
            this.create(request, response);
        } else if (uri.contains("edit")) {
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
        request.setAttribute("viewAdmin", "/views/admin/attribute/home.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewAdmin", "/views/admin/attribute/create.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("viewAdmin", "/views/admin/attribute/edit.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }
}
