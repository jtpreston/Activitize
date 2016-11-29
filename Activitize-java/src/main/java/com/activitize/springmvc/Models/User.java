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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
 
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.joda.deser.LocalDateDeserializer;

@Entity
@Table(name="users")
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)	
	private Integer user_id;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.user", cascade=CascadeType.ALL)
	private Set<UserHasEvent> userHasEvents = new HashSet<UserHasEvent>(0);
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_has_friend_groups", joinColumns = {
			@JoinColumn(name = "users_user_id", nullable = false, updatable = false) },
	inverseJoinColumns = { @JoinColumn(name = "friend_groups_friend_group_id",
	nullable = false, updatable = false) })
	private Set<FriendGroup> friendGroups = new HashSet<FriendGroup>(0);
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
	private Set<Friend> friends = new HashSet<Friend>(0);
	
	@NotNull
	@Size(max=256)   
	@Column(name = "username", nullable = false)
	private String username;
	
	@NotNull
	@Size(max=256)
	@Column(name = "password", nullable = false)
	private String password;
	
	@NotNull
	@Size(max=256)
	@Column(name = "first_name", nullable = false)
	private String first_name;
	
	@NotNull
	@Size(max=256)
	@Column(name = "last_name", nullable = false)
	private String last_name;
	
	@NotNull
	@Size(max=256)
	@Column(name = "nickname", nullable = false)
	private String nickname;
	
	@NotNull
	@DateTimeFormat(pattern="dd/MM/yyyy")
	@Column(name = "age", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate age;
	
	@NotNull
	@Size(max=256)
	@Column(name = "email", nullable = false)
	private String email;
	
	@Column(name = "phone_number", nullable = true)
	private String phone_number;
	
	@Size(max=256)
	@Column(name = "path_to_profile_picture", nullable = true)
	private String path_to_profile_picture;
	
	@NotNull
	@Column(name = "number_of_friends", nullable = false)
	private int number_of_friends = 0;
	
	@NotNull
	private boolean using_facebook;
	
	@Column(name = "facebook_user_id", nullable = true)
	private long facebook_user_id;
	
	@NotEmpty
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "users_user_profile", 
	joinColumns = { @JoinColumn(name = "USERS_USER_ID") }, 
	inverseJoinColumns = { @JoinColumn(name = "USER_PROFILE_ID") })
	private Set<UserProfile> userProfiles = new HashSet<UserProfile>();

	public User() {

	}

	public User(Integer user_id, String username, String password, String first_name, String last_name, String nickname, LocalDate age, 
			String email, String phone_number, String path_to_profile_picture, int number_of_friends, boolean using_facebook, 
			long facebook_user_id, Set<Friend> friends, Set<UserProfile> userProfiles, Set<UserHasEvent> userHasEvents, Set<FriendGroup> friendGroups) {
		this.user_id = user_id;
		this.username = username;	
		this.password = password;
		this.first_name = first_name;
		this.last_name = last_name;
		this.nickname = nickname;
		this.age = age;
		this.email = email;
		this.phone_number = phone_number;
		this.path_to_profile_picture = path_to_profile_picture;
		this.number_of_friends = number_of_friends;
		this.using_facebook = using_facebook;
		this.facebook_user_id = facebook_user_id;
		this.friends = friends;
		this.userProfiles = userProfiles;
		this.userHasEvents = userHasEvents;
		this.friendGroups = friendGroups;
	}

	public Integer getUserId() {
		return user_id;
	}
	public void setUserId(Integer user_id) {
		this.user_id = user_id;
	}

	public Set<UserHasEvent> getUserHasEvents() {
		return userHasEvents;
	}

	public void setUserHasEvents(Set<UserHasEvent> userHasEvents) {
		this.userHasEvents = userHasEvents;
	}

	public Set<Friend> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public LocalDate getAge() {
		return age;
	}

	public void setAge(LocalDate age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phone_number;
	}

	public void setPhoneNumber(String phone_number) {
		this.phone_number = phone_number;
	}

	public String getPathToProfilePicture() {
		return path_to_profile_picture;
	}

	public void setPathToProfilePicture(String path_to_profile_picture) {
		this.path_to_profile_picture = path_to_profile_picture;
	}

	public int getNumberOfFriends() {
		return number_of_friends;
	}

	public void setNumberOfFriends(int number_of_friends) {
		this.number_of_friends = number_of_friends;
	}

	public boolean getUsingFacebook() {
		return using_facebook;
	}

	public void setUsingFacebook(boolean using_facebook) {
		this.using_facebook = using_facebook;
	}

	public long getFacebookUserId() {
		return facebook_user_id;
	}

	public void setFacebookUserId(long facebook_user_id) {
		this.facebook_user_id = facebook_user_id;
	}

	public Set<UserProfile> getUserProfiles() {
		return userProfiles;
	}

	public void setUserProfiles(Set<UserProfile> userProfiles) {
		this.userProfiles = userProfiles;
	}
	
	public Set<FriendGroup> getFriendGroups() {
		return friendGroups;
	}

	public void setFriendGroups(Set<FriendGroup> friendGroups) {
		this.friendGroups = friendGroups;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof User))
			return false;
		User other = (User) obj;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", first_name=" + first_name + ", last_name=" + last_name + ", nickname="
				+ nickname + ", age=" + age + ", phone_number=" + phone_number 
				+ ", email=" + email + ", number_of_friends=" + number_of_friends + ", using_facebook=" + using_facebook + "]";
	}
	
}
