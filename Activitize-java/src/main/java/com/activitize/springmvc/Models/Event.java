package com.activitize.springmvc.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="events")
public class Event implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer event_id;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.event")
	private Set<UserHasEvent> userHasEvents = new HashSet<UserHasEvent>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	private Set<Comment> comments = new HashSet<Comment>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	private Set<Subevent> subevents = new HashSet<Subevent>(0);

	@NotNull
	@Size(max = 256)
	@Column(name = "event_name", nullable = false)
	private String event_name;

	@NotNull
	@Column(name = "event_start", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private DateTime event_start;

	@NotNull
	@Column(name = "event_end", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private DateTime event_end;

	@Column(name = "description", nullable = true)
	private String description;

	@NotNull
	@Size(max = 256)
	@Column(name = "location", nullable = false)
	private String location;

	@NotNull
	@Column(name = "private", nullable = false)
	private boolean priv = false;

	@Size(max = 256)
	@Column(name = "path_to_event_picture", nullable = true)
	private String path_to_event_picture;

	@NotNull
	@Column(name = "subevent", nullable = false)
	private boolean subevent = false;

	@NotNull
	@Size(max=256)   
	@Column(name = "creator", nullable = false)
	private String creator;

	public Event() {

	}

	public Event(Integer event_id, String event_name, DateTime event_start, DateTime event_end, String description, String location, 
			boolean priv, String path_to_event_picture, boolean subevent, 
			Set<Comment> comments, Set<UserHasEvent> userHasEvents, Set<Subevent> subevents, String creator) {
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_start = event_start;
		this.event_end = event_end;
		this.description = description;
		this.location = location;
		this.priv = priv;
		this.path_to_event_picture = path_to_event_picture;
		this.subevent = subevent;
		this.comments = comments;
		this.userHasEvents = userHasEvents;
		this.subevents = subevents;
		this.creator = creator;
	}

	public Integer getEventId() {
		return event_id;
	}

	public void setEventId(Integer event_id) {
		this.event_id = event_id;
	}

	public Set<Comment> getComments() {
		return comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}

	public Set<UserHasEvent> getUserHasEvents() {
		return userHasEvents;
	}

	public void setUserHasEvents(Set<UserHasEvent> userHasEvents) {
		this.userHasEvents = userHasEvents;
	}

	public Set<Subevent> getSubevents() {
		return subevents;
	}

	public void setSubevents(Set<Subevent> subevents) {
		this.subevents = subevents;
	}

	public String getEventName() {
		return event_name;
	}

	public void setEventName(String event_name) {
		this.event_name = event_name;
	}

	public DateTime getEventStart() {
		return event_start;
	}

	public void setEventStart(DateTime event_start) {
		this.event_start = event_start;
	}

	public DateTime getEventEnd() {
		return event_end;
	}

	public void setEventEnd(DateTime event_end) {
		this.event_end = event_end;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean getPriv() {
		return priv;
	}

	public void setPriv(boolean priv) {
		this.priv = priv;
	}

	public String getPathToEventPicture() {
		return path_to_event_picture;
	}

	public void setPathToEventPicture(String path_to_event_picture) {
		this.path_to_event_picture = path_to_event_picture;
	}

	public boolean getSubevent() {
		return subevent;
	}

	public void setSubevent(boolean subevent) {
		this.subevent = subevent;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((event_id == null) ? 0 : event_id.hashCode());
		result = prime * result + ((event_name == null) ? 0 : event_name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Event))
			return false;
		Event other = (Event) obj;
		if (event_id == null) {
			if (other.event_id != null)
				return false;
		} else if (!event_id.equals(other.event_id))
			return false;
		if (event_name == null) {
			if (other.event_name != null)
				return false;
		} else if (!event_name.equals(other.event_name))
			return false;
		return true;
	}

}	
