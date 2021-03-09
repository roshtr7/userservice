package com.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByIdAndDeleted(Long id, Boolean deleteFlag);
	
	@Query("SELECT u.id FROM users u")
	Optional<Long> findByEmail(String email);
}
