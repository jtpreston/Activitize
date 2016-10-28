package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Event;
import com.activitize.springmvc.Models.Reply;

@Repository("replyDao")
public class ReplyDaoImpl extends AbstractDao<Integer, Reply> implements ReplyDao {

	@Override
	public Reply findById(int id) {
		// TODO Auto-generated method stub
		return getByKey(id);
	}

	@Override
	public List<Reply> findAllRepliesByCommendID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void editReply(Reply reply) {
		// TODO Auto-generated method stub
		
	}

}
