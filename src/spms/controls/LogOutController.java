package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.annotation.Component;

@Component("/auth/logout.do")
public class LogOutController implements Controller {

	public String execute (Map<String, Object> model) {
		// TODO Auto-generated method stub

		HttpSession session = (HttpSession)model.get("session");
		session.invalidate();
		
		return "redirect:/auth/login.do";
	}

}
