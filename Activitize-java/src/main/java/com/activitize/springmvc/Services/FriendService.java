package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.User;

public interface FriendService {

	Friend findFriendById(int id);
	
	List<Friend> findFriendsbyUserID(int id);
		
	List<Friend> findFriendsByUsername(String username);
	
	List<Friend> findFriendsByUser(User user);
			
	void addFriend(Friend friend);
	
	void deleteFriend(Friend friend);
}
