package com.demo.service.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.service.users.models.UserRole;

public interface UserRolesRepository extends JpaRepository<UserRole, Long> {

	
}
