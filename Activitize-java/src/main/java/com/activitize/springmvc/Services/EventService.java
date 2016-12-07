package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

public interface EventService {

	Event findById(int id);

	Event findByEvent(Event event);

	Event canUserBeRemoved(Event event, User user);

	boolean isEventUnique(Event event);

	boolean addUserToEvent(Event event, User user);

	boolean removeUserFromEvent(Event event, User user);

	boolean declineUserIsGoingToEvent(Event event, User user);

	boolean confirmUserIsGoingToEvent(Event event, User user);

	boolean doesRequestingUserHavePermission(Event event, User user);

	boolean favoriteAnEvent(Event event, User user);

	boolean unfavoriteAnEvent(Event event, User user);

	void createEvent(Event event, User user);

	void deleteEvent(Event event, User user);

	void editEvent(Event event, User user);

	List<Event> findAllEvents();

	List<Event> getAllEventsForUser(User user);

	List<Event> getAllEventFavoritesForUser(User user);

	List<Event> getAllPendingEventsForUser(User user);

	List<Event> getAllAcceptedEventsForUser(User user);

	List<User> getAllUsersForEvent(Event event, User user);

	List<User> getAllPendingUsersForEvent(Event event, User user);

	List<User> getAllAcceptedUsersForEvent(Event event, User user);

	List<User> getAllAdminsForEvent(Event event, User user);

	List<User> getAllNonAdminsForEvent(Event event, User user);

}
