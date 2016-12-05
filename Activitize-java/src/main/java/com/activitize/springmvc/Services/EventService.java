package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

public interface EventService {

	Event findById(int id);

	Event findByEvent(Event event);

	boolean isEventUnique(Event event);

	boolean addUserToEvent(Event event, User user);

	boolean removeUserFromEvent(Event event, User user);

	boolean removeUserFromEventAfterConfirming(Event event, User user);

	boolean confirmUserIsGoingToEvent(Event event, User user);

	void createEvent(Event event, User user);

	void deleteEvent(Event event, User user);

	void editEvent(Event event, User user);

	List<Event> findAllEvents();

	List<Event> getAllEventsForUser(User user);

}
