package spms.controls;

import java.util.Map;

import spms.annotation.Component;
import spms.dao.ProjectDao;

@Component("/project/list.do")
public class ProjectListController implements Controller {
    ProjectDao projectDao;

    public ProjectListController setProjectDao (ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    public String execute (Map<String, Object> model) throws Exception {
        try {
            model.put("projects", projectDao.selectList());
            return "/project/ProjectList.jsp";
        } catch (Exception e) {
            throw e;
        }
    }
}
