package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Event;

public interface EventService {

	Event findById(int id);
	
	List<Event> findAllEvents();
	
	void createEvent(Event event);
	
	void deleteEvent(Event event);
	
	void editEvent(Event event);
}
