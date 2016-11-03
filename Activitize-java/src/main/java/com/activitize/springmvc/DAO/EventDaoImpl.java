package com.activitize.springmvc.DAO;

import java.util.List;

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
	
	public void createEvent(Event event) {	
		persist(event);
	}
	
	public void deleteEvent(Event event) {	
		
	}
	
	public void editEvent(Event event) {	
		
	}

}
