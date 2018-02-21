package spms.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.RequestDispatcher;

import java.util.HashMap;

import spms.controls.*;
import spms.util.DataBinding;
import spms.bind.ServletRequestDataBinder;
import spms.context.ApplicationContext;
import spms.listeners.ContextLoaderListener;

@SuppressWarnings("serial")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(
            HttpServletRequest request, HttpServletResponse response
    ) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8"); // 응답 시 인코딩 설정
        String servletPath = request.getServletPath(); // 요청이 들어온 url 주소를 저장.


        try {
            ApplicationContext ctx = ContextLoaderListener.getApplicationContext();
            HashMap<String, Object> model = new HashMap<String, Object>();
            Controller pageController = (Controller)ctx.getBean(servletPath);

            model.put("session", request.getSession());

            if (pageController == null) {
                throw new Exception("요청한 서비스를 찾을 수 없습니다.");
            }

            if (pageController instanceof DataBinding) {
                prepareRequestData(request, model, (DataBinding)pageController);
            }

            // 사용자가 요청한 url의 서블릿으로 이동하는 rd

            String viewUrl = pageController.execute(model);

            for (String key : model.keySet()) { // model의 key, value를 pageContainer에 등록한다.
                request.setAttribute(key, model.get(key));
            }

            if(viewUrl.startsWith("redirect:")) { // viewUrl이 "redirect:" 로 시작될 경우
                response.sendRedirect(viewUrl.substring(9)); // view Url중 redirect: 이후의 url정보를 response.sendRedirect의 매개변수로 준다.
                return ;
            } else { // viewUrl이 "redirect:" 로 시작하지 않을 경우
                RequestDispatcher rd = request.getRequestDispatcher(viewUrl);
                rd.include(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e);
            RequestDispatcher rd = request.getRequestDispatcher("/Error.jsp");
            rd.forward(request, response);
        }
    }

    private void prepareRequestData (HttpServletRequest request,
             HashMap<String, Object> model, DataBinding dataBinding) throws Exception {
        Object[] dataBinders = dataBinding.getDataBinding(); // 컨트롤러에 필요한 vo 객체를 Object 배열로 담아 반환한다.
        String dataName = null; // 데이터의 이름을 담을 객체
        Class<?> dataType = null; // 데이터의 타입을 담을 객체
        Object dataObj = null;
        for (int i = 0; i < dataBinders.length; i += 2) {
            dataName = (String)dataBinders[i]; // 데이터의 이름을 담는다.
            dataType = (Class<?>) dataBinders[i+1]; // 데이터의 타입을 담는다.
            dataObj = ServletRequestDataBinder.bind(request, dataType, dataName); // 매개변수로 받은 파라미터 값을 세터 메서드에 저장한다.
            model.put(dataName, dataObj);
        }
    }
}