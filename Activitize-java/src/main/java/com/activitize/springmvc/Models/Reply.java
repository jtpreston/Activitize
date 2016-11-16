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
@Table(name="replies_to_comments")
public class Reply implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer replies_to_comments_id;
	
	@NotNull
	@Column(name = "comments_comment_id", nullable = false)
	private Integer comments_comment_id;
	
	@NotNull
	@Column(name = "comments_events_event_id", nullable = false)
	private Integer comments_events_event_id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_id", nullable = false)
	private Comment comment;

	public Reply() {

	}

	public Reply(Integer replies_to_comments_id, Integer comments_comment_id, Integer comments_events_event_id, Comment comment) {
		this.replies_to_comments_id = replies_to_comments_id;
		this.comments_comment_id = comments_comment_id;
		this.comments_events_event_id = comments_events_event_id;
		this.comment = comment;
	}

	private Integer getRepliesToCommentsId() {
		return replies_to_comments_id;
	}

	private void setRepliesToCommentsId(Integer replies_to_comments_id) {
		this.replies_to_comments_id = replies_to_comments_id;
	}

	public Integer getCommentsCommentId() {
		return comments_comment_id;
	}

	public void setCommentsCommentId(Integer comments_comment_id) {
		this.comments_comment_id = comments_comment_id;
	}

	public Integer getCommentsEventsEventId() {
		return comments_events_event_id;
	}

	public void setCommentsEventsEventId(Integer comments_events_event_id) {
		this.comments_events_event_id = comments_events_event_id;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((replies_to_comments_id == null) ? 0 : replies_to_comments_id.hashCode());
		result = prime * result + ((comments_comment_id == null) ? 0 : comments_comment_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Reply))
			return false;
		Reply other = (Reply) obj;
		if (replies_to_comments_id == null) {
			if (other.replies_to_comments_id != null)
				return false;
		} else if (!replies_to_comments_id.equals(other.replies_to_comments_id))
			return false;
		if (comments_comment_id == null) {
			if (other.comments_comment_id != null)
				return false;
		} else if (!comments_comment_id.equals(other.comments_comment_id))
			return false;
		return true;
	}
	
}
