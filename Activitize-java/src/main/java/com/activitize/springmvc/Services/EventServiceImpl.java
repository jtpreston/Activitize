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

	public List<User> getAllAdminsForEvent(Event event, User user) {
		return dao.getAllAdminsForEvent(event, user);
	}

	public List<User> getAllNonAdminsForEvent(Event event, User user) {
		return dao.getAllNonAdminsForEvent(event, user);
	}

	public List<Event> getAllEventFavoritesForUser(User user) {
		return dao.getAllEventFavoritesForUser(user);
	}

	public List<Event> getAllPendingEventsForUser(User user) {
		return dao.getAllPendingEventsForUser(user);
	}

	public List<Event> getAllAcceptedEventsForUser(User user) {
		return dao.getAllAcceptedEventsForUser(user);
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

	public List<User> getAllUsersForEvent(Event event, User user) {
		return dao.getAllUsersForEvent(event, user);
	}

	public boolean addUserToEvent(Event event, User user) {
		return dao.addUserToEvent(event, user);
	}

	public boolean removeUserFromEvent(Event event, User user) {
		return dao.removeUserFromEvent(event, user);
	}

	public boolean declineUserIsGoingToEvent(Event event, User user) {
		return dao.declineUserIsGoingToEvent(event, user);
	}

	public boolean confirmUserIsGoingToEvent(Event event, User user) {
		return dao.confirmUserIsGoingToEvent(event, user);
	}

	public boolean doesRequestingUserHavePermission(Event event, User user) {
		return dao.doesRequestingUserHavePermission(event, user);
	}

	public boolean favoriteAnEvent(Event event, User user) {
		return dao.favoriteAnEvent(event, user);
	}

	public boolean unfavoriteAnEvent(Event event, User user) {
		return dao.unfavoriteAnEvent(event, user);
	}

	public Event canUserBeRemoved(Event event, User user) {
		return dao.canUserBeRemoved(event, user);
	}

}
