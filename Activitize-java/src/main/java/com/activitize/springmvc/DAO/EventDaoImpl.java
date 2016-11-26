package com.activitize.springmvc.DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

@Repository("eventDao")
public class EventDaoImpl extends AbstractDao<Integer, Event> implements EventDao {

	public Event findById(int id) {
		return getByKey(id);
	}
	
	public Event findByEvent(Event event) {
		Criteria crit = getSession().createCriteria(Event.class);
		crit.add(Example.create(event));
		Event tempEvent = (Event)crit.uniqueResult();
		return tempEvent;
	}

	public List<Event> findAllEvents() {	
		return null;
	}

	public void createEvent(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		persist(event);
		Criteria otherCrit = getSession().createCriteria(Event.class);
		otherCrit.add(Example.create(event));
		Event tempEvent = (Event)otherCrit.uniqueResult();
		SQLQuery insertQuery = getSession().createSQLQuery("" + "INSERT INTO users_has_events(users_user_id,events_event_id,favorite,admin,going)VALUES(?,?,?,?,?)");
		insertQuery.setParameter(0, userTemp.getUserId());
		insertQuery.setParameter(1, tempEvent.getEventId());
		insertQuery.setParameter(2, 0);
		insertQuery.setParameter(3, 1);
		insertQuery.setParameter(4, 1);
		insertQuery.executeUpdate();
	}

	public void deleteEvent(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
	}

	public void editEvent(Event event, User user) {	
		
	}

}
