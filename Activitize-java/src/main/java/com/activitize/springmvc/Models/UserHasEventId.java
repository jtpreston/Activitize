package com.activitize.springmvc.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class UserHasEventId implements Serializable {
	@NotNull
	@Column(name = "users_user_id", nullable = false)
	private int users_user_id;
	@NotNull
	@Column(name = "events_event_id", nullable = false)
	private int events_event_id;
}
