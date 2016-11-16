package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.DAO.UserHasEventDao;
import com.activitize.springmvc.Models.User;
import com.activitize.springmvc.Models.UserHasEvent;

@Service("userHasEventService")
@Transactional
public class UserHasEventServiceImpl implements UserHasEventService {

	@Autowired
	private UserHasEventDao dao;

	public UserHasEvent findById(int id) {
		return dao.findById(id);
	}

	public List<UserHasEvent> findAllEventsByUser(User user) {
		return dao.findAllEventsByUser(user);
	}

	public List<UserHasEvent> findAllAdminEventsByUser(User user) {
		return dao.findAllAdminEventsByUser(user);
	}

	public List<UserHasEvent> findAllGoingEventsByUser(User user) {
		return dao.findAllGoingEventsByUser(user);
	}

	public void createEvent(UserHasEvent userHasEvent) {
		dao.createEvent(userHasEvent);
	}

	public void deleteEvent(UserHasEvent userHasEvent) {
		dao.deleteEvent(userHasEvent);
	}

	public void editEvent(UserHasEvent userHasEvent) {
		dao.editEvent(userHasEvent);
	}
	
}
