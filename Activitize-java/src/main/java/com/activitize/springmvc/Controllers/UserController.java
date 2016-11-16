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
    UserService userService;
	
	@Autowired
    UserProfileService userProfileService;
	
	@Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;
     
    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;
	
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	/*@RequestMapping(value = "/verifyUser", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse verifyUser(@RequestBody User user) {
		if (isCurrentAuthenticationAnonymous()) {
			userService.verifyUser(user);
			return new JsonResponse("OK","");
		}
		else {
			return new JsonResponse("ERROR","User already logged in!");
		}
	}*/
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        if (isCurrentAuthenticationAnonymous()) {
            return "login";
        } else {
            return "redirect:/list";  
        }
    }
	
	@RequestMapping(value = "/createUser", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse createUser(@RequestBody User user) {
		if (userService.isUserEmailUnique(user.getEmail()) == false) {
			return new JsonResponse("FAILED","Email is already taken");
		}
		if (userService.isUsernameUnique(user.getUsername()) == false) {
			return new JsonResponse("FAILED","Username is already taken");
		}
		userService.createUser(user);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/deleteUser", 
			method = RequestMethod.POST)
	@ResponseBody
	public JsonResponse deleteUser(HttpServletRequest request, HttpServletResponse response) {
		User user = new User();
		user.setUsername(getPrincipal());
		userService.deleteUser(user);
		logoutPage(request, response);
		return new JsonResponse("OK","");
	}
	
	@RequestMapping(value = "/editUser", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse editUser(@RequestBody User user) {
		user.setUsername(getPrincipal());
		userService.editUser(user);
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
