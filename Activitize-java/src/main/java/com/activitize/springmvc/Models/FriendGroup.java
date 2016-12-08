package com.activitize.springmvc.Models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
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
	private Integer friend_groups_id;
	
	@NotNull
	@Size(max = 256)
	@Column(name = "group_name", nullable = false)
	private String group_name;
	
	@NotNull	
	@Size(max = 256)
	@Column(name = "group_owner", nullable = false)
	private String group_owner;
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "friendGroups")
	private Set<User> users = new HashSet<User>(0);

	public FriendGroup() {

	}

	public FriendGroup(Integer friend_groups_id, String group_name, String group_owner, Set<User> users) {
		this.friend_groups_id = friend_groups_id;
		this.group_name = group_name;
		this.group_owner = group_owner;
		this.users = users;
	}

	public Integer getFriendGroupsId() {
		return friend_groups_id;
	}

	public void setFriendGroupsId(Integer friend_groups_id) {
		this.friend_groups_id = friend_groups_id;
	}

	public String getGroupName() {
		return group_name;
	}

	public void setGroupName(String group_name) {
		this.group_name = group_name;
	}

	public String getGroupOwner() {
		return group_owner;
	}

	public void setGroupOwner(String group_owner) {
		this.group_owner = group_owner;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((friend_groups_id == null) ? 0 : friend_groups_id.hashCode());
		result = prime * result + ((group_name == null) ? 0 : group_name.hashCode());
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
		if (group_name == null) {
			if (other.group_name != null)
				return false;
		} else if (!group_name.equals(other.group_name))
			return false;
		return true;
	}
	
}
