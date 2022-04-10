package controller.admin;

import dao.SizeDAO;
import entity.Size;
import entity.Size;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/admin/size/list",
        "/admin/size/create",
        "/admin/size/store",
        "/admin/size/edit",
        "/admin/size/update",
        "/admin/size/delete"
})
public class SizeManagerController extends HttpServlet {

    private SizeDAO sizeDAO;

    public SizeManagerController() {
        this.sizeDAO = new SizeDAO();
    }

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

        List<Size> listSize = sizeDAO.findALl();
        int count = sizeDAO.countSize();

        request.setAttribute("count", count);
        request.setAttribute("listSize", listSize);
        request.setAttribute("viewAdmin", "/views/admin/attribute/size/home.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewAdmin", "/views/admin/attribute/size/create.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String sizeName = request.getParameter("size-name");
        try {
            Size size = new Size();
            size.setSizeName(sizeName);
            sizeDAO.create(size);

            response.sendRedirect("/Assignment_Java4/admin/size/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/size/create");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            sizeDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Java4/admin/size/list");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Size size = sizeDAO.findById(id);

        request.setAttribute("size", size);
        request.setAttribute("viewAdmin", "/views/admin/attribute/size/edit.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String sizeName = request.getParameter("size-name");
        try {

            Size size = sizeDAO.findById(id);
            size.setCreatedAt(size.getCreatedAt());
            size.setSizeName(sizeName);
            sizeDAO.update(size);

            response.sendRedirect("/Assignment_Java4/admin/size/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/size/edit?id=" + id);
        }
    }
}
