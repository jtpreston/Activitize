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

	@Override
	public Event findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public List<Event> findAllEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

}
