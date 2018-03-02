package spms.dao;

import java.util.List;
import java.util.HashMap;

import spms.vo.Member;
import spms.annotation.Component;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
	/*
	 * MemberDao 에서는 서블릿의 보관소(Servlet Context)에 접근할 수 없기 때문에
	 * Connection의 인스턴스 변수를 생성한 후 셋터 메서드를 사용하여 서블릿에서 connection을 주입받아야 한다.
	 * 이를 의존성 주입(DI, Dependency Injection) 이라고 한다.
	 */
	SqlSessionFactory sqlSessionFactory;

	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		this.sqlSessionFactory = sqlSessionFactory;
	}
	
	public List<Member> selectList(HashMap<String, Object> paramMap) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			return sqlSession.selectList("spms.dao.MemberDao.selectList", paramMap);
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	
	public void addMember (Member member) throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			sqlSession.insert("spms.dao.MemberDao.insert", member);
			sqlSession.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	
	public void deleteMember(int no)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			sqlSession.delete("spms.dao.MemberDao.delete", no);
			sqlSession.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	
	public void updateMember(Member member)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			sqlSession.update("spms.dao.MemberDao.update", member);
			sqlSession.commit();
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	
	public Member updateList(int no)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			return sqlSession.selectOne("spms.dao.MemberDao.selectOne", no);
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}
	
	public Member memberLogin(Member member)
			throws Exception {
		SqlSession sqlSession = sqlSessionFactory.openSession();

		try {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			Member loginMember = sqlSession.selectOne("spms.dao.MemberDao.login",
					member);
			
			if(loginMember.getEmail() != null) {
				return loginMember;
			} else {
				return new Member();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			sqlSession.close();
		}
	}
}
