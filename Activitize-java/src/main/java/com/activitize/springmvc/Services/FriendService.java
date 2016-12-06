package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.User;

public interface FriendService {

	Friend findFriendById(int id);

	Friend findIfCurrentFriend(Friend friend);

	Friend findIfDuplicateFriendRequest(Friend friend);

	Friend findIfDuplicateAdd(Friend friend);

	List<Friend> findFriendsByUserID(int id);

	List<Friend> findFriendsByUsername(String username);

	List<User> findFriendsByUser(User user);

	void addFriend(Friend friend);

	void deleteFriend(Friend friend);

	void confirmFriend(Friend friend);

	void rejectFriend(Friend friend);

}
