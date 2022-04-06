package controller.user;

import dao.UserDAO;
import entity.Users;
import utils.CookieUtil;
import utils.EncryptUtil;

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
    boolean checkValid = false;

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
        } else if (uri.contains("logout")) {
            doPostLogout(request, response);
        }
    }

    public void doGetLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Đăng nhập");
        request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
    }

    public void doGetSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Đăng nhập");
        request.getRequestDispatcher("/views/user/signup.jsp").forward(request, response);
    }

    public void doGetLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        CookieUtil.addCookie("cookie", null, 0, response);
        response.sendRedirect("/Assignment_Java4/home");
    }

    public void doPostLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = request.getParameter("email-login");
            String password = request.getParameter("password-login");
            boolean remember = "true".equals(request.getParameter("remember"));
            String regex = "(\\S.*\\S)(@)(\\S.*\\S)(.\\S[a-z]{2,3})";
            HttpSession session = request.getSession();

            Users user = userDAO.findByEmail(email);
            boolean check = EncryptUtil.check(password, user.getPassword());
            System.out.println(user.toString());
            if (!check) {
                session.setAttribute("errorMess", "Sai tên tài khoản hoặc mật khẩu");
                request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
            } else {
                session.setAttribute("user", user);
                if (remember) {
                    CookieUtil.addCookie("cookie", email.split("@")[0], 2, response);
                } else {
                    CookieUtil.addCookie("cookie", null, 0, response);
                }
                if (user.getRolesByRoleId().getName().equals("admin")) {
                    session.setAttribute("successMess", "Đăng nhập thành công");
                    response.sendRedirect("/Assignment_Java4/admin/home");
                } else {
                    session.setAttribute("successMess", "Đăng nhập thành công");
                    response.sendRedirect("/Assignment_Java4/home");
                }
            }

//            if (ValidateUtil.checkTrong(email, password)) {
//                checkValid = true;
//            } else if (!email.matches(regex)) {
//
//            } else if (user != null) {
//
//            }
//
//            if (checkValid == true) {
//                System.out.println(1);
//            }


        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("/Assignment_Java4/login");
        }
    }

    public void doPostSignUp(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("title", "Đăng nhập");
        request.getRequestDispatcher("/views/user/signup.jsp").forward(request, response);
    }

    public void doPostLogout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
