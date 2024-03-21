package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "memberSaveServlet", urlPatterns = "/servlet/members/save")
public class MemberSaveServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String age = req.getParameter("age");

        Member member = new Member(name, Integer.parseInt(age));
        memberRepository.save(member);

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        resp.getWriter().write(
                "<html>\n" +
                        "<head>\n" +
                        "    <meta charset=\"UTF-8\">\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "    <h1>회원 저장 결과</h1>\n" +
                        "    <p>아이디: " + member.getId() + "</p>\n" +
                        "    <p>이름: " + member.getUsername() + "</p>\n" +
                        "    <p>나이: " + member.getAge() + "</p>\n" +
                        "    <a href=\"/index.html\">메인</a>\n" +
                        "    <a href=\"/servlet/members/new-form\">신규 회원 등록</a>\n" +
                        "    <a href=\"/servlet/members\">회원 목록</a>\n" +
                        "</body>\n" +
                        "</html>"
        );

    }
}
