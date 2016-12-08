package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.User;

@Repository("friendDao")
public class FriendDaoImpl extends AbstractDao<Integer, Friend> implements FriendDao {

	public Friend findFriendById(int id) {
		return getByKey(id);
	}

	public Friend findIfCurrentFriend(Friend friend) {
		boolean exists = (Long) getSession().createQuery("select count(*) from Friend where WHERE users_user_id = :users_user_id AND other_user_id = :other_user_id AND status = :status").setParameter("users_user_id", friend.getFriendId().getUsersUserId()).setParameter("other_user_id", friend.getFriendId().getOtherUserId()).setParameter("status", 1).uniqueResult() > 0;
		if (exists) {
			return new Friend();
		}
		else {
			return null;
		}
	}

	public Friend findIfDuplicateFriendRequest(Friend friend) {
		boolean exists = (Long) getSession().createQuery("select count(*) from Friend where WHERE users_user_id = :users_user_id AND other_user_id = :other_user_id AND status = :status").setParameter("users_user_id", friend.getFriendId().getUsersUserId()).setParameter("other_user_id", friend.getFriendId().getOtherUserId()).setParameter("status", 0).uniqueResult() > 0;
		if (exists) {
			return new Friend();
		}
		else {
			return null;
		}
	}

	public Friend findIfDuplicateAdd(Friend friend) {
		boolean exists = (Long) getSession().createQuery("select count(*) from Friend where WHERE users_user_id = :users_user_id AND other_user_id = :other_user_id").setParameter("users_user_id", friend.getFriendId().getUsersUserId()).setParameter("other_user_id", friend.getFriendId().getOtherUserId()).uniqueResult() > 0;
		if (exists) {
			return new Friend();
		}
		else {
			return null;
		}
	}

	public List<Friend> findFriendsByUserID(int id) {
		return null;
	}

	public List<Friend> findFriendsByUsername(String username) {	
		return null;
	}

	public List<User> findFriendsByUser(User user) {
		Query q = getSession().createSQLQuery("SELECT username, first_name, last_name, nickname FROM users WHERE user_id IN(SELECT other_user_id FROM friends WHERE users_user_id = ? AND status = ?) OR user_id IN(SELECT users_user_id FROM friends WHERE other_user_id = ? AND status = ?)");
		q.setParameter(0, user.getUserId());
		q.setParameter(1, 1);
		q.setParameter(2, user.getUserId());
		q.setParameter(3, 1);
		List result = q.list();
		return result;
	}

	public void addFriend(Friend friend) {
		SQLQuery insertQuery = getSession().createSQLQuery("" + "INSERT INTO friends(users_user_id,other_user_id,status,action_user_id)VALUES(?,?,?,?)");
		insertQuery.setParameter(0, friend.getFriendId().getUsersUserId());
		insertQuery.setParameter(1, friend.getFriendId().getOtherUserId());
		insertQuery.setParameter(2, friend.getStatus());
		insertQuery.setParameter(3, friend.getActionUserId());
		insertQuery.executeUpdate();
	}

	public void deleteFriend(Friend friend) {
		Query q = getSession().createSQLQuery("DELETE FROM friends WHERE users_user_id=:users_user_id AND other_user_id=:other_user_id").setParameter("users_user_id", friend.getFriendId().getUsersUserId()).setParameter("other_user_id", friend.getFriendId().getOtherUserId());
		q.executeUpdate();
	}

	public void confirmFriend(Friend friend) {
		Query q = getSession().createSQLQuery("UPDATE friends SET status = ?, action_user_id = ? WHERE users_user_id = ? AND other_user_id = ?");
		q.setParameter(0, friend.getStatus());
		q.setParameter(1, friend.getActionUserId());
		q.setParameter(2, friend.getFriendId().getUsersUserId());
		q.setParameter(3, friend.getFriendId().getOtherUserId());
		q.executeUpdate();
	}

	public void rejectFriend(Friend friend) {
		Query q = getSession().createSQLQuery("DELETE FROM friends WHERE users_user_id=:users_user_id AND other_user_id=:other_user_id").setParameter("users_user_id", friend.getFriendId().getUsersUserId()).setParameter("other_user_id", friend.getFriendId().getOtherUserId());
		q.executeUpdate();
	}

}
