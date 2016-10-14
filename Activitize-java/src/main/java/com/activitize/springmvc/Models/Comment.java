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
@Table(name="comments")
public class Comment {
	
	private int comment_id;

	private String comment;
		
	private LocalDate timestamp;

	private String username;

	private int events_event_id;

	private int number_of_replies;

	private int yeah;

	private int nah;
	public Comment() {
		
	}
	public int getCommentId() {
		return comment_id;
	}
	public void setCommentId(int com) {	
		this.comment_id = com;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public LocalDate getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDate time) {
		this.timestamp = time;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getEventsEventId() {
		return events_event_id;
	}
	public void setEventEventId(int event) {
		this.events_event_id = event;
	}
	public int getNumberOfReplies() {
		return number_of_replies;
	}
	public void setNumberOfReplies(int replies) {
		this.number_of_replies = replies;
	}
	public int getYeah() {
		return yeah;
	}
	public void setYeah(int yeah) {
		this.yeah = yeah;
	}
	public int getNah() {
		return nah;
	}
	public void setNah(int nah) {
		this.nah = nah;
	}
}
