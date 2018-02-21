package spms.dao;

import java.sql.*;
import java.util.List;

import javax.sql.DataSource;
import java.util.ArrayList;

import spms.vo.Member;
import spms.annotation.Component;

@Component("memberDao")
public class MySqlMemberDao implements MemberDao {
	/*
	 * MemberDao 에서는 서블릿의 보관소(Servlet Context)에 접근할 수 없기 때문에
	 * Connection의 인스턴스 변수를 생성한 후 셋터 메서드를 사용하여 서블릿에서 connection을 주입받아야 한다.
	 * 이를 의존성 주입(DI, Dependency Injection) 이라고 한다.
	 */
	DataSource ds;
	
	public void setDataSource(DataSource ds) {
		this.ds = ds;
	}
	
	public List<Member> selectList() throws Exception {
		Connection connection = ds.getConnection();
		
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = connection.createStatement();
			rs = stmt.executeQuery(
				"SELECT MNO, MNAME, EMAIL, CRE_DATE" +
				" FROM MEMBERS" +
				" ORDER BY MNO ASC"
			);
			
			ArrayList<Member> members = new ArrayList<Member>();
		
			while (rs.next()) {
				members.add(new Member()
					.setNo(rs.getInt("MNO"))
					.setName(rs.getString("MNAME"))
					.setEmail(rs.getString("EMAIL"))
					.setCreateDate(rs.getDate("CRE_DATE"))
				);
			}
			
			return members;
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close();; } catch (Exception e) {}
			try { if(stmt != null) stmt.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
	
	public void addMember (Member member) throws Exception {
		PreparedStatement stmt = null;
		Connection connection = ds.getConnection();
		try {
			stmt = connection.prepareStatement(
			"INSERT INTO MEMBERS(MNAME, PWD, EMAIL, CRE_DATE, MOD_DATE) VALUES "+
			"(?, ?, ?, NOW(), NOW())"
			);

			stmt.setString(1, member.getName());
			stmt.setString(2, member.getPassword());
			stmt.setString(3, member.getEmail());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if (stmt != null) stmt.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
	
	public void deleteMember(int no)
			throws Exception {
		PreparedStatement stmt = null;
		Connection connection = ds.getConnection();
		try {
			stmt = connection.prepareStatement("DELETE FROM MEMBERS WHERE MNO = ?");
			stmt.setInt(1, no);
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
	
	public void updateMember(Member member)
			throws Exception {
		PreparedStatement stmt = null;
		Connection connection = ds.getConnection();
		try {
			stmt = connection.prepareStatement("UPDATE MEMBERS SET MNAME=?, EMAIL=?, MOD_DATE = now()"
					+ " WHERE MNO=?");
			stmt.setString(1, member.getName());
			stmt.setString(2, member.getEmail());
			stmt.setInt(3, member.getNo());
			stmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(stmt != null) stmt.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
	
	public Member updateList(int no)
			throws Exception {
		Connection connection = ds.getConnection();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			System.out.println("update: " + no);
			stmt = connection.createStatement();
			rs = stmt.executeQuery("SELECT MNO, MNAME, EMAIL, CRE_DATE FROM MEMBERS WHERE MNO=" 
					+ no);
			rs.next();
			
			return new Member().setNo(rs.getInt("MNO"))
					.setName(rs.getString("MNAME")).setEmail(rs.getString("EMAIL"))
					.setCreateDate(rs.getDate("CRE_DATE"));
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close();; } catch (Exception e) {}
			try { if(stmt != null) stmt.close(); } catch (Exception e) {}
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
	
	public Member memberLogin(Member member)
			throws Exception {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Connection connection = ds.getConnection();
		try {
			stmt = connection.prepareStatement("SELECT MNAME, EMAIL FROM MEMBERS "
					+ "WHERE EMAIL=? AND PWD=?");
			stmt.setString(1, member.getEmail());
			stmt.setString(2, member.getPassword());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				member = new Member().setEmail(rs.getString("EMAIL"))
						.setName(rs.getString("MNAME")).setPassword(null);
				return member;
			} else {
				return new Member();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			try { if(rs != null) rs.close(); } catch (Exception e) {}
			try { if(stmt != null) stmt.close(); } catch (Exception e) {}
			// DBCP의 BasicDataSource는 커넥션의 대행 객체인 PoolableConnection 을 반환한다.
			try { if (connection != null) connection.close(); } catch (Exception e) {}
		}
	}
}
