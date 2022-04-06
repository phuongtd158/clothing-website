package utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {

    public static String readCookie(String name, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    return cookie.getValue();
                }
            }
        }
        return "";
    }

    public static Cookie addCookie(String name, String value, int hours, HttpServletResponse response) {
        Cookie cookie = new Cookie(name, value);

        cookie.setMaxAge(hours * 60 * 60);
        cookie.setPath("/");

        response.addCookie(cookie);

        return cookie;
    }

}
