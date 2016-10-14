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
@Table(name="users_has_events")
public class UserHasEvent {
	
	private int users_user_id;
	
	private int events_event_id;

	private boolean favorite;
	
	private boolean admin;

	private boolean going;
	
	public UserHasEvent() {
		
	}
	public int getUsersUserId() {
		return users_user_id;
	}
	public void setUsersUserId(int usersid) {
		this.users_user_id = usersid;
	}	
	public int getEventsEventId() {
		return events_event_id;
	}
	public void setEventsEventId(int eventid) {
		this.events_event_id = eventid;
	}
	public boolean getFavorite() {
		return favorite;
	}
	public void setFavorite(boolean favorite) {
		this.favorite = favorite;
	}
	public boolean getAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {	
		this.admin = admin;
	}
	public boolean getGoing() {
		return going;
	}
	public boolean setGoing(boolean going) {
		this.going = going;
	}
}
