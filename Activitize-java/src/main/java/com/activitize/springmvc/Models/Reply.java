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
@Table(name="replies_to_comments")
public class Reply {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	@Column(name = "replies_to_comments_id", nullable = false)
	private int replies_to_comments_id;
	@NotNull
	@Column(name = "comments_comment_id", nullable = false)
	private int comments_comment_id;
	@NotNull
	@Column(name = "comments_events_event_id", nullable = false)
	private int comments_events_event_id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id", nullable = false)
	private Comment comment;
	
	public Reply() {
		
	}
	
	public Reply(int replies_to_comments_id, int comments_comment_id, int comments_events_event_id, Comment comment) {
		this.replies_to_comments_id = replies_to_comments_id;
		this.comments_comment_id = comments_comment_id;
		this.comments_events_event_id = comments_events_event_id;
		this.comment = comment;
	}
	
	private int getRepliesToCommentsId() {
		return replies_to_comments_id;
	}
	
	private void setRepliesToCommentsId(int reply) {
		this.replies_to_comments_id = reply;
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
	
	public void setCommentsEventsEventId(int comments_events) {
		this.comments_events_event_id = comments_events;
	}
	
	public Comment getComment() {
		return comment;
	}
	
	public void setComment(Comment comment) {
		this.comment = comment;
	}
	
}
