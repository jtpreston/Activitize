package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.FriendGroup;
import com.activitize.springmvc.Models.User;

public interface FriendGroupDao {

	FriendGroup findFriendGroupById(int id);
	
	List<FriendGroup> findFriendGroupsbyUserID(int id);
		
	List<Friend> findFriendGroupsByUsername(String username);
	
	List<Friend> findFriendGroupsByUser(User user);
			
	void addFriendGroup(FriendGroup friends);
	
	void deleteFriendGroup(FriendGroup friends);
	
	void addToFriendGroup(FriendGroup friends, Friend friend);
}
