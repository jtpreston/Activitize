package com.activitize.springmvc.Converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
 
import com.activitize.springmvc.Models.UserProfile;
import com.activitize.springmvc.Services.UserProfileService;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

	@Autowired
    UserProfileService userProfileService;
	
	public UserProfile convert(Object element) {
        Integer id = Integer.parseInt((String)element);
        UserProfile profile= userProfileService.findById(id);
        return profile;
    }
	
}
