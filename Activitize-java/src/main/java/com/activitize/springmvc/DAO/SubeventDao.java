package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.Subevent;

public interface SubeventDao {

	Subevent findById(int id);

	List<Subevent> findAllSubeventsForEvent(Event event);

	void createSubevent(Subevent subevent);

	void deleteSubevent(Subevent subevent);

	void editSubevent(Subevent subevent);

}
