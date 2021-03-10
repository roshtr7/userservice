package com.userservice.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.userservice.entity.User;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

	User findByIdAndIsDelete(Long id, Boolean deleteFlag);

	@Query("SELECT u.id FROM User u where u.email = (:email)")
	Optional<Long> findByEmail(@Param("email")String email);

	void deleteById(Long id);

	@Query("SELECT u FROM User u ORDER BY u.dateOfBirth DESC, u.dateOfJoining DESC")
	List<User> getUsersOrderByDOBAndDOJ();
}
