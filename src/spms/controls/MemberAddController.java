package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;
import spms.vo.Member;
import spms.util.DataBinding;
import spms.annotation.Component;

@Component("/member/add.do")
public class MemberAddController implements Controller,DataBinding {
	MemberDao memberDao;

	public MemberAddController setMemberDao (MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}

	public Object[] getDataBinding () {
		return new Object[]{"member", spms.vo.Member.class};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		Member member = (Member)model.get("member");

		if(member.getEmail() != null) {
			memberDao.addMember((Member)model.get("member"));
			return "redirect:list.do";
		} else {
			return "/member/MemberAdd.jsp";
		}
	}
}
