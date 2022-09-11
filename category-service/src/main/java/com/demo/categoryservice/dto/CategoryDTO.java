package com.demo.categoryservice.dto;

public class CategoryDTO {

	 private Long categoryid;
	 private String categoryname; 
     private String description;	

	public Long getCategoryid() {
		return categoryid;
	}

	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
		
	}

	
	@Override
	public String toString() {
		return "CategoryDTO [companyid=" + categoryid + ", categoryname=" + categoryname 
				+ ", description=" + description +"]";
	}

	public CategoryDTO(Long categoryid, String categoryname, String description) {
		super();
		this.categoryid = categoryid;
		this.categoryname = categoryname;
		this.description= description;
	}

	public CategoryDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	 
	 
}
