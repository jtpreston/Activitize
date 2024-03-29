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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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

@Entity
@Table(name="comments")
public class Comment implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer comment_id;

	@NotNull
	@Column(name = "comment", nullable = false)
	private String comment;

	@NotNull
	@Column(name = "timestamp", nullable = false)
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
	private DateTime timestamp;

	@NotNull
	@Size(max = 256)
	@Column(name = "username", nullable = false)
	private String username;

	@NotNull
	@Column(name = "events_event_id", nullable = false)
	private Integer events_event_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
	private Set<Reaction> reactions = new HashSet<Reaction>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
	private Set<Reply> repliesToComments = new HashSet<Reply>(0);

	public Comment() {

	}

	public Comment(Integer comment_id, String comment, DateTime timestamp, String username, Integer events_event_id, 
			Event event, Set<Reaction> reactions, Set<Reply> repliesToComments) {
		this.comment_id = comment_id;
		this.comment = comment;
		this.timestamp = timestamp;
		this.username = username;
		this.events_event_id = events_event_id;
		this.event = event;
		this.reactions = reactions;
		this.repliesToComments = repliesToComments;
	}

	public Integer getCommentId() {
		return comment_id;
	}

	public void setCommentId(Integer comment_id) {	
		this.comment_id = comment_id;
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

	public void setTimestamp(DateTime timestamp) {
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getEventsEventId() {
		return events_event_id;
	}

	public void setEventEventId(Integer events_event_id) {
		this.events_event_id = events_event_id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Set<Reaction> getReactions() {
		return reactions;
	}

	public void setReactions(Set<Reaction> reactions) {
		this.reactions = reactions;
	}

	public Set<Reply> getReplies() {
		return repliesToComments;
	}

	public void setReplies(Set<Reply> repliesToComments) {
		this.repliesToComments = repliesToComments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((comment_id == null) ? 0 : comment_id.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Comment))
			return false;
		Comment other = (Comment) obj;
		if (comment_id == null) {
			if (other.comment_id != null)
				return false;
		} else if (!comment_id.equals(other.comment_id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

}
