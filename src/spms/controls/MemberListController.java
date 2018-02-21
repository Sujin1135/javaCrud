package spms.controls;

import javax.servlet.ServletException;

import java.util.List;
import java.util.Map;

import spms.vo.Member;
import spms.dao.MemberDao;
import spms.annotation.Component;

@Component("/member/list.do")
public class MemberListController implements Controller{
	MemberDao memberDao;

	public MemberListController setMemberDao (MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		// TODO Auto-generated method stub
		
		try {
			model.put("members", (List<Member>)memberDao.selectList()); // request에 회원 목록 데이터를 보관한다.
			System.out.println(model.get("members"));
			return "/member/MemberList.jsp";
		} catch (Exception e) {
			// throw new ServletException(e);
			throw new ServletException(e);
		}
	}

}
