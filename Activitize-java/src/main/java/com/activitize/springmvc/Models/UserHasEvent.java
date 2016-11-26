package com.activitize.springmvc.Models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users_has_events")
@AssociationOverrides({
	@AssociationOverride(name = "pk.user",
			joinColumns = @JoinColumn(name = "user_id")),
	@AssociationOverride(name = "pk.event",
	joinColumns = @JoinColumn(name = "event_id")) })
public class UserHasEvent implements Serializable {

	@EmbeddedId
	private UserHasEventId pk = new UserHasEventId();

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

	public UserHasEvent(UserHasEventId pk, boolean favorite, boolean admin, boolean going) {
		this.pk = pk;
		this.favorite = favorite;
		this.admin = admin;
		this.going = going;
	}

	public UserHasEventId getPk() {
		return pk;
	}

	public void setPk(UserHasEventId pk) {
		this.pk = pk;
	}

	@Transient
	public User getUser() {
		return getPk().getUser();
	}

	public void setUser(User user) {
		getPk().setUser(user);
	}

	@Transient
	public Event getEvent() {
		return getPk().getEvent();
	}

	public void setEvent(Event event) {
		getPk().setEvent(event);
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

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserHasEvent that = (UserHasEvent) o;

		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getPk() != null ? getPk().hashCode() : 0);
	}

}
