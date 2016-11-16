package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.FriendDao;
import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.User;

@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {

	@Autowired
	private FriendDao dao;

	public Friend findFriendById(int id) {
		return dao.findFriendById(id);
	}

	public List<Friend> findFriendsbyUserID(int id) {
		return dao.findFriendsbyUserID(id);
	}

	public List<Friend> findFriendsByUsername(String username) {
		return dao.findFriendsByUsername(username);
	}

	public List<Friend> findFriendsByUser(User user) {
		return dao.findFriendsByUser(user);
	}

	public void addFriend(Friend friend) {
		dao.addFriend(friend);
	}

	public void deleteFriend(Friend friend) {
		dao.deleteFriend(friend);
	}
	
}
