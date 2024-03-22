package hello.servlet.web.frontcontroller.v4;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import hello.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// v4 하위의 어떤 경로도 이 서블릿이 처리하도록 설정
@WebServlet(name = "frontControllerV4", urlPatterns = "/front-controller/v4/*")
public class FrontControllerServletV4 extends HttpServlet {
    private final Map<String, ControllerV4> controllerMapV4 = new HashMap<>();

    /**
     * controllerV4Map에 URL 패턴과 해당 URL을 처리할 컨트롤러를 매핑합니다.
     * "/front-controller/v4/members/new-form" URL 패턴은 MemberFormControllerV4 객체를 통해 처리됩니다.
     * "/front-controller/v4/members/save" URL 패턴은 MemberSaveControllerV4 객체를 통해 처리됩니다.
     * "/front-controller/v4/members/members" URL 패턴은 MemberListControllerV4 객체를 통해 처리됩니다.
     */
    public FrontControllerServletV4(){
        controllerMapV4.put("/front-controller/v4/members/new-form", new MemberFormControllerV4());
        controllerMapV4.put("/front-controller/v4/members/save", new MemberSaveControllerV4());
        controllerMapV4.put("/front-controller/v4/members", new MemberListControllerV4());
    }
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV4.service");

        String requestURI = request.getRequestURI();
        ControllerV4 controller = controllerMapV4.get(requestURI);

        if(controller == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();
//        ModelView mv = controller.process(paramMap);

        String viewName = controller.process(paramMap, model);

        MyView myView = viewResolver(viewName);
        myView.render(model,request, response);
    }

    /**
     * 이 메소드는 제공된 뷰 이름에 기반하여 뷰를 해결하는데 사용됩니다.
     * "/WEB-INF/views/" 디렉토리에 있는 JSP 파일의 경로를 구성하고 이 경로를 가진 새로운 MyView 객체를 반환합니다.
     *
     * @param viewName 해결할 뷰의 이름입니다. 이것은 "/WEB-INF/views/" 디렉토리에 있는 JSP 파일의 이름과 일치해야 하며, ".jsp" 확장자는 없어야 합니다.
     * @return 뷰를 렌더링하는데 사용할 수 있는 새로운 MyView 객체를 반환합니다.
     */
    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String ,String > createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
