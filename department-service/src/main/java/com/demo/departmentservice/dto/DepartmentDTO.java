package com.demo.departmentservice.dto;

public class DepartmentDTO {

	private Long departmentid;

	private String departmentname;

	private String departmentCode;

	private String organizationName;

	public Long getDepartmentid() {
		return departmentid;
	}


	@Override
	public String toString() {
		return "DepartmentDTO [departmentid=" + departmentid + ", departmentname=" + departmentname
				+ ", departmentCode=" + departmentCode + ", organizationName=" + organizationName + "]";
	}




	public DepartmentDTO(Long departmentid, String departmentname, String departmentCode, String organizationName) {
		super();
		this.departmentid = departmentid;
		this.departmentname = departmentname;
		this.departmentCode = departmentCode;
		this.organizationName = organizationName;
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




	public DepartmentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


}
