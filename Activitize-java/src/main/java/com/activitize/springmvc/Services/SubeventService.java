package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Subevent;
import com.activitize.springmvc.Models.Event;

public interface SubeventService {

	Subevent findById(int id);

	List<Subevent> findAllSubeventsForEvent(Event event);

	void createSubevent(Subevent subevent);

	void deleteSubevent(Subevent subevent);

	void editSubevent(Subevent subevent);

}
