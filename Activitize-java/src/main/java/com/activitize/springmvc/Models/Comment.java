package com.activitize.springmvc.Models;

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
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="comments")
public class Comment {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	@Column(name = "comment_id", nullable = false)
	private int comment_id;
	@NotNull
	@Column(name = "comment", nullable = false)
	private String comment;
	@NotNull
	@Column(name = "timestamp", nullable = false)
	private DateTime timestamp;
	@NotNull
	@Size(max = 256)
	@Column(name = "username", nullable = false)
	private String username;
	@NotNull
	@Column(name = "events_event_id", nullable = false)
	private int events_event_id;
	@NotNull
	@Column(name = "number_of_replies", nullable = false)
	private int number_of_replies = 0;
	@NotNull
	@Column(name = "yeah", nullable = false)
	private int yeah = 0;
	@NotNull
	@Column(name = "nah", nullable = false)
	private int nah = 0;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;
	
	public Comment() {
		
	}
	public Comment(int comment_id, String comment, DateTime timestamp, String username, int events_event_id, 
		int number_of_replies, int yeah, int nah, Event event) {
		this.comment_id = comment_id;
		this.comment = comment;
		this.timestamp = timestamp;
		this.username = username;
		this.events_event_id = events_event_id;
		this.number_of_replies = number_of_replies;
		this.yeah = yeah;
		this.nah = nah;
		this.event = event;
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
	
	public DateTime getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(DateTime time) {
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
	
	public Event getEvent() {
		return event;
	}
	
	public void setEvent(Event event) {
		this.event = event;
	}
	
}
