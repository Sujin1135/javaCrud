package spms.controls;

import spms.vo.Project;
import spms.util.DataBinding;
import spms.annotation.Component;
import spms.dao.ProjectDao;

import java.util.Map;

@Component("/project/update.do")
public class ProjectUpdateController implements Controller, DataBinding {
    ProjectDao projectDao;

    public Object[] getDataBinding () {
        return new Object[] {
                "no", Integer.class,
                "project", spms.vo.Project.class
        };
    }

    public ProjectUpdateController setProjectDao (ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    public String execute (Map<String, Object> model) throws Exception {
        Project project = (Project)model.get("project");

        try {
            if(project.getName() == null) {
                model.put("project", projectDao.updateList((int)model.get("no")));
                return "/project/ProjectUpdate.jsp";
            } else {
                projectDao.updateProject(project);
                return "redirect:list.do";
            }
        } catch (Exception e) {
            throw e;
        }
    }
}
