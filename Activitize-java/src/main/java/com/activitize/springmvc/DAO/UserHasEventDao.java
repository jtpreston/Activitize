package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.User;
import com.activitize.springmvc.Models.UserHasEvent;

public interface UserHasEventDao {

	UserHasEvent findById(int id);

	List<UserHasEvent> findAllEventsByUser(User user);

	List<UserHasEvent> findAllAdminEventsByUser(User user);

	List<UserHasEvent> findAllGoingEventsByUser(User user);

	void createEvent(UserHasEvent userHasEvent);

	void deleteEvent(UserHasEvent userHasEvent);

	void editEvent(UserHasEvent userHasEvent);
	
}
