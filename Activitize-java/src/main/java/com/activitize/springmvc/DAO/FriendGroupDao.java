package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.FriendGroup;
import com.activitize.springmvc.Models.User;

public interface FriendGroupDao {

	FriendGroup findFriendGroupById(int id);
	
	List<FriendGroup> findFriendGroupsbyUserID(int id);
		
	List<FriendGroup> findFriendGroupsByUsername(String username);
	
	List<FriendGroup> findFriendGroupsByUser(User user);
			
	void addFriendGroup(FriendGroup friendGroup);
	
	void deleteFriendGroup(FriendGroup friendGroup);
		
	void modifyFriendGroup(FriendGroup friendGroup);
	
}
