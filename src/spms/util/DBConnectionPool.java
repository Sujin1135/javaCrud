package spms.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

public class DBConnectionPool {
	/*
	 * url, username, password 는 연결할 데이터 베이스의 정보들이다.
	 */
	String url;
	String username;
	String password;
	ArrayList<Connection> connList = new ArrayList<Connection>(); // Connection 인스턴스를 담을 리스트
	
	public DBConnectionPool(String driver, String url,
			String username, String password) throws Exception { // 생성자 메서드
		/*
		 * db 커넥션 생성에 필요한 값들을 매개변수로 받은 후 저장한다.
		 */
		this.url = url;
		this.username = username;
		this.password = password;
		
		Class.forName(driver);
	}
	
	public Connection getConnection() throws Exception {
		if (connList.size() > 0) { // connList에 저장된 객체가 있을 경우
			Connection conn = connList.remove(0); // conn 객체를 생성 후 connList의 0번째 인덱스 객체를 저장한다.
			if (conn.isValid(10)) { // db에 연결되어있는지 확인하는 메서드
				return conn;
			}
		}
		
		return DriverManager.getConnection(url, username, password); // ArrayList에 보관된 객체가 없다면, 커넥션을 만들어서 반환한다.
	}
	
	public void returnConnection(Connection conn) {
		connList.add(conn);
	}
	
	public void closeAll() {
		for(Connection conn : connList) {
			try {
				conn.close();
			} catch (Exception e) {}
		}
	}
}
