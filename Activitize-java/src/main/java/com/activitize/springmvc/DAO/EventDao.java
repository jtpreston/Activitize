package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

public interface EventDao {

	Event findById(int id);
	
	Event findByEvent(Event event);

	List<Event> findAllEvents();

	void createEvent(Event event, User user);

	void deleteEvent(Event event, User user);

	void editEvent(Event event, User user);
	
	List<Event> getAllEventsForUser(User user);
	
}
