package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.CommentDao;
import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.Comment;
import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
    
	@Autowired
	private CommentDao dao;

	public Comment findById(int id) {
		return dao.findById(id);
	}

	public Comment findByUserAndEvent(User user, Event event) {		// TODO Auto-generated method stub
		return dao.findByUserAndEvent(user, event);
	}

	public List<Comment> findAllCommentsForEvent(Event event) {
		return dao.findAllCommentsForEvent(event);
	}

	public void createComment(Comment comment) {
		dao.createComment(comment);
	}

	public void deleteComment(Comment comment) {
		dao.deleteComment(comment);		
	}

	public void editComment(Comment comment) {
		dao.editComment(comment);
	}
	
}
