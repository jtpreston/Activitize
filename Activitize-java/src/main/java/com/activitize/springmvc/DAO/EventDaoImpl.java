package com.activitize.springmvc.DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

@Repository("eventDao")
public class EventDaoImpl extends AbstractDao<Integer, Event> implements EventDao {

	private static final Logger logger = LogManager.getLogger(EventDaoImpl.class);

	public Event findById(int id) {
		return getByKey(id);
	}

	public Event findByEvent(Event event) {
		Criteria crit = getSession().createCriteria(Event.class);
		crit.add(Example.create(event));
		Event tempEvent = (Event)crit.uniqueResult();
		return tempEvent;
	}

	public List<Event> findAllEvents() {	
		return null;
	}

	public List<Event> getAllEventsForUser(User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		Query q = getSession().createSQLQuery("SELECT * FROM events INNER JOIN users_has_events ON events.event_id = users_has_events.events_event_id WHERE users_has_events.users_user_id = ?");
		q.setParameter(0, userTemp.getUserId());
		List result = q.list();
		return result;
	}

	public void createEvent(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		persist(event);
		Criteria otherCrit = getSession().createCriteria(Event.class);
		otherCrit.add(Example.create(event));
		Event tempEvent = (Event)otherCrit.uniqueResult();
		SQLQuery insertQuery = getSession().createSQLQuery("" + "INSERT INTO users_has_events(users_user_id,events_event_id,favorite,admin,going)VALUES(?,?,?,?,?)");
		insertQuery.setParameter(0, userTemp.getUserId());
		insertQuery.setParameter(1, tempEvent.getEventId());
		insertQuery.setParameter(2, 0);
		insertQuery.setParameter(3, 1);
		insertQuery.setParameter(4, 1);
		insertQuery.executeUpdate();
	}

	public void deleteEvent(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
	}

