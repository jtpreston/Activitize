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
	@NotNull
	@Column(name = "users_user_id", nullable = false)
	private int users_user_id;
	@NotNull
	@Column(name = "events_event_id", nullable = false)
	private int events_event_id;
	@NotNull
	@Column(name = "favorite", nullable = false)
	private boolean favorite;
	@NotNull
	@Column(name = "admin", nullable = false)
	private boolean admin;
	@NotNull
	@Column(name = "going", nullable = false)
	private boolean going;
	
	public UserHasEvent() {
		
	}
	
	public UserHasEvent(int users_user_id, int events_event_id, boolean favorite, boolean admin, boolean going) {
		this.users_user_id = users_user_id;
		this.events_event_id = events_event_id;
		this.favorite = favorite;
		this.admin = admin;
		this.going = going;
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
	
	public void setGoing(boolean going) {
		this.going = going;
	}
	
}
