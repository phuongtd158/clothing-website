package controller.admin;

import dao.CategoryDAO;
import entity.Categories;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "/admin/category/list",
        "/admin/category/create",
        "/admin/category/store",
        "/admin/category/edit",
        "/admin/category/update",
        "/admin/category/delete"
})
public class CategoryManagerController extends HttpServlet {

    private CategoryDAO categoryDAO;

    public CategoryManagerController() {
        this.categoryDAO = new CategoryDAO();
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
        List<Categories> listCategories = categoryDAO.findAll();
        int count = categoryDAO.countCategory();

        request.setAttribute("count", count);
        request.setAttribute("listCategories", listCategories);
        request.setAttribute("viewAdmin", "/views/admin/category/home.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("viewAdmin", "/views/admin/category/create.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            String categoryName = request.getParameter("category-name");
            Categories category = new Categories();
            category.setName(categoryName);

            categoryDAO.create(category);
            session.setAttribute("successMess", "Thêm thành công");
            response.sendRedirect("/Assignment_Java4/admin/category/list");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMess", "Thêm thất bại");
            response.sendRedirect("/Assignment_Java4/admin/category/create");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            categoryDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Java4/admin/category/list");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Categories category = categoryDAO.findById(id);

            request.setAttribute("category", category);
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("viewAdmin", "/views/admin/category/edit.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String categoryName = request.getParameter("category-name");
        Categories category = categoryDAO.findById(id);
        try {
            category.setCreatedAt(category.getCreatedAt());
            category.setName(categoryName);

            categoryDAO.update(category);
            session.setAttribute("successMess", "Cập nhật thành công");
            response.sendRedirect("/Assignment_Java4/admin/category/list");
        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("errorMess", "Cập nhật thất bại");
            response.sendRedirect("/Assignment_Java4/admin/category/edit?id=" + id);
        }
    }
}
