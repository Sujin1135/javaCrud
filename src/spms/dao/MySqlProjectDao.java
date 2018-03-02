package spms.dao;

import spms.vo.Project;
import spms.annotation.Component;

import java.util.List;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {

    SqlSessionFactory sqlSessionFactory; // SqlSession 객체를 생성하는 객체

    public void setSqlSessionFactory (SqlSessionFactory sqlSessionFactory) { // SqlSessionFactory Setter
        this.sqlSessionFactory = sqlSessionFactory;
    }

    public void insert (Project project) throws Exception {
        // jdbc를 사용하여 sql문을 전송하는 객체
        // openSession(true) 라고 입력할 경우 자동으로 sqlSession.commit()을 처리해준다.
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            // spms.dao.ProjectDao.insert == SQL 맵퍼의 네임스페이스 이름 + SQL 문 ID
            sqlSession.insert("spms.dao.ProjectDao.insert", project); // project는 SQL 맵퍼에서 parameter로 들어간다.
            sqlSession.commit(); // 임시 저장소에 있는 sql문을 데이터 베이스에 전송
        } catch (Exception e) {
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    public List<Project> selectList (HashMap<String, Object> paramMap) throws Exception {
       SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            return sqlSession.selectList("spms.dao.ProjectDao.selectList", paramMap); // vo 목록을 반환
        } catch (Exception e) {
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    public Project selectOne (int no) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            return sqlSession.selectOne("spms.dao.ProjectDao.selectOne", no); // 하나의 값 객체 목록을 반환
        } catch (Exception e) {
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    public int update (Project project) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        HashMap<String, Object> paramMap = projectEquals(sqlSession, project);

        if (paramMap.size() > 0) {
            try {
                System.out.println("paramMap.get('content') = " + paramMap.get("content"));
                int count = sqlSession.update("spms.dao.ProjectDao.update", paramMap); // 객체 데이터를 변경
                sqlSession.commit();
                return count;
            } catch (Exception e) {
                throw e;
            } finally {
                sqlSession.close();
            }
        } else {
            return 0;
        }
    }

    public void delete (int no) throws Exception {
        SqlSession sqlSession = sqlSessionFactory.openSession();

        try {
            sqlSession.delete("spms.dao.ProjectDao.delete", no); // 객체 데이터를 삭제
            sqlSession.commit();
        } catch (Exception e) {
            throw e;
        } finally {
            sqlSession.close();
        }
    }

    private static HashMap<String, Object> projectEquals(SqlSession sqlSession, Project project) {
        Project original = sqlSession.selectOne("spms.dao.ProjectDao.selectOne", project.getNo());
        HashMap<String, Object> paramMap = new HashMap<String, Object>();

        if (!original.getName().equals(project.getName())) {
            paramMap.put("name", project.getName());
        }
        if (!original.getContent().equals(project.getContent())) {
            paramMap.put("content", project.getContent());
        }
        if (original.getStartDate().compareTo(project.getStartDate()) != 0) {
            paramMap.put("startDate", project.getStartDate());
        }
        if (original.getEndDate().compareTo(project.getEndDate()) != 0) {
            paramMap.put("endDate", project.getEndDate());
        }
        if (original.getState() != project.getState()) {
            paramMap.put("state", project.getState());
        }
        if (!original.getTags().equals(project.getTags())) {
            paramMap.put("tags", project.getTags());
        }
        paramMap.put("no", project.getNo());

        return paramMap;
    }
}
