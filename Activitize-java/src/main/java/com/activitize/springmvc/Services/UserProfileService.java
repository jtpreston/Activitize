package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.UserProfile;

public interface UserProfileService {

	UserProfile findById(int id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();
	
}
