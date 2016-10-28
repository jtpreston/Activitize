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

	public Reply findById(int id) {
		return getByKey(id);
	}
	
	public List<Reply> findAllRepliesByCommendID(int id) {	
		return null;
	}
	
	public void createReply(Reply reply) {	
		
	}
	
	public void deleteReply(Reply reply) {	
		
	}
	
	public void editReply(Reply reply) {	
		
	}

}
