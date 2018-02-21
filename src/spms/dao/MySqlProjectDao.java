package spms.dao;

import spms.vo.Project;
import spms.annotation.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Component("projectDao")
public class MySqlProjectDao implements ProjectDao {

    DataSource ds;

    public void setDataSource(DataSource ds) {
        this.ds = ds;
    }

    public void addProject (Project project)
            throws Exception {
        PreparedStatement stmt = null;
        Connection connection = ds.getConnection();

        try {
            stmt = connection.prepareStatement("INSERT INTO PROJECTS(PNAME, CONTENT," +
                    " STA_DATE, END_DATE, STATE, CRE_DATE, TAGS) " +
                    "VALUES(?, ?, ?, ?, 0, NOW(), ?)");
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getContent());
            stmt.setDate(3, new Date(project.getStartDate().getTime()));
            stmt.setDate(4, new Date(project.getEndDate().getTime()));
            stmt.setString(5, project.getTags());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try { if(stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    public List<Project> selectList () throws Exception {
        Statement stmt = null;
        Connection connection = ds.getConnection();
        ResultSet rs = null;
        try {
            stmt = connection.createStatement();
            rs = stmt.executeQuery(
                    "SELECT PNO, PNAME, STA_DATE, END_DATE, STATE " +
                    "FROM PROJECTS"
            );

            ArrayList<Project> list = new ArrayList<Project>();

            while(rs.next()) {
                list.add(
                        new Project().setNo(rs.getInt("PNO"))
                            .setName(rs.getString("PNAME"))
                            .setStartDate(rs.getDate("STA_DATE"))
                            .setEndDate(rs.getDate("END_DATE"))
                            .setState(rs.getInt("STATE"))
                );
            }

            return list;
        } catch (Exception e) {
            throw e;
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    public Project updateList (int no) throws Exception {
        ResultSet rs = null;
        Statement stmt = null;
        Connection connection = ds.getConnection();

        try {
            System.out.println("no: " + no);
            stmt = connection.createStatement();
            rs = stmt.executeQuery(
                    "SELECT PNO, PNAME, CONTENT, STA_DATE, END_DATE, STATE, TAGS FROM PROJECTS " +
                            "WHERE PNO=" + no
            );

            rs.next();

            return new Project().setNo(rs.getInt("PNO")).setName(rs.getString("PNAME"))
                            .setContent(rs.getString("CONTENT")).setStartDate(rs.getDate("STA_DATE"))
                            .setEndDate(rs.getDate("END_DATE")).setState(rs.getInt("STATE"))
                            .setTags(rs.getString("TAGS"));
        } catch (Exception e) {
            throw e;
        } finally {
            try { if(rs != null) rs.close(); } catch (Exception e) {}
            try { if(stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    public void updateProject (Project project) throws Exception {
        PreparedStatement stmt = null;
        Connection connection = ds.getConnection();

        try {
            stmt = connection.prepareStatement("UPDATE PROJECTS SET PNAME=?, CONTENT=?, STA_DATE=?, " +
                    "END_DATE=?, STATE=?, TAGS=? WHERE PNO=?");
            stmt.setString(1, project.getName());
            stmt.setString(2, project.getContent());
            stmt.setDate(3, new Date(project.getStartDate().getTime()));
            stmt.setDate(4, new Date(project.getEndDate().getTime()));
            stmt.setInt(5, project.getState());
            stmt.setString(6, project.getTags());
            stmt.setInt(7, project.getNo());
            stmt.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            try { if(stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (connection != null) connection.close(); } catch (Exception e) {}
        }
    }

    public void deleteProject (int no) throws Exception {
        Statement stmt = null;
        Connection connection = ds.getConnection();

        try {
            stmt = connection.createStatement();
            stmt.executeUpdate("DELETE FROM PROJECTS WHERE PNO=" + no);
        } catch (Exception e) {
            throw e;
        } finally {
            try { if(stmt != null) stmt.close(); } catch (Exception e) {}
            try { if (connection != null) connection.close(); } catch (Exception e) {}
        }
    }
}
