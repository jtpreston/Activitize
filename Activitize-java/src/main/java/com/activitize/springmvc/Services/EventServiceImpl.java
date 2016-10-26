package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.EventDao;
import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.Event;

@Service("eventService")
@Transactional
public class EventServiceImpl implements EventService {

	@Autowired
    private EventDao dao;
	
}
