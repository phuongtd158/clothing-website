package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static Cookie addCookie(String name, String value, int hours, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(hours);
        cookie.setPath("/");
        response.addCookie(cookie);
        return cookie;
    }

    public static String readCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    System.out.println("cookie.getValue() = " + cookie.getValue());
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

}
