package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {
    MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Member> memberList = memberRepository.findAll();

        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");

        PrintWriter writer = resp.getWriter();

//        StringBuilder sb = new StringBuilder();
        writer.write("<html>\n");
        writer.write("<head>\n");
        writer.write("    <meta charset=\"UTF-8\">\n");
        writer.write("    <title>Title</title>\n");
        writer.write("</head>\n");
        writer.write("<body>\n");
        writer.write("    <a href=\"/index.html\">메인</a>\n");
        writer.write("    <table>\n");
        writer.write("        <thead>\n");
        writer.write("            <th>id</th>\n");
        writer.write("            <th>username</th>\n");
        writer.write("            <th>age</th>\n");
        writer.write("        </thead>\n");
        writer.write("        <tbody>\n");

        for (Member member : memberList) {
            writer.write("        <tr>\n");
            writer.write("            <td>" + member.getId() + "</td>\n");
            writer.write("            <td>" + member.getUsername() + "</td>\n");
            writer.write("            <td>" + member.getAge() + "</td>\n");
            writer.write("        </tr>\n");
        }

        writer.write("        </tbody>\n");
        writer.write("    </table>\n");
        writer.write("</body>\n");
        writer.write("</html>\n");

//        writer.write(sb.toString());
    }
}
