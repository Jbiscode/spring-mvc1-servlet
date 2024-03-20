package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // status-line
        resp.setStatus(HttpServletResponse.SC_OK);

        // response-headers
        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("my-header", "hello");

//        [Header 편의 메서드]
        content(resp);
//       [쿠키 편의 메서드]
        cookie(resp);
//        [Redirect 편의 메서드]
        redirect(resp);

        PrintWriter writer = resp.getWriter();
        writer.println("안녕하세요.");
    }


    private void content(HttpServletResponse resp) {
//        Content-Type: text/plain;charset=utf-8
//        Content-Length: 2
//        resp.setHeader("Content-Type", "text/plain;charset=utf-8");
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
    }

    private void cookie(HttpServletResponse resp) {
//        Set-Cookie: myCookie=good; Max-Age=600;
//        resp.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);
        resp.addCookie(cookie);
    }
    private void redirect(HttpServletResponse resp) throws IOException {
//        Status Code 302
//        Location: /basic/hello-form.html

//        resp.setStatus(HttpServletResponse.SC_FOUND); // 302
//        resp.setHeader("Location", "/basic/hello-form.html");

//        이 방법을 사용하면 위의 두줄을 한줄로 대체할 수 있다.
        resp.sendRedirect("/basic/hello-form.html");
    }

}

