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

import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.JsonResponse;
import com.activitize.springmvc.Services.FriendService;

@Controller
@RequestMapping("/friend")
public class FriendController {
	
	@Autowired
    FriendService service;
	
	@RequestMapping(value = "/addFriend", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse addFriend(@RequestBody Friend friend) {
		service.addFriend(friend);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/deleteFriend", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteFriend(@RequestBody Friend friend) {
		service.deleteFriend(friend);
		return new JsonResponse("OK","");
	}
	
}
