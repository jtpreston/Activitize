package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.User;
import com.activitize.springmvc.Models.UserHasEvent;

@Repository("userHasEventDao")
public class UserHasEventDaoImpl extends AbstractDao<Integer, UserHasEvent> implements UserHasEventDao {

	@Override
	public UserHasEvent findById(int id) {
		return getByKey(id);
	}

	@Override
	public List<UserHasEvent> findAllEventsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserHasEvent> findAllAdminEventsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserHasEvent> findAllGoingEventsByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createEvent(UserHasEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEvent(UserHasEvent event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editEvent(UserHasEvent event) {
		// TODO Auto-generated method stub
		
	}

}
