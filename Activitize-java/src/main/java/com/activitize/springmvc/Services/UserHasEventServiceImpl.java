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

	@Override
	public UserHasEvent findById(int id) {
		return dao.findById(id);
	}

	@Override
	public List<UserHasEvent> findAllEventsByUser(User user) {
		return dao.findAllEventsByUser(user);
	}

	@Override
	public List<UserHasEvent> findAllAdminEventsByUser(User user) {
		return dao.findAllAdminEventsByUser(user);
	}

	@Override
	public List<UserHasEvent> findAllGoingEventsByUser(User user) {
		return dao.findAllGoingEventsByUser(user);
	}

	@Override
	public void createEvent(UserHasEvent event) {
		dao.createEvent(event);
	}

	@Override
	public void deleteEvent(UserHasEvent event) {
		dao.deleteEvent(event);
	}

	@Override
	public void editEvent(UserHasEvent event) {
		dao.editEvent(event);
	}
	
}
