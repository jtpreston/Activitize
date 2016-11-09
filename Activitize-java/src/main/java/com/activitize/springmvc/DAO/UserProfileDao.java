package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.UserProfile;

public interface UserProfileDao {

	List<UserProfile> findAll();
    
	UserProfile findByType(String type);
     
	UserProfile findById(int id);
	
}
