package hello.servlet.web.frontcontroller.v2;

import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// v2 하위의 어떤 경로도 이 서블릿이 처리하도록 설정
@WebServlet(name = "frontControllerV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    private final Map<String, ControllerV2> controllerMapV2 = new HashMap<>();

    /**
     * controllerV2Map에 URL 패턴과 해당 URL을 처리할 컨트롤러를 매핑합니다.
     * "/front-controller/v2/members/new-form" URL 패턴은 MemberFormControllerV2 객체를 통해 처리됩니다.
     * "/front-controller/v2/members/save" URL 패턴은 MemberSaveControllerV2 객체를 통해 처리됩니다.
     * "/front-controller/v2/members/members" URL 패턴은 MemberListControllerV2 객체를 통해 처리됩니다.
     */
    public FrontControllerServletV2(){
        controllerMapV2.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllerMapV2.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllerMapV2.put("/front-controller/v2/members", new MemberListControllerV2());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV2.service");

        String requestURI = request.getRequestURI();
        ControllerV2 controller = controllerMapV2.get(requestURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
