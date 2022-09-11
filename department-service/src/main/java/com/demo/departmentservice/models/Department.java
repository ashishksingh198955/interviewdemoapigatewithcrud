package com.demo.departmentservice.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.demo.departmentservice.utils.ApplicationTableConstants;

@Entity
@Table(	name = com.demo.departmentservice.utils.ApplicationTableConstants.DEPARTMENT_INFO)
public class Department implements Serializable {
	
	private static final long serialVersionUID = 8851505104868965779L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ApplicationTableConstants.DEPARTMENT_ID)
	private Long departmentid;

	@Column(name = ApplicationTableConstants.DEPARTMENT_NAME)
	private String departmentname;

	@Column(name = ApplicationTableConstants.DEPARTMENT_CODE)
	private String departmentCode;

	@Column(name = ApplicationTableConstants.ORGANIZATION_NAME)
	private String organizationName;

	public Long getDepartmentid() {
		return departmentid;
	}

	public void setDepartmentid(Long departmentid) {
		this.departmentid = departmentid;
	}

	public String getDepartmentname() {
		return departmentname;
	}

	public void setDepartmentname(String departmentname) {
		this.departmentname = departmentname;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

}
