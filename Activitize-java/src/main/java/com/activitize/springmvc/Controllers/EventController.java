package com.activitize.springmvc.Controllers;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
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
		service.createEvent(event);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/deleteEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteEvent(@RequestBody Event event) {
		service.deleteEvent(event);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/editEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse editEvent(@RequestBody Event event) {
		service.editEvent(event);
		return new JsonResponse("OK","");
	}

}
