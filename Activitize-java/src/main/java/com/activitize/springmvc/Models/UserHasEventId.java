package com.activitize.springmvc.Models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class UserHasEventId implements Serializable {
	@NotNull
	@Column(name = "users_user_id", nullable = false)
	private int users_user_id;
	@NotNull
	@Column(name = "events_event_id", nullable = false)
	private int events_event_id;
	
	public UserHasEventId() {
		
	}
	
	public UserHasEventId(int users_user_id, int events_event_id) {
		this.users_user_id = users_user_id;
		this.events_event_id = events_event_id;
	}
	
	public int getUsersUserId() {
		return users_user_id;
	}
	
	public void setUsersUserId(int users_user_id) {
		this.users_user_id = users_user_id;
	}
	
	public int getEventsEventId() {
		return events_event_id;
	}
	
	public void setEventsEventId(int events_event_id) {
		this.events_event_id = events_event_id;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof UserHasEventId)) return false;
		UserHasEventId that = (UserHasEventId) o;
		return Objects.equals(getUsersUserId(), that.getUsersUserId()) && Objects.equals(getEventsEventId(), that.getEventsEventId());
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(getUsersUserId(), getEventsEventId());
	}
	
}
