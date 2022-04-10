package controller.admin;

import dao.RoleDAO;
import dao.UserDAO;
import entity.Roles;
import entity.Users;
import org.apache.commons.beanutils.BeanUtils;
import utils.EncryptUtil;
import utils.UploadUtil;

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
@MultipartConfig
public class UserManagerController extends HttpServlet {

    private UserDAO userDAO;
    private RoleDAO roleDAO;

    public UserManagerController() {
        this.userDAO = new UserDAO();
        this.roleDAO = new RoleDAO();
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

        List<Users> listUser = userDAO.findAll();
        int count = userDAO.countUser();

        request.setAttribute("listUser", listUser);
        request.setAttribute("count", count);
        request.setAttribute("viewAdmin", "/views/admin/user/home.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Roles> listRole = roleDAO.findAll();

        request.setAttribute("viewAdmin", "/views/admin/user/create.jsp");
        request.setAttribute("listRole", listRole);
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void store(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String password = request.getParameter("password");
        String passwordEncrypted = EncryptUtil.encrypt(password);
        try {
            Users user = new Users();

            user.setAvatar(UploadUtil.uploadImage("avatar", request));
            BeanUtils.populate(user, request.getParameterMap());
            user.setPassword(passwordEncrypted);
            userDAO.create(user);
            response.sendRedirect("/Assignment_Java4/admin/user/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/user/create");
        }
    }

    protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            userDAO.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        response.sendRedirect("/Assignment_Java4/admin/user/list");
    }

    protected void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        Users user = userDAO.findById(id);
        List<Roles> listRole = roleDAO.findAll();

        request.setAttribute("listRole", listRole);
        request.setAttribute("user", user);
        request.setAttribute("viewAdmin", "/views/admin/user/edit.jsp");
        request.getRequestDispatcher("/views/admin/index.jsp").forward(request, response);
    }

    protected void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            Users user = userDAO.findById(id);

            BeanUtils.populate(user, request.getParameterMap());
            user.setCreatedAt(user.getCreatedAt());
            user.setPassword(user.getPassword());

            userDAO.update(user);
            response.sendRedirect("/Assignment_Java4/admin/user/list");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/admin/user/edit?id=" + id);
        }

    }
}
