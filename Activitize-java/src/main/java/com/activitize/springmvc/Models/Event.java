package com.activitize.springmvc.Models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	
	private int event_id;

	private LocalDate event_start;
		
	private LocalDate event_end;

	private String description;
	
	private String location;
	
	private boolean priv;
		
	private int number_of_comments;

	private String number_of_comments;

	private int number_of_comments;

	private String path_to_event_picture;

	private int number_going;

	private int number_not_going;

	private boolean subevent;

	private int subevent_parent_id;

	private int friend_group_id;
	public Event() {
		
	}
	public int getEventId() {
		return event_id;
	}
	public void setEventId(int id) {
		this.event_id = id;
	}
	public LocalDate getEventStart() {
		return event_start;
	}
	public void setEventStart(LocalDate start) {
		this.event_start = start;
	}
	public LocalDate getEventEnd() {
		return event_end;
	}
	public void setEventEnd(LocalDate end) {
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
	public boolean setSubevent(boolean sub) {
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
