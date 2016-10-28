package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.Reaction;

public interface ReactionDao {

	Reaction findById(int id);
	
	List<Reaction> findAllReactionsByCommentID(int id);
	
	void createReaction(Reaction reaction);
	
	void deleteReaction(Reaction reaction);
	
}
