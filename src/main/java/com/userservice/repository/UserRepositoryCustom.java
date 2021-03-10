package com.userservice.repository;

import java.util.List;

import com.userservice.entity.User;

public interface UserRepositoryCustom {

	List<User> searchUserByFilter(String firstName, String lastName, String pinCode);

}
