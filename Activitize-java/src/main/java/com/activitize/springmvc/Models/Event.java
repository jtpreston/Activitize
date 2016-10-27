package com.activitize.springmvc.Models;

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

@Entity
@Table(name="events")
public class Event {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	private int event_id;
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
 	private Set<User> users = new HashSet<User>(0);
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
	private Set<Comment> comments = new HashSet<Comment>(0);
	@NotNull
	@Size(max = 256)
	@Column(name = "event_name", nullable = false)
	private String event_name;
	@NotNull
	@Column(name = "event_start", nullable = false)
	private DateTime event_start;
	@Column(name = "event_end", nullable = true)	
	private DateTime event_end;
	@Column(name = "description", nullable = true)
	private String description;
	@NotNull
	@Size(max = 256)
	@Column(name = "location", nullable = false)
	private String location;
	@NotNull
	@Column(name = "priv", nullable = false)
	private boolean priv;
	@NotNull
	@Column(name = "name_of_comments", nullable = false)
	private int number_of_comments = 0;
	@Size(max = 256)
	@Column(name = "path_to_event_picture", nullable = true)
	private String path_to_event_picture;
	@NotNull
	@Column(name = "number_going", nullable = false)
	private int number_going = 1;
	@NotNull
	@Column(name = "number_not_going", nullable = false)
	private int number_not_going = 0;
	@NotNull
	@Column(name = "subevent", nullable = false)
	private boolean subevent;
	@Column(name = "subevent_parent_id", nullable = true)
	private int subevent_parent_id;
	@Column(name = "friend_group_id", nullable = true)
	private int friend_group_id;
	
	public Event() {
		
	}
	
	public Event(int event_id, String event_name, DateTime event_start, DateTime event_end, String description, String location, 
			boolean priv, int number_of_comments, String path_to_event_picture, int number_going, boolean subevent, 
			int subevent_parent_id, int friend_group_id, Set<Comment> comments) {
		this.event_id = event_id;
		this.event_name = event_name;
		this.event_start = event_start;
		this.event_end = event_end;
		this.description = description;
		this.location = location;
		this.priv = priv;
		this.number_of_comments = number_of_comments;
		this.path_to_event_picture = path_to_event_picture;
		this.number_going = number_going;
		this.subevent = subevent;
		this.subevent_parent_id = subevent_parent_id;
		this.friend_group_id = friend_group_id;
		this.comments = comments;
	}
			
	public int getEventId() {
		return event_id;
	}
	
	public void setEventId(int id) {
		this.event_id = id;
	}
	
	public Set<Comment> getComments() {
		return this.comments;
	}

	public void setComments(Set<Comment> comments) {
		this.comments = comments;
	}
	
	public Set<User> getCategories() {
		return this.users;
	}

	public void setCategories(Set<User> users) {
		this.users = users;
	}
	
	public String getEventName() {
		return event_name;
	}
	
	public void setEventName(String event) {
		this.event_name = event;
	}
	
	public DateTime getEventStart() {
		return event_start;
	}
	
	public void setEventStart(DateTime start) {
		this.event_start = start;
	}
	
	public DateTime getEventEnd() {
		return event_end;
	}
	
	public void setEventEnd(DateTime end) {
		this.event_end = end;
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
	
	public void setLocation(String loc) {
		this.location = loc;
	}
	
	public boolean getPrivate() {
		return priv;
	}
	
	public void setPrivate(boolean priv) {
		this.priv = priv;
	}
	
	public int getNumberOfComments() {
		return number_of_comments;
	}
	
	public void setNumberOfComments(int numCom) {
		this.number_of_comments = numCom;
	}
	
	public String getPathToEventPicture() {
		return path_to_event_picture;
	}
	
	public void setPathToEventPicture(String path) {
		this.path_to_event_picture = path;
	}
	
	public int getNumberGoing() {
		return number_going;
	}
	
	public void setNumberGoing(int num) {
		this.number_going = num;
	}
	
	public int getNumberNotGoing() {
		return number_not_going;
	}
	
	public void setNumberNotGoing(int num) {
		this.number_not_going = num;
	}
	
	public boolean getSubevent() {
		return subevent;
	}
	
	public void setSubevent(boolean sub) {
		this.subevent = sub;
	}
	
	public int getSubeventParentId() {
		return subevent_parent_id;
	}
	
	public void setSubeventParentId(int subevent_id) {
		this.subevent_parent_id = subevent_id;
	}
	
	public int getFriendGroupId() {
		return friend_group_id;
	}
	
	public void setFriendGroupId(int group) {
		this.friend_group_id = group;
	}
	
}	
