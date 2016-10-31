package com.activitize.springmvc.Models;

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
public class User {
 	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull	
	private int user_id;
 	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "users_has_events", joinColumns = {
			@JoinColumn(name = "users_user_id", nullable = false, updatable = false) },
			inverseJoinColumns = { @JoinColumn(name = "events_event_id",
					nullable = false, updatable = false) })
 	private Set<Event> events = new HashSet<Event>(0);
 	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
 	private Set<Friend> friends = new HashSet<Friend>(0);
 	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
 	private Set<FriendGroup> friendGroups = new HashSet<FriendGroup>(0);
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
	private int phone_number;
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
	
	public User() {
		
	}
	
	public User(int user_id, String username, String password, String first_name, String last_name, String nickname, LocalDate age, 
			String email, int phone_number, String path_to_profile_picture, int number_of_friends, boolean using_facebook, 
			long facebook_user_id, Set<Friend> friends, Set<FriendGroup> friendGroups) {
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
		this.friendGroups = friendGroups;
	}
	
	public int getId() {
		return user_id;
	}
	
	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}
	
	public Set<Friend> getFriends() {
		return friends;
	}

	public void setFriends(Set<Friend> friends) {
		this.friends = friends;
	}
	
	public Set<FriendGroup> getFriendGroups() {
		return friendGroups;
	}

	public void setFriendGroups(Set<FriendGroup> friendGroups) {
		this.friendGroups = friendGroups;
	}
	
	public void setId(int user_id) {
		this.user_id = user_id;
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
	
	public void setNickName(String nickname) {
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
	
	public int getPhoneNumber() {
		return phone_number;
	}
	
	public void setPhoneNumber(int phone_number) {
		this.phone_number = phone_number;
	}
	
	public String getPathToProfilePicture() {
		return path_to_profile_picture;
	}
	
	public void setPathToProfilePicture(String path) {
		this.path_to_profile_picture = path;
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
	
	public void setUsingFacebook(boolean usingFacebook) {
		this.using_facebook = usingFacebook;
	}
	
	public long getFacebookId() {
		return facebook_user_id;
	}
	
	public void setFacebookId(long facebook) {
		this.facebook_user_id = facebook;
	}
	
}
