package controller.admin;

import entity.Users;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/admin/user/list",
        "/admin/user/create",
        "/admin/user/store",
        "/admin/user/edit",
        "/admin/user/update",
        "/admin/user/delete"
})
public class UserManagerController extends HttpServlet {
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
        request.setAttribute("viewAdmin", "/views/admin/user/home.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewAdmin", "/views/admin/user/create.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("viewAdmin", "/views/admin/user/edit.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        response.sendRedirect("/PH13747_TranDucPhuong_Lab5_300322/index");
    }
}
