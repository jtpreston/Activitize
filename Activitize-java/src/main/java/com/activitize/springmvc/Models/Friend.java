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
@Table(name="friends")
public class Friend implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "friends_id", nullable = false)
	private Integer friends_id;
	@NotNull
	@Column(name = "users_user_id", nullable = false)
	private Integer users_user_id;
	@NotNull
	@Column(name = "other_user_id", nullable = false)
	private Integer other_user_id;
	@NotNull
	private boolean status;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
	
	public Friend() {
		
	}
	
	public Friend(Integer friends_id, Integer users_user_id, Integer other_user_id, boolean status, User user) {
		this.friends_id = friends_id;
		this.users_user_id = users_user_id;
		this.other_user_id = other_user_id;
		this.status = status;
		this.user = user;
	}
	
	public Integer getFriendsId() {
		return friends_id;
	}
	
	public void setFriendsId(Integer friendId) {
		this.friends_id = friendId;
	}
	
	public Integer getUsersUserId() {
		return users_user_id;
	}
	
	public void setUsersUserId(Integer userId) {
		this.users_user_id = userId;
	}
	
	public Integer getOtherUserId() {
		return other_user_id;
	}
	
	public void setOtherUserId(Integer other_user_id) {
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
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((friends_id == null) ? 0 : friends_id.hashCode());
        result = prime * result + ((users_user_id == null) ? 0 : users_user_id.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Friend))
            return false;
        Friend other = (Friend) obj;
        if (friends_id == null) {
            if (other.friends_id != null)
                return false;
        } else if (!friends_id.equals(other.friends_id))
            return false;
        if (users_user_id == null) {
            if (other.users_user_id != null)
                return false;
        } else if (!users_user_id.equals(other.users_user_id))
            return false;
        return true;
    }
	
}
