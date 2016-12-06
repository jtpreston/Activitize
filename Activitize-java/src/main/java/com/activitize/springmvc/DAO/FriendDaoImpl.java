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
		boolean exists = (Long) getSession().createQuery("select count(*) from Friend where WHERE users_user_id = :users_user_id AND other_user_id = :other_user_id AND status = :status").setParameter("users_user_id", friend.getUsersUserId()).setParameter("other_user_id", friend.getOtherUserId()).setParameter("status", 1).uniqueResult() > 0;
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
		Query q = getSession().createSQLQuery("SELECT username, first_name, last_name, nickname FROM users INNER JOIN friends ON friends.users_user_id = users.user_id WHERE friends.users_user_id = ? AND friends.status = ?");
		q.setParameter(0, user.getUserId());
		q.setParameter(1, 1);
		List result = q.list();
		return result;
	}

	public void addFriend(Friend friend) {
		SQLQuery insertQuery = getSession().createSQLQuery("" + "INSERT INTO friends(users_user_id,other_user_id,status)VALUES(?,?,?)");
		insertQuery.setParameter(0, friend.getUsersUserId());
		insertQuery.setParameter(1, friend.getOtherUserId());
		insertQuery.setParameter(2, friend.getStatus());
		insertQuery.executeUpdate();
	}

	public void deleteFriend(Friend friend) {
		Query q = getSession().createSQLQuery("DELETE FROM friends WHERE users_user_id=:users_user_id AND other_user_id=:other_user_id").setParameter("users_user_id", friend.getUsersUserId()).setParameter("other_user_id", friend.getOtherUserId());
		q.executeUpdate();
		q = getSession().createSQLQuery("UPDATE users SET number_of_friends = number_of_friends - 1 WHERE user_id = ? OR user_id = ?");
		q.setParameter(0, friend.getUsersUserId());
		q.setParameter(1, friend.getOtherUserId());
	}

	public void confirmFriend(Friend friend) {
		Query q = getSession().createSQLQuery("UPDATE friends SET status = ? WHERE users_user_id = ? AND other_user_id = ?");
		q.setParameter(0, friend.getStatus());
		q.setParameter(1, friend.getUsersUserId());
		q.setParameter(2, friend.getOtherUserId());
		q.executeUpdate();
		q = getSession().createSQLQuery("UPDATE users SET number_of_friends = number_of_friends + 1 WHERE user_id = ? OR user_id = ?");
		q.setParameter(0, friend.getUsersUserId());
		q.setParameter(1, friend.getOtherUserId());
	}

	public void rejectFriend(Friend friend) {
		Query q = getSession().createSQLQuery("DELETE FROM friends WHERE users_user_id=:users_user_id AND other_user_id=:other_user_id").setParameter("users_user_id", friend.getUsersUserId()).setParameter("other_user_id", friend.getOtherUserId());
		q.executeUpdate();
	}

}
