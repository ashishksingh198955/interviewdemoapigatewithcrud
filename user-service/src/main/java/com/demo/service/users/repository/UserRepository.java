package com.demo.service.users.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.service.users.dto.UserDTO;
import com.demo.service.users.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Query(value ="select new com.demo.service.users.dto.UserDTO(user.userid,user.firstname ,user.middlename ,user.lastname,user.email) from User as user ")

	Optional<UserDTO> findByEmailIsNull(String email);

	Boolean existsByEmail(String email);

	@Query(value = "select count(*) from users where email =:email and status=1",nativeQuery = true)
	public int isUserActive(@Param("email") String email);

	@Query(value ="select new com.demo.service.users.dto.UserDTO(user.userid,user.firstname ,user.middlename ,user.lastname,user.email,user.position,r.rolename) from User as user inner join com.demo.service.users.models.UserRole as ur on ur.userid= user.userid inner join com.demo.service.users.models.Role as r on r.roleid= ur.roleId where user.status=1 ")
	List<UserDTO> findAllEmployee();

}
