package com.activitize.springmvc.Models;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class FriendId implements Serializable {

	@NotNull
	@Column(name = "users_user_id", nullable = false)
	private Integer users_user_id;

	@NotNull
	@Column(name = "other_user_id", nullable = false)
	private Integer other_user_id;

	public FriendId() {

	}

	public FriendId(Integer users_user_id, Integer other_user_id) {
		this.users_user_id = users_user_id;
		this.other_user_id = other_user_id;
	}

	public Integer getUsersUserId() {
		return users_user_id;
	}

	public void setUsersUserId(Integer users_user_id) {
		this.users_user_id = users_user_id;
	}

	public Integer getOtherUserId() {
		return other_user_id;
	}

	public void setOtherUserId(Integer other_user_id) {
		this.other_user_id = other_user_id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FriendId)) return false;
		FriendId that = (FriendId) o;
		return Objects.equals(getUsersUserId(), that.getUsersUserId()) && Objects.equals(getOtherUserId(), that.getOtherUserId());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getUsersUserId(), getOtherUserId());
	}

}
