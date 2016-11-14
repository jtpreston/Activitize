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
import com.activitize.springmvc.Models.Reaction;
import com.activitize.springmvc.Services.ReactionService;

@Controller
@RequestMapping("/reaction")
public class ReactionController {
	
	@Autowired
    ReactionService service;

	@RequestMapping(value = "/createReaction", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse createReaction(@RequestBody Reaction reaction) {
		service.createReaction(reaction);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/deleteReaction", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteReaction(@RequestBody Reaction reaction) {
		service.deleteReaction(reaction);
		return new JsonResponse("OK","");
	}
}
