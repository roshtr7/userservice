package com.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByIdAndIsDelete(Long id, Boolean deleteFlag);

	@Query("SELECT u.id FROM User u")
	Optional<Long> findByEmail(String email);

	void deleteById(Long id);

	@Query("SELECT u FROM User u ORDER BY u.dob DESC, u.doj DESC")
	List<User> getUsersOrderByDOBAndDOJ();
}
