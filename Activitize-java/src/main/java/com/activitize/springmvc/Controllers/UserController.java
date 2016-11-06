package com.activitize.springmvc.Controllers;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.activitize.springmvc.Models.JsonResponse;
import com.activitize.springmvc.Models.User;
import com.activitize.springmvc.Models.UserProfile;
import com.activitize.springmvc.Services.UserService;
import com.activitize.springmvc.Services.UserProfileService;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Controller
@RequestMapping("/user")
@SessionAttributes("roles")
public class UserController {
    
	@Autowired
    UserService service;
	
	@Autowired
    UserProfileService userProfileService;
	
	@Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
     
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@RequestMapping(value = "/verifyUser", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse verifyUser(@RequestBody User user) {
		if (isCurrentAuthenticationAnonymous()) {
			service.verifyUser(user);
			return new JsonResponse("OK","");
		}
		else {
			return new JsonResponse("ERROR","User already logged in!");
		}
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
	
	@ModelAttribute("roles")
    public List<UserProfile> initializeProfiles() {
        return userProfileService.findAll();
    }
	
	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	@ResponseBody
    public JsonResponse accessDeniedPage() {
		return new JsonResponse("Access Denied!","Improper credentials were provided");
    }
	
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	@ResponseBody
    public JsonResponse logoutPage (HttpServletRequest request, HttpServletResponse response){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {    
            persistentTokenBasedRememberMeServices.logout(request, response, auth);
            SecurityContextHolder.getContext().setAuthentication(null);
        }
        return new JsonResponse("OK", "");
    }
	
	private String getPrincipal(){
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
	
	private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
	}
	
}
