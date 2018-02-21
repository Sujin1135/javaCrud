package spms.controls;

import spms.annotation.Component;
import spms.util.DataBinding;
import spms.dao.ProjectDao;
import spms.vo.Project;

import java.util.Map;

@Component("/project/add.do")
public class ProjectAddController implements DataBinding, Controller {
    ProjectDao projectDao;

    public Object[] getDataBinding() {
        return new Object[] {
          "project", spms.vo.Project.class
        };
    }

    public ProjectAddController setProjectDao (ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    public String execute (Map<String, Object> model) throws Exception {
        Project project = (Project)model.get("project");

        if (project.getName() == null) {
            return "/project/ProjectAdd.jsp";
        } else {
            System.out.println(project.getStartDate());
            System.out.println(projectDao);
            projectDao.addProject(project);
            return "redirect:add.do";
        }
    }
}
