package controller.admin;

import dao.ColorDAO;
import entity.Color;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/admin/color/list",
        "/admin/color/create",
        "/admin/color/store",
        "/admin/color/edit",
        "/admin/color/update",
        "/admin/color/delete"
})
public class ColorManagerController extends HttpServlet {

    private ColorDAO colorDAO;

    public ColorManagerController() {
        this.colorDAO = new ColorDAO();
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

        List<Color> listColor = colorDAO.findALl();
        int count = colorDAO.countColor();

        request.setAttribute("count", count);
        request.setAttribute("listColor", listColor);
        request.setAttribute("viewAdmin", "/views/admin/attribute/color/home.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewAdmin", "/views/admin/attribute/color/create.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String colorName = request.getParameter("color-name");
        try {
            Color color = new Color();
            color.setColorName(colorName);
            colorDAO.create(color);

            response.sendRedirect("/Assignment_Java4/admin/color/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/color/create");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            colorDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Java4/admin/color/list");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Color color = colorDAO.findById(id);

        request.setAttribute("color", color);
        request.setAttribute("viewAdmin", "/views/admin/attribute/color/edit.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String colorName = request.getParameter("color-name");
        try {

            Color color = colorDAO.findById(id);
            color.setCreatedAt(color.getCreatedAt());
            color.setColorName(colorName);
            colorDAO.update(color);

            response.sendRedirect("/Assignment_Java4/admin/color/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/color/edit?id=" + id);
        }
    }
}
