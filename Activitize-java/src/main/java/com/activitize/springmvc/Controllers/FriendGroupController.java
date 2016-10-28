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

import com.activitize.springmvc.Models.FriendGroup;
import com.activitize.springmvc.Models.JsonResponse;
import com.activitize.springmvc.Services.FriendGroupService;

@Controller
@RequestMapping("/friendgroup")
public class FriendGroupController {
	
	@Autowired
    FriendGroupService service;

	@RequestMapping(value = "/addFriendGroup", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse addFriendGroup(@RequestBody FriendGroup friends) {
		service.addFriendGroup(friends);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/deleteFriendGroup", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteFriendGroup(@RequestBody FriendGroup friends) {
		service.deleteFriendGroup(friends);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/modifyFriendGroup", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse modifyFriendGroup(@RequestBody FriendGroup friends) {
		service.modifyFriendGroup(friends);
		return new JsonResponse("OK","");
	}
}
