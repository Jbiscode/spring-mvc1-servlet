package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        System.out.println("=====================================");
        printHeaders(req);
        System.out.println("=====================================");
        printHeaderUtils(req);
        System.out.println("=====================================");
        printEtc(req);
    }

    private static void printStartLine(HttpServletRequest req) {
        System.out.println("[전체 헤더 정보 조회] - start");
        System.out.println("req.getMethod() = " + req.getMethod());
        System.out.println("req.getProtocol() = " + req.getProtocol());
        System.out.println("req.getScheme() = " + req.getScheme());
        System.out.println("req.getRequestURL() = " + req.getRequestURL());
        System.out.println("req.getRequestURI() = " + req.getRequestURI());
        System.out.println("req.getQueryString() = " + req.getQueryString());
        System.out.println("req.isSecure() = " + req.isSecure());
        System.out.println("[전체 헤더 정보 조회] - end");
        System.out.println();
    }

    // 헤더의 모든 정보를 출력하는 메서드
    private static void printHeaders(HttpServletRequest req) {
        System.out.println("[헤더 전체 조회] - start");
        req.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + req.getHeader(headerName)));
        System.out.println("[헤더 전체 조회] - end");
        System.out.println();

//        헤더 하나만 조회하고싶을때
//        System.out.println("req.getHeader(\"host\") = " + req.getHeader("host"));
    }

//    printHeaderUtils
    private static void printHeaderUtils(HttpServletRequest req) {
        System.out.println("[헤더 편의 조회] - start");
        System.out.println("[Host] 편의조회");
        System.out.println("req.getServerName() = " + req.getServerName()); // Host 헤더
        System.out.println("req.getServerPort() = " + req.getServerPort()); // Host 헤더
        System.out.println();

        System.out.println("[Accept-Language] 편의 조회");
        req.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " + locale));
        System.out.println("req.getLocale() = " + req.getLocale());
        System.out.println();

        System.out.println("[cookie] 편의 조회");
        if (req.getCookies() != null) {
            for (var cookie : req.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content] 편의 조회");
        System.out.println("req.getContentType() = " + req.getContentType());
        System.out.println("req.getContentLength() = " + req.getContentLength());
        System.out.println("req.getCharacterEncoding() = " + req.getCharacterEncoding());

        System.out.println("[헤더 편의 조회] - end");
        System.out.println();
    }

//    printEtc
    private static void printEtc(HttpServletRequest req) {
        System.out.println("[기타 조회]");
        System.out.println("[Remote 정보] - start");
        System.out.println("req.getRemoteHost() = " + req.getRemoteHost()); // 클라이언트 IP
        System.out.println("req.getRemoteAddr() = " + req.getRemoteAddr()); // 클라이언트 IP
        System.out.println("req.getRemotePort() = " + req.getRemotePort()); // 클라이언트 포트
        System.out.println();

        System.out.println("[Local 정보] - start");
        System.out.println("req.getLocalName() = " + req.getLocalName()); // 내 서버 IP
        System.out.println("req.getLocalAddr() = " + req.getLocalAddr()); // 내 서버 IP
        System.out.println("req.getLocalPort() = " + req.getLocalPort()); // 내 서버 포트
        System.out.println();
        System.out.println("[기타조회] - end");
        System.out.println();
    }
}
