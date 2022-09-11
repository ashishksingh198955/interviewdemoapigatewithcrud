package com.demo.service.users.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;

import com.demo.service.users.models.Role;

public class UserDTO {

    private Long userid;
	private String firstname;
	 private String middlename;
	 private String rolename;

	private String lastname;

	private String gender;

	private String position;

	private String contact;

	private Long companyid;

	private long createdby;

	private Date createdatetime;

	private Date auditeddatetime;

	private long auditedby;

	private String password;

	private String email;
	
	private String username;


	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	

	public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public Long getCompanyid() {
		return companyid;
	}

	public void setCompanyid(Long companyid) {
		this.companyid = companyid;
	}

	public long getCreatedby() {
		return createdby;
	}

	public void setCreatedby(long createdby) {
		this.createdby = createdby;
	}

	public Date getCreatedatetime() {
		return createdatetime;
	}

	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}

	public Date getAuditeddatetime() {
		return auditeddatetime;
	}

	public void setAuditeddatetime(Date auditeddatetime) {
		this.auditeddatetime = auditeddatetime;
	}

	public long getAuditedby() {
		return auditedby;
	}

	public void setAuditedby(long auditedby) {
		this.auditedby = auditedby;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	

	public UserDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getRole() {
		return rolename;
	}

	public void setRole(String role) {
		this.rolename = role;
	}


	public UserDTO(Long userid, String firstname, String middlename, String lastname, String email) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
//		this.role = role;
		
	}

	public UserDTO(Long userid, String firstname, String middlename, String lastname, String email,String position,String rolename) {
		super();
		this.userid = userid;
		this.firstname = firstname;
		this.middlename = middlename;
		this.lastname = lastname;
		this.email = email;
		this.position = position;
		this.rolename=rolename;
	}
	
	
	@Override
	public String toString() {
		return "UserDTO [userid=" + userid + ", firstname=" + firstname + ", middlename=" + middlename + ", role="
				+ rolename + ", lastname=" + lastname + ", gender=" + gender + ", position=" + position + ", contact="
				+ contact + ", companyid=" + companyid + ", createdby=" + createdby + ", createdatetime="
				+ createdatetime + ", auditeddatetime=" + auditeddatetime + ", auditedby=" + auditedby + ", password="
				+ password + ", email=" + email + ", username=" + username + "]";
	}





	

	
	
}
