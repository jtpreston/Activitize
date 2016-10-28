package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.FriendGroup;
import com.activitize.springmvc.Models.User;

@Repository("friendGroupDao")
public class FriendGroupDaoImpl extends AbstractDao<Integer, FriendGroup> implements FriendGroupDao {
	
	public FriendGroup findFriendGroupById(int id) {
		return getByKey(id);
	}
	
	public List<FriendGroup> findFriendGroupsbyUserID(int id) {
		return null;
	}

	@Override
	public List<FriendGroup> findFriendGroupsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FriendGroup> findFriendGroupsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addFriendGroup(FriendGroup friendGroup) {	
		
	}

	public void deleteFriendGroup(FriendGroup friendGroup) {
			
	}

	@Override
	public void modifyFriendGroup(FriendGroup friends) {
		
	}

}
