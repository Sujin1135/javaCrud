package spms.controls;

import spms.util.DataBinding;
import spms.annotation.Component;
import spms.dao.ProjectDao;

import java.util.Map;

@Component("/project/delete.do")
public class ProjectDeleteController implements Controller, DataBinding {
    ProjectDao projectDao;

    public Object[] getDataBinding() {
        return new Object[] {
            "no", Integer.class
        };
    }

    public ProjectDeleteController setProjectDao (ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    public String execute (Map<String, Object> model) throws Exception {
        System.out.println(projectDao);
        projectDao.delete((int)model.get("no"));
        return "redirect:list.do";
    }
}
