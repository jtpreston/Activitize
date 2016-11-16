package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.Event;

public interface EventDao {

	Event findById(int id);

	List<Event> findAllEvents();

	void createEvent(Event event);

	void deleteEvent(Event event);

	void editEvent(Event event);
	
}
