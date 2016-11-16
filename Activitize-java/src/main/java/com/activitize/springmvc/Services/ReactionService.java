package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Reaction;

public interface ReactionService {

	Reaction findById(int id);

	List<Reaction> findAllReactionsByCommentID(int id);

	void createReaction(Reaction reaction);

	void deleteReaction(Reaction reaction);
	
}
