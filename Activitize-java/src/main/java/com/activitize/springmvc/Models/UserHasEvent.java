package com.activitize.springmvc.Models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
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
	@EmbeddedId
	UserHasEventId id;
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
	
	public UserHasEvent(UserHasEventId id, boolean favorite, boolean admin, boolean going) {
		this.id = id;
		this.favorite = favorite;
		this.admin = admin;
		this.going = going;
	}
	
	public UserHasEventId getId() {
		return id;
	}
	
	public void setId(UserHasEventId id) {
		this.id = id;
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
