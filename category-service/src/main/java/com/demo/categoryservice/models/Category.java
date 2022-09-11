package com.demo.categoryservice.models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.LastModifiedDate;

import com.demo.categoryservice.utils.ApplicationConstants;
import com.demo.categoryservice.utils.ApplicationTableConstants;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(	name = com.demo.categoryservice.utils.ApplicationTableConstants.CATEGORY_INFO)
public class Category implements Serializable {
	
	private static final long serialVersionUID = 8851505104868965779L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = ApplicationTableConstants.CATEGORY_ID)
	private Long categoryid;

	@Column(name = ApplicationTableConstants.CATEGORY_NAME)
	private String categoryname;

	@Column(name = ApplicationTableConstants.CATEGORY_DESCRIPTION)
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
