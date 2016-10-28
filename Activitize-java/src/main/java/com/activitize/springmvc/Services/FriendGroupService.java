package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.FriendGroup;
import com.activitize.springmvc.Models.User;

public interface FriendGroupService {

	FriendGroup findFriendGroupById(int id);
	
	List<FriendGroup> findFriendGroupsbyUserID(int id);
		
	List<Friend> findFriendGroupsByUsername(String username);
	
	List<Friend> findFriendGroupsByUser(User user);
			
	void addFriendGroup(FriendGroup friendGroup);
	
	void deleteFriendGroup(FriendGroup friendGroup);
	
	void addToFriendGroup(FriendGroup friendGroup, Friend friend);
	
}
