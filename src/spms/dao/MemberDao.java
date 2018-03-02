package spms.dao;

import java.util.List;
import java.util.HashMap;

import spms.vo.Member;

import org.apache.ibatis.session.SqlSessionFactory;

public interface MemberDao {
    List<Member> selectList(HashMap<String, Object> paramMap) throws Exception;
    void addMember(Member member) throws Exception;
    void deleteMember(int no) throws Exception;
    void updateMember(Member member) throws Exception;
    Member updateList(int no) throws Exception;
    Member memberLogin(Member member) throws Exception;
    void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory);
}
