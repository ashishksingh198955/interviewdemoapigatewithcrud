package com.demo.departmentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.departmentservice.models.Department;




public interface DepartmentRepository extends JpaRepository<Department, Long> {

	@Query(value = "select new com.demo.departmentservice.dto.DepartmentDTO(depinfo.departmentid,depinfo.departmentname,depinfo.departmentCode,depinfo.organizationName) from Department as depinfo")
	List<com.demo.departmentservice.dto.DepartmentDTO> findAllDepartment();
}
