package spms.dao;

import javax.sql.DataSource;

import spms.vo.Project;

import java.util.List;

public interface ProjectDao {
    void setDataSource(DataSource ds);
    void addProject(Project project) throws Exception;
    List<Project> selectList () throws Exception;
    void updateProject (Project project) throws Exception;
    Project updateList (int no) throws Exception;
    void deleteProject (int no) throws Exception;
}
