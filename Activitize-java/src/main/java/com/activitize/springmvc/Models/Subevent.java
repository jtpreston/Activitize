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
import javax.persistence.Table;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
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
@Table(name="subevents")
public class Subevent implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer subevents_id;

	@NotNull
	@Column(name = "events_event_id", nullable = false)
	private Integer events_event_id;

	@NotNull
	@Column(name = "subevents_event_id", nullable = false)
	private Integer subevents_event_id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "event_id", nullable = false)
	private Event event;

	public Subevent() {

	}

	public Subevent(Integer subevents_id, Integer events_event_id, Integer subevents_event_id, Event event) {
		this.subevents_event_id = subevents_event_id;
		this.events_event_id = events_event_id;
		this.subevents_id = subevents_id;
		this.event = event;
	}

	public Integer getSubeventsId() {
		return subevents_id;
	}

	public void setSubeventsId(Integer subevents_id) {
		this.subevents_id = subevents_id;
	}

	public Integer getEventsEventId() {
		return events_event_id;
	}

	public void setEventsEventId(Integer events_event_id) {
		this.events_event_id = events_event_id;
	}

	public Integer getSubeventsEventId() {
		return subevents_event_id;
	}

	public void setSubeventsEventId(Integer subevents_event_id) {
		this.subevents_event_id = subevents_event_id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((subevents_id == null) ? 0 : subevents_id.hashCode());
		result = prime * result + ((events_event_id == null) ? 0 : events_event_id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Event))
			return false;
		Subevent other = (Subevent) obj;
		if (subevents_id == null) {
			if (other.subevents_id != null)
				return false;
		} else if (!subevents_id.equals(other.subevents_id))
			return false;
		if (events_event_id == null) {
			if (other.events_event_id != null)
				return false;
		} else if (!events_event_id.equals(other.events_event_id))
			return false;
		return true;
	}

}
