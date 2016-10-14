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
@Table(name="reactions")
public class Reaction {
	
	private int reactions_id;
	
	private int comments_comment_id;

	private int comments_events_event_id;

	private String username;

	private boolean yeah;
	public Reaction() {
		
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
	public void setCommentsCommentId(int comment {
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
}
