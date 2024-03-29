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
import com.activitize.springmvc.Models.Friend;
import com.activitize.springmvc.Models.FriendId;
import com.activitize.springmvc.Models.JsonResponse;
import com.activitize.springmvc.Models.User;
import com.activitize.springmvc.Services.FriendService;
import com.activitize.springmvc.Services.UserService;

@Controller
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	FriendService service;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/addFriend", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse addFriend(@RequestBody User user) {
		Friend friend = new Friend();
		User requestingUser = userService.findByUsername(getPrincipal());
		User otherUser = userService.findByUsername(user.getUsername());
		int compVal = requestingUser.getUserId().compareTo(otherUser.getUserId());
		FriendId friendId = new FriendId();
		if (compVal > 0) {
			friendId.setUsersUserId(otherUser.getUserId());
			friendId.setOtherUserId(requestingUser.getUserId());
		}
		else if (compVal < 0) {
			friendId.setUsersUserId(requestingUser.getUserId());
			friendId.setOtherUserId(otherUser.getUserId());
		}
		else {
			return new JsonResponse("FAILED", "Cannot add yourself");
		}
		friend.setFriendId(friendId);
		friend.setStatus(false);
		friend.setActionUserId(requestingUser.getUserId());
		service.addFriend(friend);
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/deleteFriend", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse deleteFriend(@RequestBody User user) {
		Friend friend = new Friend();
		User requestingUser = userService.findByUsername(getPrincipal());
		User otherUser = userService.findByUsername(user.getUsername());
		int compVal = requestingUser.getUserId().compareTo(otherUser.getUserId());
		FriendId friendId = new FriendId();
		if (compVal > 0) {
			friendId.setUsersUserId(otherUser.getUserId());
			friendId.setOtherUserId(requestingUser.getUserId());
		}
		else if (compVal < 0) {
			friendId.setUsersUserId(requestingUser.getUserId());
			friendId.setOtherUserId(otherUser.getUserId());
		}
		else {
			return new JsonResponse("FAILED", "Cannot delete yourself");
		}
		friend.setFriendId(friendId);
		friend.setStatus(true);
		friend.setActionUserId(requestingUser.getUserId());
		service.deleteFriend(friend);
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/confirmFriend", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse confirmFriend(@RequestBody User user) {
		Friend friend = new Friend();
		User requestingUser = userService.findByUsername(getPrincipal());
		User otherUser = userService.findByUsername(user.getUsername());
		int compVal = requestingUser.getUserId().compareTo(otherUser.getUserId());
		FriendId friendId = new FriendId();
		if (compVal > 0) {
			friendId.setUsersUserId(otherUser.getUserId());
			friendId.setOtherUserId(requestingUser.getUserId());
		}
		else if (compVal < 0) {
			friendId.setUsersUserId(requestingUser.getUserId());
			friendId.setOtherUserId(otherUser.getUserId());
		}
		else {
			return new JsonResponse("FAILED", "Cannot confirm yourself");
		}
		friend.setFriendId(friendId);
		friend.setStatus(true);
		friend.setActionUserId(requestingUser.getUserId());
		service.confirmFriend(friend);
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/rejectFriend", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public JsonResponse rejectFriend(@RequestBody User user) {
		Friend friend = new Friend();
		User requestingUser = userService.findByUsername(getPrincipal());
		User otherUser = userService.findByUsername(user.getUsername());
		int compVal = requestingUser.getUserId().compareTo(otherUser.getUserId());
		FriendId friendId = new FriendId();
		if (compVal > 0) {
			friendId.setUsersUserId(otherUser.getUserId());
			friendId.setOtherUserId(requestingUser.getUserId());
		}
		else if (compVal < 0) {
			friendId.setUsersUserId(requestingUser.getUserId());
			friendId.setOtherUserId(otherUser.getUserId());
		}
		else {
			return new JsonResponse("FAILED", "Cannot reject yourself");
		}
		friend.setFriendId(friendId);
		friend.setStatus(false);
		friend.setActionUserId(requestingUser.getUserId());
		service.rejectFriend(friend);
		return new JsonResponse("OK","");
	}

	@RequestMapping(value = "/getAllFriendsForSpecificUser", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public List<User> getAllFriendsForSpecificUser(@RequestBody User user) {
		Friend friend = new Friend();
		User requestingUser = userService.findByUsername(getPrincipal());
		User otherUser = userService.findByUsername(user.getUsername());
		int compVal = requestingUser.getUserId().compareTo(otherUser.getUserId());
		FriendId friendId = new FriendId();
		if (compVal > 0) {
			friendId.setUsersUserId(otherUser.getUserId());
			friendId.setOtherUserId(requestingUser.getUserId());
		}
		else if (compVal < 0) {
			friendId.setUsersUserId(requestingUser.getUserId());
			friendId.setOtherUserId(otherUser.getUserId());
		}
		else {
			return null;
		}
		friend.setFriendId(friendId);
		friend.setStatus(false);
		friend.setActionUserId(requestingUser.getUserId());
		if (service.findIfCurrentFriend(friend) == null) {
			return null;
		}
		return service.findFriendsByUser(otherUser);
	}

	@RequestMapping(value = "/getAllFriendsForCurrentUser", method = RequestMethod.GET)
	@ResponseBody
	public List<User> getAllFriendsForCurrentUser() {
		User user = new User();
		user = userService.findByUsername(getPrincipal());
		return service.findFriendsByUser(user);
	}

	@RequestMapping(value = "/getProfileForFriend", 
			method = RequestMethod.POST,
			headers = {"Content-type=application/json"})
	@ResponseBody
	public User getProfileForFriend(@RequestBody User user) {
		Friend friend = new Friend();
		User requestingUser = userService.findByUsername(getPrincipal());
		User otherUser = userService.findByUsername(user.getUsername());
		int compVal = requestingUser.getUserId().compareTo(otherUser.getUserId());
		FriendId friendId = new FriendId();
		if (compVal > 0) {
			friendId.setUsersUserId(otherUser.getUserId());
			friendId.setOtherUserId(requestingUser.getUserId());
		}
		else if (compVal < 0) {
			friendId.setUsersUserId(requestingUser.getUserId());
			friendId.setOtherUserId(otherUser.getUserId());
		}
		else {
			return null;
		}
		friend.setFriendId(friendId);
		friend.setStatus(false);
		friend.setActionUserId(requestingUser.getUserId());
		if (service.findIfCurrentFriend(friend) == null) {
			return null;
		}
		User returnedUser = new User();
		returnedUser.setAge(otherUser.getAge());
		returnedUser.setFirstName(otherUser.getFirstName());
		returnedUser.setLastName(otherUser.getLastName());
		returnedUser.setPhoneNumber(otherUser.getPhoneNumber());
		returnedUser.setNickname(otherUser.getNickname());
		returnedUser.setUsername(otherUser.getUsername());
		return returnedUser;
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
