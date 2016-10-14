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
@Table(name="users")
public class User {
 	
	private int user_id;
		   
	private String username;

	private String password;
	
	private String first_name;

	private String last_name;

	private String nickname;

	private LocalDate age;

	private String email;
	
	private int phone_number;

	private String path_to_profile_picture;

	private int number_of_friends;

	private boolean using_facebook;

	private long facebook_user_id;
	public User() {
		
	}
	public int getId() {
		return user_id;
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
	public void setEmail(string email) {
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
