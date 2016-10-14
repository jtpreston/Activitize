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
@Table(name="friend_groups")
public class FriendGroup {
	@NotNull
	@Column(name = "friend_groups_id", nullable = false)
	private int friend_groups_id;
	@NotNull
	@Column(name = "users_user_id", nullable = false)
	private int users_user_id;
	@NotNull
	@Size(max = 256)
	@Column(name = "group_name", nullable = false)
	private String group_name;
	@NotNull
	@Column(name = "group_size", nullable = false)
	private int group_size;
	@NotNull	
	@Size(max = 256)
	@Column(name = "group_owner", nullable = false)
	private String group_owner;
	public FriendGroup() {
		
	}
	public int getFriendGroupsId() {
		return friend_groups_id;
	}
	public void setFriendGroupsId(int friendgroup) {
		this.friend_groups_id = friendgroup;
	}
	public int getUsersUserId() {	
		return users_user_id;
	}
	public void setUsersUserId(int usersid) {
		this.users_user_id = usersid;
	}
	public String getGroupName() {
		return group_name;
	}
	public void setGroupName(String groupName) {
		this.group_name = groupname;
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
	
}
