package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.Subevent;

@Repository("subeventDao")
public class SubeventDaoImpl extends AbstractDao<Integer, Subevent> implements SubeventDao {

	public Subevent findById(int id) {
		return getByKey(id);
	}

	public List<Subevent> findAllSubeventsForEvent(Event event) {
		return null;
	}

	public void createSubevent(Subevent subevent) {

	}

	public void deleteSubevent(Subevent subevent) {

	}

	public void editSubevent(Subevent subevent) {

	}

}
