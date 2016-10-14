package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {
	
	public User findById(int id) {
		return getByKey(id);
	}
	
	public User findByEmail(String email) {
		return null;	
	}
	
	public User findByUsername(String username) {
		return null;
	}
	
	public User verifyUser(User user) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return null;
	}
	
	public void createUser(User user) {
		persist(user);
	}
	
	public void deleteUser(User user) {
		
	}
	
	public void editUser(User user) {
		
	}

}
