package com.activitize.springmvc.Models;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="friends")
public class Friend implements Serializable {

	@EmbeddedId
	FriendId id;

	@NotNull
	@Column(name = "status", nullable = false)
	private boolean status = false;

	@NotNull
	@Column(name = "action_user_id", nullable = false)
	private Integer action_user_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public Friend() {

	}

	public Friend(FriendId id, boolean status, Integer action_user_id, User user) {
		this.id = id;
		this.status = status;
		this.action_user_id = action_user_id;
		this.user = user;
	}

	public FriendId getFriendId() {
		return id;
	}

	public void setFriendId(FriendId id) {
		this.id = id;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Integer getActionUserId() {
		return action_user_id;
	}

	public void setActionUserId(Integer action_user_id) {
		this.action_user_id = action_user_id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
