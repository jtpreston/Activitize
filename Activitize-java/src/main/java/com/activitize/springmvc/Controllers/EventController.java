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
		User user = new User();
		user.setUsername(getPrincipal());
		service.editEvent(event, user);
		return new JsonResponse("OK","");
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
