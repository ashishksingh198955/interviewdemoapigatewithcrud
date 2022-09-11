package com.demo.categoryservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.categoryservice.dto.CategoryDTO;
import com.demo.categoryservice.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	@Query(value = "select new com.demo.categoryservice.dto.CategoryDTO(catinfo.categoryid,catinfo.categoryname,catinfo.description) from Category as catinfo")
	List<CategoryDTO> findAllCompany();
}
