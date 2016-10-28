package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.User;

@Repository("friendDao")
public class FriendDaoImpl extends AbstractDao<Integer, Friend> implements FriendDao {

	@Override
	public Friend findFriendById(int id) {
		return getByKey(id);
	}

	@Override
	public List<Friend> findFriendsbyUserID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Friend> findFriendsByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Friend> findFriendsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addFriend(Friend friend) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFriend(Friend friend) {
		// TODO Auto-generated method stub
		
	}


}
