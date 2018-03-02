package spms.controls;

import java.util.Map;

import spms.dao.MemberDao;
import spms.util.DataBinding;
import spms.vo.Member;
import spms.annotation.Component;

@Component("/member/update.do")
public class MemberUpdateController implements Controller, DataBinding {
    MemberDao memberDao;

    public Object[] getDataBinding() {
        return new Object[] {"no", Integer.class,
                "member", Member.class};
    }

    public MemberUpdateController setMemberDao(MemberDao memberDao) {
        this.memberDao = memberDao;
        return this;
    }

    @Override
    public String execute(Map<String, Object> model) throws Exception {
        Member member = (Member)model.get("member");

        try {
            if (member.getEmail() == null) {
                model.put("member", memberDao.updateList((int)model.get("no")));
                return "/member/MemberUpdate.jsp";
            } else {
                memberDao.updateMember((Member)model.get("member"));
                return "redirect:list.do";
            }
        } catch (Exception e) {
            throw e;
        }
    }
}