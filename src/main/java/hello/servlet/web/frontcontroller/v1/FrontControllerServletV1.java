package hello.servlet.web.frontcontroller.v1;

import hello.servlet.web.frontcontroller.v1.controller.MemberFormControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberListControllerV1;
import hello.servlet.web.frontcontroller.v1.controller.MemberSaveControllerV1;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// v1 하위의 어떤 경로도 이 서블릿이 처리하도록 설정
@WebServlet(name = "frontControllerV1", urlPatterns = "/front-controller/v1/*")
public class FrontControllerServletV1 extends HttpServlet {
    private final Map<String, ControllerV1> controllerMapV1 = new HashMap<>();

    /**
     * controllerV1Map에 URL 패턴과 해당 URL을 처리할 컨트롤러를 매핑합니다.
     * "/front-controller/v1/members/new-form" URL 패턴은 MemberFormControllerV1 객체를 통해 처리됩니다.
     * "/front-controller/v1/members/save" URL 패턴은 MemberSaveControllerV1 객체를 통해 처리됩니다.
     * "/front-controller/v1/members/members" URL 패턴은 MemberListControllerV1 객체를 통해 처리됩니다.
     */
    public FrontControllerServletV1(){
    controllerMapV1.put("/front-controller/v1/members/new-form", new MemberFormControllerV1());
    controllerMapV1.put("/front-controller/v1/members/save", new MemberSaveControllerV1());
    controllerMapV1.put("/front-controller/v1/members", new MemberListControllerV1());
}
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV1.service");

        String requestURI = request.getRequestURI();
        ControllerV1 controller = controllerMapV1.get(requestURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        controller.process(request, response);
    }
}
