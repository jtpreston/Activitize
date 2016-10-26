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
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="reactions")
public class Reaction {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@NotNull
	@Column(name = "reactions_id", nullable = false)
	private int reactions_id;
	@NotNull
	@Column(name = "comments_comment_id", nullable = false)
	private int comments_comment_id;
	@NotNull
	@Column(name = "comments_events_event_id", nullable = false)
	private int comments_events_event_id;
	@NotNull
	@Size(max = 256)
	@Column(name = "username", nullable = false)
	private String username;
	@NotNull
	@Column(name = "yeah", nullable = false)
	private boolean yeah;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id", nullable = false)
	private Comment comment;
	
	public Reaction() {
		
	}
	
	public Reaction(int reactions_id, int comments_comment_id, int comments_events_event_id, String username, boolean yeah, Comment comment) {
		this.reactions_id = reactions_id;
		this.comments_comment_id = comments_comment_id;
		this.comments_events_event_id = comments_events_event_id;
		this.username = username;
		this.yeah = yeah;
		this.comment = comment;
	}
	
	public int getReactionsId() {
		return reactions_id;
	}
	
	public void setReactionsId(int reactions) {
		this.reactions_id = reactions;
	}
	
	public int getCommentsCommentId() {
		return comments_comment_id;
	}
	
	public void setCommentsCommentId(int comment) {
		this.comments_comment_id = comment;
	}
	
	public int getCommentsEventsEventId() {
		return comments_events_event_id;
	}
	
	public void setCommentsEventsEventId(int event) {
		this.comments_events_event_id = event;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String user) {
		this.username = user;
	}
	
	public boolean getYeah() {
		return yeah;
	}
	
	public void setYeah(boolean yeah) {
		this.yeah = yeah;
	}
	
	public Comment getComment() {
		return comment;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
}
