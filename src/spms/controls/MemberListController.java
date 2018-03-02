package spms.controls;

import javax.servlet.ServletException;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import spms.vo.Member;
import spms.dao.MemberDao;
import spms.annotation.Component;
import spms.util.DataBinding;

@Component("/member/list.do")
public class MemberListController implements Controller, DataBinding {
	MemberDao memberDao;

	public MemberListController setMemberDao (MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	public Object[] getDataBinding() {
		return new Object[] {
				"orderCond", String.class
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub

		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("orderCond", model.get("orderCond"));
			System.out.println("orderCond: " + model.get("orderCond"));
			model.put("members", (List<Member>)memberDao.selectList(paramMap)); // request에 회원 목록 데이터를 보관한다.
			return "/member/MemberList.jsp";
		} catch (Exception e) {
			// throw new ServletException(e);
			throw new ServletException(e);
		}
	}

}
