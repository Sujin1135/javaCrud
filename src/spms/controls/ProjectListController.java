package spms.controls;

import java.util.HashMap;
import java.util.Map;

import spms.annotation.Component;
import spms.dao.ProjectDao;
import spms.util.DataBinding;

@Component("/project/list.do")
public class ProjectListController implements Controller, DataBinding {
    ProjectDao projectDao;

    public ProjectListController setProjectDao (ProjectDao projectDao) {
        this.projectDao = projectDao;
        return this;
    }

    public Object[] getDataBinding() {
        return new Object[]{
                "orderCond", String.class
        };
    }

    public String execute (Map<String, Object> model) throws Exception {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        try {
            paramMap.put("orderCond", model.get("orderCond"));
            System.out.println(model.get("orderCond"));
            model.put("projects", projectDao.selectList(paramMap));
            return "/project/ProjectList.jsp";
        } catch (Exception e) {
            throw e;
        }
    }
}