	public void editEvent(Event event, User user) {
		Event tempEvent = findById(event.getEventId());
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		SQLQuery query = getSession().createSQLQuery("SELECT COUNT(*) FROM users_has_events WHERE events_event_id = ? AND users_user_id = ? AND admin = ?");
		query.setParameter(0, event.getEventId());
		query.setParameter(1, userTemp.getUserId());
		query.setParameter(2, 1);
		Object countobj = query.list().get(0);
		int count = ((Number) countobj).intValue();
		if (count == 0) {
			logger.info("User: " + user.toString() + " was not allowed to edit this event");
			return;
		}
		//All valid fields are being updated
		if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, event_end = :event_end, description = :description, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name are being updated
		else if (event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, event_end = :event_end, description = :description, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start are being updated
		else if (event.getEventName() != null && event.getEventEnd() != null && event.getDescription() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_end = :event_end, description = :description, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event end are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getDescription() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, description = :description, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except description are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, event_end = :event_end, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except location are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, event_end = :event_end, description = :description, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except priv are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, event_end = :event_end, description = :description, location = :location where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name and event start are being updated
		else if (event.getEventEnd() != null && event.getDescription() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_end = :event_end, description = :description, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name and event end are being updated
		else if (event.getEventStart() != null && event.getDescription() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, description = :description, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name and description are being updated
		else if (event.getEventStart() != null && event.getEventEnd() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, event_end = :event_end, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name and location are being updated
		else if (event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, event_end = :event_end, description = :description, private = :priv where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name and priv are being updated
		else if (event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, event_end = :event_end, description = :description, location = :location where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start and event end are being updated
		else if (event.getEventName() != null && event.getDescription() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, description = :description, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start and description are being updated
		else if (event.getEventName() != null && event.getEventEnd() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_end = :event_end, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start and location are being updated
		else if (event.getEventName() != null && event.getEventEnd() != null && event.getDescription() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_end = :event_end, description = :description, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start and priv are being updated
		else if (event.getEventName() != null && event.getEventEnd() != null && event.getDescription() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_end = :event_end, description = :description, location = :location where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event end and description are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event end and location are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getDescription() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, description = :description, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("description", event.getDescription());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event end and priv are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getDescription() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, description = :description, location = :location where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except description and location are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, event_end = :event_end, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except description and priv are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, event_end = :event_end, location = :location where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except location and priv are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, event_end = :event_end, description = :description where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except description, location, and priv are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, event_end = :event_end where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event end, description, and location are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event end, description, and priv are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, location = :location where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event end, location, and priv are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start, event_end = :event_end, description = :description, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("description", event.getDescription());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, description, and location are being updated
		else if (event.getEventName() != null && event.getEventEnd() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_end = :event_end, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, description, and priv are being updated
		else if (event.getEventName() != null && event.getEventEnd() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_end = :event_end, location = :location where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, location, and priv are being updated
		else if (event.getEventName() != null && event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_end = :event_end, description = :description where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, event end, and description are being updated
		else if (event.getEventName() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, event end, and location are being updated
		else if (event.getEventName() != null && event.getDescription() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, description = :description, location = :location where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("description", event.getDescription());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, event end, and priv are being updated
		else if (event.getEventName() != null && event.getDescription() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, description = :description, location = :location where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, and event end are being updated
		else if (event.getDescription() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set description = :description, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, and description are being updated
		else if (event.getEventEnd() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_end = :event_end, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event end, and description are being updated
		else if (event.getEventStart() != null && event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, location = :location, private = :priv where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, and location are being updated
		else if (event.getEventEnd() != null && event.getDescription() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_end = :event_end, description = :description, private = :priv where event_id = :event_id");
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event end, and location are being updated
		else if (event.getEventStart() != null && event.getDescription() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, description = :description, private = :priv where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("description", event.getDescription());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, and priv are being updated
		else if (event.getEventEnd() != null && event.getDescription() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_end = :event_end, description = :description, location = :location where event_id = :event_id");
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event end, and priv are being updated
		else if (event.getEventStart() != null && event.getDescription() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, description = :description, location = :location where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, location, and priv are being updated
		else if (event.getEventStart() != null && event.getEventEnd() != null && event.getDescription() != null) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, event_end = :event_end, description = :description where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, description, and priv are being updated
		else if (event.getEventStart() != null && event.getEventEnd() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, event_end = :event_end, location = :location where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, description, and location are being updated
		else if (event.getEventStart() != null && event.getEventEnd() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, event_end = :event_end, private = :priv where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event end, description, location, and priv are being updated
		else if (event.getEventName() != null && event.getEventStart() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_start = :event_start where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, description, location, and priv are being updated
		else if (event.getEventName() != null && event.getEventEnd() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, event_end = :event_end where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, event end, location, and priv are being updated
		else if (event.getEventName() != null && event.getDescription() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, description = :description where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("description", event.getDescription());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, event end, description, and priv are being updated
		else if (event.getEventName() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, location = :location where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event start, event end, description, and location are being updated
		else if (event.getEventName() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_name = :event_name, private = :priv where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event end, location, and priv are being updated
		else if (event.getEventStart() != null && event.getDescription() != null) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, description = :description where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("description", event.getDescription());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, description, location, and priv are being updated
		else if (event.getEventStart() != null && event.getEventEnd() != null) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, event_end = :event_end where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event end, description, and priv are being updated
		else if (event.getEventStart() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, location = :location where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event end, description, and location are being updated
		else if (event.getEventStart() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_start = :event_start, private = :priv where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, location, and priv are being updated
		else if (event.getEventEnd() != null && event.getDescription() != null) {
			Query q = getSession().createQuery("update Event set event_end = :event_end, description = :description where event_id = :event_id");
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("description", event.getDescription());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, description, and priv are being updated
		else if (event.getEventEnd() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set event_end = :event_end, location = :location where event_id = :event_id");
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, description, and location are being updated
		else if (event.getEventEnd() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set event_end = :event_end, private = :priv where event_id = :event_id");
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, event end, and priv are being updated
		else if (event.getDescription() != null && event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set description = :description, location = :location where event_id = :event_id");
			q.setParameter("description", event.getDescription());
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, event end, and location are being updated
		else if (event.getDescription() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set description = :description, private = :priv where event_id = :event_id");
			q.setParameter("description", event.getDescription());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All valid fields except event name, event start, event end, and description are being updated
		else if (event.getLocation() != null && event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set location = :location, private = :priv where event_id = :event_id");
			q.setParameter("location", event.getLocation());
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//Only updating event name
		else if (event.getEventName() != null) {
			Query q = getSession().createQuery("update Event set event_name = :event_name where event_id = :event_id");
			q.setParameter("event_name", event.getEventName());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//Only updating event start
		else if (event.getEventStart() != null) {
			Query q = getSession().createQuery("update Event set event_start = :event_start where event_id = :event_id");
			q.setParameter("event_start", event.getEventStart());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//Only updating event end
		else if (event.getEventEnd() != null) {
			Query q = getSession().createQuery("update Event set event_end = :event_end where event_id = :event_id");
			q.setParameter("event_end", event.getEventEnd());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//Only updating description
		else if (event.getDescription() != null) {
			Query q = getSession().createQuery("update Event set description = :description where event_id = :event_id");
			q.setParameter("description", event.getDescription());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//Only updating location
		else if (event.getLocation() != null) {
			Query q = getSession().createQuery("update Event set location = :location where event_id = :event_id");
			q.setParameter("location", event.getLocation());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//Only updating priv
		else if (event.getPriv() != tempEvent.getPriv()) {
			Query q = getSession().createQuery("update Event set private = :priv where event_id = :event_id");
			q.setParameter("priv", event.getPriv());
			q.setParameter("event_id", event.getEventId());
			q.executeUpdate();
		}
		//All fields were null
		else {
			logger.error("Invalid request! Nothing was received to update!");
		}
	}

	public boolean addUserToEvent(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		SQLQuery query = getSession().createSQLQuery("SELECT COUNT(*) FROM users_has_events WHERE events_event_id = ? AND users_user_id = ?");
		query.setParameter(0, event.getEventId());
		query.setParameter(1, userTemp.getUserId());
		Object countobj = query.list().get(0);
		int count = ((Number) countobj).intValue();
		if (count > 0) {
			logger.info("User: " + user.toString() + " has already been added to this event");
			return false;
		}
		SQLQuery insertQuery = getSession().createSQLQuery("" + "INSERT INTO users_has_events(users_user_id,events_event_id,favorite,admin,going)VALUES(?,?,?,?,?)");
		insertQuery.setParameter(0, userTemp.getUserId());
		insertQuery.setParameter(1, event.getEventId());
		insertQuery.setParameter(2, 0);
		insertQuery.setParameter(3, 0);
		insertQuery.setParameter(4, 0);
		insertQuery.executeUpdate();
		return true;
	}

	public boolean removeUserFromEvent(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		SQLQuery query = getSession().createSQLQuery("SELECT COUNT(*) FROM users_has_events WHERE events_event_id = ? AND users_user_id = ?");
		query.setParameter(0, event.getEventId());
		query.setParameter(1, userTemp.getUserId());
		Object countobj = query.list().get(0);
		int count = ((Number) countobj).intValue();
		if (count == 0) {
			logger.info("User: " + user.toString() + " has already been removed from this event");
			return false;
		}
		Query q = getSession().createSQLQuery("DELETE FROM users_has_events WHERE events_event_id = ? AND users_user_id = ?");
		q.setParameter(0, event.getEventId());
		q.setParameter(1, userTemp.getUserId());
		q.executeUpdate();
		return true;
	}

	public boolean removeUserFromEventAfterConfirming(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		SQLQuery query = getSession().createSQLQuery("SELECT COUNT(*) FROM users_has_events WHERE events_event_id = ? AND users_user_id = ?");
		query.setParameter(0, event.getEventId());
		query.setParameter(1, userTemp.getUserId());
		Object countobj = query.list().get(0);
		int count = ((Number) countobj).intValue();
		if (count == 0) {
			logger.info("User: " + user.toString() + " has already been removed from this event");
			return false;
		}
		Query q = getSession().createSQLQuery("DELETE FROM users_has_events WHERE events_event_id = ? AND users_user_id = ?");
		q.setParameter(0, event.getEventId());
		q.setParameter(1, userTemp.getUserId());
		q.executeUpdate();
		q = getSession().createSQLQuery("UPDATE events SET number_going = ? WHERE event_id = ?");
		q.setParameter(0, event.getNumberGoing() - 1);
		q.setParameter(1, event.getEventId());
		q.executeUpdate();
		return true;
	}

	public boolean confirmUserIsGoingToEvent(Event event, User user) {
		Criteria crit = getSession().createCriteria(User.class);
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		SQLQuery query = getSession().createSQLQuery("SELECT COUNT(*) FROM users_has_events WHERE events_event_id = ? AND users_user_id = ? AND going = ?");
		query.setParameter(0, event.getEventId());
		query.setParameter(1, userTemp.getUserId());
		query.setParameter(2, 0);
		Object countobj = query.list().get(0);
		int count = ((Number) countobj).intValue();
		if (count != 0) {
			logger.info("User: " + user.toString() + " has already confirmed for this event");
			return false;
		}
		Query q = getSession().createSQLQuery("UPDATE users_has_events SET going = ? WHERE events_event_id = ? AND users_user_id = ?");
		q.setParameter(0, 1);
		q.setParameter(1, event.getEventId());
		q.setParameter(2, userTemp.getUserId());
		q.executeUpdate();
		q = getSession().createSQLQuery("UPDATE events SET number_going = ? WHERE event_id = ?");
		q.setParameter(0, event.getNumberGoing() + 1);
		q.setParameter(1, event.getEventId());
		q.executeUpdate();
		return true;
	}

}
