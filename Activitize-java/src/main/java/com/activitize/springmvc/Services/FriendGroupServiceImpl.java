package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.FriendGroupDao;
import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.FriendGroup;
import com.activitize.springmvc.Models.User;

@Service("friendGroupService")
@Transactional
public class FriendGroupServiceImpl implements FriendGroupService {

	@Autowired
    private FriendGroupDao dao;

	public FriendGroup findFriendGroupById(int id) {
		return dao.findFriendGroupById(id);
	}

	public List<FriendGroup> findFriendGroupsbyUserID(int id) {
		return dao.findFriendGroupsbyUserID(id);
	}

	public List<Friend> findFriendGroupsByUsername(String username) {
		return dao.findFriendGroupsByUsername(username);
	}

	public List<Friend> findFriendGroupsByUser(User user) {
		return dao.findFriendGroupsByUser(user);
	}

	public void addFriendGroup(FriendGroup friendGroup) {
		dao.addFriendGroup(friendGroup);
	}

	public void deleteFriendGroup(FriendGroup friendGroup) {
		dao.deleteFriendGroup(friendGroup);
	}

	public void addToFriendGroup(FriendGroup friendGroup, Friend friend) {
		dao.addToFriendGroup(friendGroup, friend);
	}
	
}
