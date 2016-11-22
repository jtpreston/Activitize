package com.activitize.springmvc.DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

@Repository("eventDao")
public class EventDaoImpl extends AbstractDao<Integer, Event> implements EventDao {

	public Event findById(int id) {
		return getByKey(id);
	}

	public List<Event> findAllEvents() {	
		return null;
	}

	public void createEvent(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		persist(event);
	}

	public void deleteEvent(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
	}

	public void editEvent(Event event, User user) {	
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
	}

}
