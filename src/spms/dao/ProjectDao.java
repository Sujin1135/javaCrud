package spms.dao;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import spms.vo.Project;

import java.util.List;
import java.util.HashMap;

public interface ProjectDao {
    void setSqlSessionFactory (SqlSessionFactory sqlSessionFactory);
    void insert(Project project) throws Exception;
    List<Project> selectList(HashMap<String, Object> paramMap) throws Exception;
    Project selectOne(int no) throws Exception;
    int update(Project project) throws Exception;
    void delete(int no) throws Exception;
}
