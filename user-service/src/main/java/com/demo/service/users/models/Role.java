package com.demo.service.users.models;

import javax.persistence.*;

import com.demo.service.users.utils.ApplicationTableConstants;

@Entity
@Table(name = ApplicationTableConstants.ROLE_MASTER)
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = ApplicationTableConstants.ROLE_ID)
	private Integer roleid;

    @Column(name = ApplicationTableConstants.ROLE_NAME)
	private String rolename;

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Role(Integer roleid, String rolename) {
		this.roleid = roleid;
		this.rolename = rolename;
	}

	public Role() {
		
	}

	@Override
	public String toString() {
		return "Role [roleid=" + roleid + ", rolename=" + rolename + "]";
	}

	
}