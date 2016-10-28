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
	
	public UserHasEvent findById(int id) {
		return getByKey(id);
	}

	public List<UserHasEvent> findAllEventsByUser(User user) {		
		return null;
	}

	public List<UserHasEvent> findAllAdminEventsByUser(User user) {		
		return null;
	}

	public List<UserHasEvent> findAllGoingEventsByUser(User user) {		
		return null;
	}

	public void createEvent(UserHasEvent userHasEvent) {
				
	}

	public void deleteEvent(UserHasEvent userHasEvent) {
				
	}

	public void editEvent(UserHasEvent userHasEvent) {
		
	}

}
