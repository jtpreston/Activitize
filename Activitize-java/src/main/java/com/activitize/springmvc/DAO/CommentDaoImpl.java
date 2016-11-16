package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Comment;
import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.User;

@Repository("commentDao")
public class CommentDaoImpl extends AbstractDao<Integer, Comment> implements CommentDao {

	public Comment findById(int id) {
		return getByKey(id);
	}

	public Comment findByUserAndEvent(User user, Event event) {
		return null;
	}

	public List<Comment> findAllCommentsForEvent(Event event) {
		return null;
	}

	public void createComment(Comment comment) {

	}

	public void deleteComment(Comment comment) {

	}

	public void editComment(Comment comment) {

	}
	
}
