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
import com.activitize.springmvc.Models.User;
import com.activitize.springmvc.Services.UserService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/user")
public class UserController {
    
	@Autowired
    UserService service;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@RequestMapping(value = "/verifyUser", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public User verifyUser(@RequestBody User user) {
		service.verifyUser(user);
		return new User();
	}
	
	@RequestMapping(value = "/createUser", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse createUser(@RequestBody User user) {
		if (service.isUserEmailUnique(user.getEmail()) == false) {
			return new JsonResponse("FAILED","Email is already taken");
		}
		if (service.isUsernameUnique(user.getUsername()) == false) {
			return new JsonResponse("FAILED","Username is already taken");
		}
		service.createUser(user);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/deleteUser", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteUser(@RequestBody User user) {
		service.deleteUser(user);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/editUser", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse editUser(@RequestBody User user) {
		service.editUser(user);
		return new JsonResponse("OK","");
	}
	
}
