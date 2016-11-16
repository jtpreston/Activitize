package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.User;

public interface UserService {

	User findById(int id);

	User findByEmail(String email);

	User findByUsername(String username);

	List<User> findAllUsers();

	void createUser(User user);

	void deleteUser(User user);

	void editUser(User user);

	boolean isUserEmailUnique(String email);

	boolean isUsernameUnique(String username);
	
}
