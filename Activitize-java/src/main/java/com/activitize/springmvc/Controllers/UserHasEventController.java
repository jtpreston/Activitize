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

import com.activitize.springmvc.Models.JsonResponse;
import com.activitize.springmvc.Models.UserHasEvent;
import com.activitize.springmvc.Services.UserHasEventService;

@Controller
@RequestMapping("/userhasevent")
public class UserHasEventController {
	
	@Autowired
    UserHasEventService service;

	@RequestMapping(value = "/createEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse createEvent(@RequestBody UserHasEvent userHasEvent) {
		service.createEvent(userHasEvent);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/deleteEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteEvent(@RequestBody UserHasEvent userHasEvent) {
		service.deleteEvent(userHasEvent);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/editEvent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse editEvent(@RequestBody UserHasEvent userHasEvent) {
		service.editEvent(userHasEvent);
		return new JsonResponse("OK","");
	}
}
