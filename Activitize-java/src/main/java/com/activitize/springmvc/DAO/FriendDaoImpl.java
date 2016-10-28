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

	public Friend findFriendById(int id) {
		return getByKey(id);
	}

	public List<Friend> findFriendsbyUserID(int id) {
		return null;
	}

	public List<Friend> findFriendsByUsername(String username) {	
		return null;
	}

	public List<Friend> findFriendsByUser(User user) {	
		return null;
	}

	public void addFriend(Friend friend) {
			
	}

	public void deleteFriend(Friend friend) {
		
	}

}
