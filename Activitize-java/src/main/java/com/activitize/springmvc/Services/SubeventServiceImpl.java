package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.CommentDao;
import com.activitize.springmvc.DAO.SubeventDao;
import com.activitize.springmvc.Models.Subevent;
import com.activitize.springmvc.Models.Event;

@Service("subeventService")
@Transactional
public class SubeventServiceImpl implements SubeventService {

	@Autowired
	private SubeventDao dao;

	public Subevent findById(int id) {
		return dao.findById(id);
	}

	public List<Subevent> findAllSubeventsForEvent(Event event) {
		return dao.findAllSubeventsForEvent(event);
	}

	public void createSubevent(Subevent subevent) {
		dao.createSubevent(subevent);
	}

	public void deleteSubevent(Subevent subevent) {
		dao.deleteSubevent(subevent);
	}

	public void editSubevent(Subevent subevent) {
		dao.editSubevent(subevent);
	}

}
