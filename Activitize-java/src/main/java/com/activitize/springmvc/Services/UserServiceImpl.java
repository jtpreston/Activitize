package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
    private UserDao dao;
	
	public User findById(int id) {
		return dao.findById(id);
	}
	
	public User findByEmail(String email) {
		return dao.findByEmail(email);	
	}
	
	public User findByUsername(String username) {
		return dao.findByUsername(username);
	}
	
	public User verifyUser(User user) {
		return dao.verifyUser(user);
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}
	
	public void createUser(User user) {
		dao.createUser(user);
	}
	
	public void deleteUser(User user) {
		dao.deleteUser(user);
	}
	
	public void editUser(User user) {
		dao.editUser(user);
	}
	
	public boolean isUserEmailUnique(String email) {
		User user = findByEmail(email);
		if (user == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean isUsernameUnique(String username) {
		User user = findByUsername(username);
		if (user == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
}
