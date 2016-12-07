package com.activitize.springmvc.Controllers;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;
import com.activitize.springmvc.Models.UserEventWrapper;
import com.activitize.springmvc.Models.JsonResponse;
import com.activitize.springmvc.Services.EventService;

@Controller
@RequestMapping("/events")
public class EventController {

	@Autowired
	EventService service;

	@RequestMapping(value = "/createEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse createEvent(@RequestBody Event event) {
		if (service.isEventUnique(event) == false) {
			return new JsonResponse("FAILED","Event was already created");
		}
		User user = new User();
		user.setUsername(getPrincipal());
		service.createEvent(event, user);
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/deleteEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteEvent(@RequestBody Event event) {
		if (event.getEventId() == null) {
			return new JsonResponse("FAILED", "No event ID present in request");
		}
		User user = new User();
		user.setUsername(getPrincipal());
		service.deleteEvent(event, user);
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/editEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse editEvent(@RequestBody Event event) {
		if (event.getEventId() == null) {
			return new JsonResponse("FAILED", "No event ID present in request");
		}
		User user = new User();
		user.setUsername(getPrincipal());
		service.editEvent(event, user);
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/addUserToEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse addUserToEvent(@RequestBody UserEventWrapper userEventWrapper) {
		if (userEventWrapper.getEvent().getEventId() == null) {
			return new JsonResponse("FAILED", "No event ID present in request");
		}
		User user = new User();
		user.setUsername(getPrincipal());
		boolean success = service.doesRequestingUserHavePermission(userEventWrapper.getEvent(), user);
		if (!success) {
			return new JsonResponse("FAILED","User did not have permission to add a user to this event");
		}
		success = service.addUserToEvent(userEventWrapper.getEvent(), userEventWrapper.getUser());
		if (!success) {
			return new JsonResponse("FAILED","User was already added to this event");
		}
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/removeUserFromEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse removeUserFromEvent(@RequestBody UserEventWrapper userEventWrapper) {
		if (userEventWrapper.getEvent().getEventId() == null) {
			return new JsonResponse("FAILED", "No event ID present in request");
		}
		User user = new User();
		user.setUsername(getPrincipal());
		boolean success = service.doesRequestingUserHavePermission(userEventWrapper.getEvent(), user);
		if (!success) {
			return new JsonResponse("FAILED","User did not have permission to remove a user from this event");
		}
		Event tempEvent = service.canUserBeRemoved(userEventWrapper.getEvent(), user);
		if (tempEvent.getCreator().equals(userEventWrapper.getUser().getUsername())) {
			return new JsonResponse("FAILED","User did not have permission to remove a user from this event");
		}
		success = service.removeUserFromEvent(userEventWrapper.getEvent(), userEventWrapper.getUser());
		if (!success) {
			return new JsonResponse("FAILED","User was already removed from this event");
		}
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/declineUserIsGoingToEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse declineUserIsGoingToEvent(@RequestBody Event event) {
		if (event.getEventId() == null) {
			return new JsonResponse("FAILED", "No event ID present in request");
		}
		User user = new User();
		user.setUsername(getPrincipal());
		boolean success = service.declineUserIsGoingToEvent(event, user);
		if (!success) {
			return new JsonResponse("FAILED","User was already removed from this event");
		}
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/confirmUserIsGoingToEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse confirmUserIsGoingToEvent(@RequestBody Event event) {
		if (event.getEventId() == null) {
			return new JsonResponse("FAILED", "No event ID present in request");
		}
		User user = new User();
		user.setUsername(getPrincipal());
		boolean success = service.confirmUserIsGoingToEvent(event, user);
		if (!success) {
			return new JsonResponse("FAILED","User couldn't confirm going to this event");
		}
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/addMultipleUsersToEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse addMultipleUsersToEvent(@RequestBody UserEventWrapper[] userEventWrapper) {
		User user = new User();
		user.setUsername(getPrincipal());
		boolean success = service.doesRequestingUserHavePermission(userEventWrapper[0].getEvent(), user);
		if (!success) {
			return new JsonResponse("FAILED","User did not have permission to add a user to this event");
		}
		for (int i = 0; i < userEventWrapper.length; i++) {
			if (userEventWrapper[i].getEvent().getEventId() == null) {
				return new JsonResponse("FAILED", "No event ID present in request");
			}
			success = service.addUserToEvent(userEventWrapper[i].getEvent(), userEventWrapper[i].getUser());
			if (!success) {
				return new JsonResponse("FAILED","User " + userEventWrapper[i].getUser().getUsername() + " was already added to this event");
			}
		}
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/removeMultipleUsersFromEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse removeMultipleUsersFromEvent(@RequestBody UserEventWrapper[] userEventWrapper) {
		User user = new User();
		user.setUsername(getPrincipal());
		boolean success = service.doesRequestingUserHavePermission(userEventWrapper[0].getEvent(), user);
		if (!success) {
			return new JsonResponse("FAILED","User did not have permission to remove a user from this event");
		}
		Event tempEvent = service.canUserBeRemoved(userEventWrapper[0].getEvent(), user);
		for (int i = 0; i < userEventWrapper.length; i++) {
			if (userEventWrapper[i].getEvent().getEventId() == null) {
				return new JsonResponse("FAILED", "No event ID present in request");
			}
			if (tempEvent.getCreator().equals(userEventWrapper[i].getUser().getUsername())) {
				return new JsonResponse("FAILED","User did not have permission to remove a user from this event");
			}
			success = service.removeUserFromEvent(userEventWrapper[i].getEvent(), userEventWrapper[i].getUser());
			if (!success) {
				return new JsonResponse("FAILED","User " + userEventWrapper[i].getUser().getUsername() + " was already removed from this event");
			}
		}
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/getAllEventsForUser", method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getAllEventsForUser() {
		User user = new User();
		user.setUsername(getPrincipal());
		return service.getAllEventsForUser(user);
	}

	@RequestMapping(value = "/getAllEventFavoritesForUser", method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getAllEventFavoritesForUser() {
		User user = new User();
		user.setUsername(getPrincipal());
		return service.getAllEventFavoritesForUser(user);
	}

	@RequestMapping(value = "/getAllPendingEventsForUser", method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getAllPendingEventsForUser() {
		User user = new User();
		user.setUsername(getPrincipal());
		return service.getAllPendingEventsForUser(user);
	}

	@RequestMapping(value = "/getAllAcceptedEventsForUser", method = RequestMethod.GET)
	@ResponseBody
	public List<Event> getAllAcceptedEventsForUser() {
		User user = new User();
		user.setUsername(getPrincipal());
		return service.getAllAcceptedEventsForUser(user);
	}

	@RequestMapping(value = "/getAllAdminsForEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public List<User> getAllAdminsForEvent(@RequestBody Event event) {
		User user = new User();
		user.setUsername(getPrincipal());
		return service.getAllAdminsForEvent(event, user);
	}

	@RequestMapping(value = "/getAllNonAdminsForEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public List<User> getAllNonAdminsForEvent(@RequestBody Event event) {
		User user = new User();
		user.setUsername(getPrincipal());
		return service.getAllNonAdminsForEvent(event, user);
	}

	@RequestMapping(value = "/getAllUsersForEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public List<User> getAllUsersForEvent(@RequestBody Event event) {
		User user = new User();
		user.setUsername(getPrincipal());
		return service.getAllUsersForEvent(event, user);
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails)principal).getUsername();
		} 
		else {
			userName = principal.toString();
		}
		return userName;
	}

}
