package spms.controls;

import java.util.Map;

import javax.servlet.http.HttpSession;

import spms.dao.MemberDao;
import spms.vo.Member;
import spms.util.DataBinding;
import spms.annotation.Component;

/**
 * Servlet implementation class LoginServlet
 */

@SuppressWarnings("serial")
@Component("/auth/login.do")
public class LoginController implements Controller, DataBinding {
	MemberDao memberDao;

	public LoginController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	public Object[] getDataBinding() {
		return new Object[]{"loginInfo", spms.vo.Member.class};
	}

	public String execute (Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		try {
			Member member = (Member)model.get("loginInfo");

			System.out.println(member.getEmail());
			if (member.getEmail() == null) {
				return "/auth/LoginForm.jsp";
			} else {
				member = memberDao.memberLogin(member);

				if (member.getName() != null) {
					HttpSession session = (HttpSession)model.get("session");
					session.setAttribute("member", member);
					return "redirect:../member/list.do";
				} else {
					return "/auth/LoginFail.jsp";
				}
			}
		} catch (Exception e) {
			throw e;
		}
	}
}
