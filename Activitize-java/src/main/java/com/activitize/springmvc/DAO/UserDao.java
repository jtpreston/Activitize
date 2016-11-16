package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.User;

public interface UserDao {
	
	User findById(int id);

	User findByEmail(String email);

	User findByUsername(String username);

	List<User> findAllUsers();

	void createUser(User user);

	void deleteUser(User user);

	void editUser(User user);
	
}
