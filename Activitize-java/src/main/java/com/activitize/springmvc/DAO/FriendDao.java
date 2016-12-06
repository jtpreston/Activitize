package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.User;

public interface FriendDao {

	Friend findFriendById(int id);

	Friend findIfCurrentFriend(Friend friend);

	List<Friend> findFriendsByUserID(int id);

	List<Friend> findFriendsByUsername(String username);

	List<User> findFriendsByUser(User user);

	void addFriend(Friend friend);

	void deleteFriend(Friend friend);

	void confirmFriend(Friend friend);

	void rejectFriend(Friend friend);

}
