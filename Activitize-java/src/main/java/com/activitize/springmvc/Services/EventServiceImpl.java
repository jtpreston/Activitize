package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.EventDao;
import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
	private EventDao dao;

	public Event findById(int id) {
		return dao.findById(id);
	}

	public Event findByEvent(Event event) {
		return dao.findByEvent(event);
	}

	public List<Event> findAllEvents() {
		return dao.findAllEvents();
	}

	public void createEvent(Event event, User user) {
		dao.createEvent(event, user);
	}

	public void deleteEvent(Event event, User user) {
		dao.deleteEvent(event, user);
	}

	public void editEvent(Event event, User user) {
		dao.editEvent(event, user);
	}

	public boolean isEventUnique(Event event) {
		Event tempEvent = findByEvent(event);
		if (tempEvent == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public List<Event> getAllEventsForUser(User user) {
		return dao.getAllEventsForUser(user);
	}

}
