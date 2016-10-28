package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.ReactionDao;
import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.Reaction;

@Service("reactionService")
@Transactional
public class ReactionServiceImpl implements ReactionService {

	@Autowired
    private ReactionDao dao;

	public Reaction findById(int id) {
		return dao.findById(id);
	}

	public List<Reaction> findAllReactionsByCommentID(int id) {
		return dao.findAllReactionsByCommentID(id);
	}

	public void createReaction(Reaction reaction) {
		dao.createReaction(reaction);
	}

	public void deleteReaction(Reaction reaction) {
		dao.deleteReaction(reaction);
	}
	
}
