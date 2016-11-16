package com.activitize.springmvc.Models;

import java.io.Serializable;
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
@Table(name="friend_groups")
public class FriendGroup implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "friend_groups_id", nullable = false)
	private Integer friend_groups_id;
	@NotNull
	@Column(name = "users_user_id", nullable = false)
	private Integer users_user_id;
	@NotNull
	@Size(max = 256)
	@Column(name = "group_name", nullable = false)
	private String group_name;
	@NotNull
	@Column(name = "group_size", nullable = false)
	private int group_size = 1;
	@NotNull	
	@Size(max = 256)
	@Column(name = "group_owner", nullable = false)
	private String group_owner;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	public FriendGroup() {

	}

	public FriendGroup(Integer friend_groups_id, Integer users_user_id, String group_name, int group_size, String group_owner, User user) {
		this.friend_groups_id = friend_groups_id;
		this.users_user_id = users_user_id;
		this.group_name = group_name;
		this.group_size = group_size;
		this.group_owner = group_owner;
		this.user = user;
	}

	public Integer getFriendGroupsId() {
		return friend_groups_id;
	}

	public void setFriendGroupsId(Integer friendgroup) {
		this.friend_groups_id = friendgroup;
	}

	public Integer getUsersUserId() {	
		return users_user_id;
	}

	public void setUsersUserId(Integer usersid) {
		this.users_user_id = usersid;
	}

	public String getGroupName() {
		return group_name;
	}

	public void setGroupName(String groupName) {
		this.group_name = groupName;
	}

	public int getGroupSize() {
		return group_size;
	}

	public void setGroupSize(int size) {
		this.group_size = size;
	}

	public String getGroupOwner() {
		return group_owner;
	}

	public void setGroupOwner(String groupOwner) {
		this.group_owner = groupOwner;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friend_groups_id == null) ? 0 : friend_groups_id.hashCode());
		result = prime * result + ((users_user_id == null) ? 0 : users_user_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof FriendGroup))
			return false;
		FriendGroup other = (FriendGroup) obj;
		if (friend_groups_id == null) {
			if (other.friend_groups_id != null)
				return false;
		} else if (!friend_groups_id.equals(other.friend_groups_id))
			return false;
		if (users_user_id == null) {
			if (other.users_user_id != null)
				return false;
		} else if (!users_user_id.equals(other.users_user_id))
			return false;
		return true;
	}
	
}
