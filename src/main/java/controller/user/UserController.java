package controller.user;

import dao.UserDAO;
import entity.Users;
import utils.CookieUtil;
import utils.EncryptUtil;
import utils.ValidateUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet({
        "/login",
        "/logout",
        "/signup"
})
public class UserController extends HttpServlet {

    private UserDAO userDAO;

    public UserController() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        if (uri.contains("login")) {
            doGetLogin(request, response);
        } else if (uri.contains("signup")) {
            doGetSignUp(request, response);
        } else if (uri.contains("logout")) {
            doGetLogout(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();
        if (uri.contains("login")) {
            doPostLogin(request, response);
        } else if (uri.contains("signup")) {
            doPostSignUp(request, response);
        }
    }

    public void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
    }

    public void doGetSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Đăng nhập");
        request.getRequestDispatcher("/views/user/signup.jsp").forward(request, response);
    }

    public void doGetLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if (session != null) {
            session.removeAttribute("user");
            response.sendRedirect("/Assignment_Java4/home");
        }
    }

    public void doPostLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String email = request.getParameter("email-login");
        String password = request.getParameter("password-login");
        try {
            Users user = userDAO.findByEmail(email);
            boolean check = EncryptUtil.check(password, user.getPassword());

            if (ValidateUtil.checkTrong(email, password)) {
                session.setAttribute("errorMess", "Không được để trống khi đăng nhập");
                response.sendRedirect("/Assignment_Java4/login");
                return;
            }
            if (ValidateUtil.checkEmail(email)) {
                session.setAttribute("errorMess", "Email sai định dạng");
                response.sendRedirect("/Assignment_Java4/login");
                return;
            }

            if (!check) {
                session.setAttribute("errorMess", "Sai tên tài khoản hoặc mật khẩu");
                request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
            } else {
                session.setAttribute("user", user);
                if (user.getRoleId() == 1) {
                    session.setAttribute("successMess", "Đăng nhập thành công");
                    response.sendRedirect("/Assignment_Java4/admin/home");
                } else {
                    session.setAttribute("successMess", "Đăng nhập thành công");
                    response.sendRedirect("/Assignment_Java4/home");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/login");
        }
    }

    public void doPostSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Đăng nhập");
        request.getRequestDispatcher("/views/user/signup.jsp").forward(request, response);
    }

}
