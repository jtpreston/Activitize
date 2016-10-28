package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Comment;
import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

public interface CommentService {

	Comment findById(int id);
	
	Comment findByUserAndEvent(User user, Event event);
				
	List<Comment> findAllCommentsForEvent(Event event);
	
	void createComment(Comment comment);
	
	void deleteComment(Comment comment);
	
	void editComment(Comment comment);
}
