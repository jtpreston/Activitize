package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.FriendGroupDao;
import com.activitize.springmvc.DAO.UserDao;
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

	@Override
	public List<FriendGroup> findFriendGroupsByUsername(String username) {
		return dao.findFriendGroupsByUsername(username);
	}

	@Override
	public List<FriendGroup> findFriendGroupsByUser(User user) {
		return dao.findFriendGroupsByUser(user);
	}

	public void addFriendGroup(FriendGroup friendGroup) {
		dao.addFriendGroup(friendGroup);
	}

	public void deleteFriendGroup(FriendGroup friendGroup) {
		dao.deleteFriendGroup(friendGroup);
	}

	@Override
	public void modifyFriendGroup(FriendGroup friends) {
		dao.modifyFriendGroup(friends);
	}
	
}
