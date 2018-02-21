package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;
import spms.util.DataBinding;
import spms.annotation.Component;

@Component("/member/delete.do")
public class MemberDeleteController implements Controller, DataBinding {
	MemberDao memberDao;

	public Object[] getDataBinding() {
		return new Object[]{"no", Integer.class};
	}

	public MemberDeleteController setMemberDao (MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	public String execute(Map<String, Object> model) throws Exception {
		memberDao.deleteMember((int)model.get("no"));
		return "redirect:list.do";
	}
}
