package com.activitize.springmvc.Models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Embeddable
public class UserHasEventId implements Serializable {

	@ManyToOne
	private User user;

	@ManyToOne
	private Event event;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		UserHasEventId that = (UserHasEventId) o;

		if (user != null ? !user.equals(that.user) : that.user != null) return false;
		if (event != null ? !event.equals(that.event) : that.event != null)
			return false;

		return true;
	}

	public int hashCode() {
		int result;
		result = (user != null ? user.hashCode() : 0);
		result = 31 * result + (event != null ? event.hashCode() : 0);
		return result;
	}

}
