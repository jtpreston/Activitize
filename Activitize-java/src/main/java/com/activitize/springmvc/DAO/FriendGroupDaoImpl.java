package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.FriendGroup;
import com.activitize.springmvc.Models.User;

@Repository("friendGroupDao")
public class FriendGroupDaoImpl extends AbstractDao<Integer, FriendGroup> implements FriendGroupDao{

	@Override
	public FriendGroup findFriendGroupById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public List<FriendGroup> findFriendGroupsbyUserID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Friend> findFriendGroupssByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Friend> findFriendGroupssByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFriendGroup(FriendGroup friends) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFriendGroup(FriendGroup friends) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addToFriendGroup(FriendGroup friends, Friend friend) {
		// TODO Auto-generated method stub
		
	}

}
