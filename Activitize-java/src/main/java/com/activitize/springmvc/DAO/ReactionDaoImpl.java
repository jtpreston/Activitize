package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Reaction;

@Repository("reactionDao")
public class ReactionDaoImpl extends AbstractDao<Integer, Reaction> implements ReactionDao {

	@Override
	public Reaction findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public List<Reaction> findAllReactionsByCommentID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReaction(Reaction reaction) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteEvent(Reaction reaction) {
		// TODO Auto-generated method stub
		
	}

}
