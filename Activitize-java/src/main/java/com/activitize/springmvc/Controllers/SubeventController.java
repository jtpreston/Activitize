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

import com.activitize.springmvc.Models.Subevent;
import com.activitize.springmvc.Models.JsonResponse;
import com.activitize.springmvc.Services.SubeventService;

@Controller
@RequestMapping("/subevent")
public class SubeventController {

	@Autowired
	SubeventService service;

	@RequestMapping(value = "/createSubevent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse createComment(@RequestBody Subevent subevent) {
		service.createSubevent(subevent);
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/deleteSubevent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteComment(@RequestBody Subevent subevent) {
		service.deleteSubevent(subevent);
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/editSubevent", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse editComment(@RequestBody Subevent subevent) {
		service.editSubevent(subevent);
		return new JsonResponse("OK","");
	}

}
