package com.activitize.springmvc.Models;

import java.math.BigDecimal;

import javax.persistence.Column;
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
public class Friend {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	@Column(name = "friends_id", nullable = false)
	private int friends_id;
	@NotNull
	@Column(name = "users_user_id", nullable = false)
	private int users_user_id;
	@NotNull
	@Column(name = "other_user_id", nullable = false)
	private int other_user_id;
	@NotNull
	private boolean status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Friend() {
		
	}
	
	public Friend(int friends_id, int users_user_id, int other_user_id, boolean status, User user) {
		this.friends_id = friends_id;
		this.users_user_id = users_user_id;
		this.other_user_id = other_user_id;
		this.status = status;
		this.user = user;
	}
	
	public int getFriendsId() {
		return friends_id;
	}
	
	public void setFriendsId(int friendId) {
		this.friends_id = friendId;
	}
	
	public int getUsersUserId() {
		return users_user_id;
	}
	
	public void setUsersUserId(int userId) {
		this.users_user_id = userId;
	}
	
	public int getOtherUserId() {
		return other_user_id;
	}
	
	public void setOtherUserId(int other_user_id) {
		this.other_user_id = other_user_id;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
}
