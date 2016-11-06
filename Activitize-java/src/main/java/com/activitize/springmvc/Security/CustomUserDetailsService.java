package com.activitize.springmvc.Security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.Models.User;
import com.activitize.springmvc.Models.UserProfile;
import com.activitize.springmvc.Services.UserService;

public class CustomUserDetailsService {

	@Autowired
	private UserService userService;

	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userService.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), 
				true, true, true, true, getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (UserProfile userProfile : user.getUserProfiles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_"+userProfile.getType()));
		}
		return authorities;
	}

}
