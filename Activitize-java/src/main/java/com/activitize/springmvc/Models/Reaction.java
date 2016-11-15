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
@Table(name="reactions")
public class Reaction implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "reactions_id", nullable = false)
	private Integer reactions_id;
	@NotNull
	@Column(name = "comments_comment_id", nullable = false)
	private Integer comments_comment_id;
	@NotNull
	@Column(name = "comments_events_event_id", nullable = false)
	private Integer comments_events_event_id;
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
	
	public Reaction(Integer reactions_id, Integer comments_comment_id, Integer comments_events_event_id, String username, boolean yeah, Comment comment) {
		this.reactions_id = reactions_id;
		this.comments_comment_id = comments_comment_id;
		this.comments_events_event_id = comments_events_event_id;
		this.username = username;
		this.yeah = yeah;
		this.comment = comment;
	}
	
	public Integer getReactionsId() {
		return reactions_id;
	}
	
	public void setReactionsId(Integer reactions) {
		this.reactions_id = reactions;
	}
	
	public Integer getCommentsCommentId() {
		return comments_comment_id;
	}
	
	public void setCommentsCommentId(Integer comment) {
		this.comments_comment_id = comment;
	}
	
	public Integer getCommentsEventsEventId() {
		return comments_events_event_id;
	}
	
	public void setCommentsEventsEventId(Integer event) {
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
	
	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((reactions_id == null) ? 0 : reactions_id.hashCode());
        result = prime * result + ((comments_comment_id == null) ? 0 : comments_comment_id.hashCode());
        return result;
    }
 
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Reaction))
            return false;
        Reaction other = (Reaction) obj;
        if (reactions_id == null) {
            if (other.reactions_id != null)
                return false;
        } else if (!reactions_id.equals(other.reactions_id))
            return false;
        if (comments_comment_id == null) {
            if (other.comments_comment_id != null)
                return false;
        } else if (!comments_comment_id.equals(other.comments_comment_id))
            return false;
        return true;
    }
	
}
