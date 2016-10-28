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

	@Override
	public FriendGroup findFriendGroupById(int id) {
		return dao.findFriendGroupById(id);
	}

	@Override
	public List<FriendGroup> findFriendGroupsbyUserID(int id) {
		return dao.findFriendGroupsbyUserID(id);
	}

	@Override
	public List<Friend> findFriendGroupsByUsername(String username) {
		return dao.findFriendGroupsByUsername(username);
	}

	@Override
	public List<Friend> findFriendGroupsByUser(User user) {
		return dao.findFriendGroupsByUser(user);
	}

	@Override
	public void addFriendGroup(FriendGroup friends) {
		dao.addFriendGroup(friends);
	}

	@Override
	public void deleteFriendGroup(FriendGroup friends) {
		dao.deleteFriendGroup(friends);
	}

	@Override
	public void addToFriendGroup(FriendGroup friends, Friend friend) {
		dao.addToFriendGroup(friends, friend);
	}
	
}
