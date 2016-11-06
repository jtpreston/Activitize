package com.activitize.springmvc.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_PROFILE")
public class UserProfile {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id; 
 
    @Column(name="TYPE", length=15, unique=true, nullable=false)
    private String type = UserProfileType.USER.getUserProfileType();
    
    public UserProfile() {
    	
    }
    
    public UserProfile(int id, String type) {
    	this.id = id;
    	this.type = type;
    }
     
    public Integer getId() {
        return id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
 
    public String getType() {
        return type;
    }
 
    public void setType(String type) {
        this.type = type;
    }
 
	
}
