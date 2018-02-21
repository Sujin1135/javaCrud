package spms.dao;

import java.util.List;

import spms.vo.Member;

import javax.sql.DataSource;

public interface MemberDao {
    List<Member> selectList() throws Exception;
    void addMember(Member member) throws Exception;
    void deleteMember(int no) throws Exception;
    void updateMember(Member member) throws Exception;
    Member updateList(int no) throws Exception;
    Member memberLogin(Member member) throws Exception;
    void setDataSource(DataSource ds);
}
