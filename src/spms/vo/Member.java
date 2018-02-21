package spms.vo;

import java.util.Date;

public class Member {
	protected int no;
	protected String name;
	protected String email;
	protected String password;
	protected Date createdDate;
	protected Date modifiedDate;
	
	/*
	 * 세터 메서드의 리턴값을 Member로 한 이유는 메서드 체이닝이 가능하게 하기 위해서다.
	 */
	public int getNo() {
		return no;
	}
	
	public Member setNo(int no) {
		this.no = no;
		return this;
	}
	
	public String getName() {
		return name;
	}
	
	public Member setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getEmail() {
		return email;
	}
	
	public Member setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getPassword() {
		return password;
	}
	
	public Member setPassword(String password) {
		this.password = password;
		return this;
	}
	
	public Date getCreateDate() {
		return createdDate;
	}
	
	public Member setCreateDate(Date createDate) {
		this.createdDate = createDate;
		return this;
	}
	
	public Date getModifiedDate() {
		return modifiedDate;
	}
	
	public Member setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		return this;
	}
}
