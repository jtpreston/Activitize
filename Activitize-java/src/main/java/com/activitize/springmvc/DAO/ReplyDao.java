package com.activitize.springmvc.DAO;

import java.util.List;

import com.activitize.springmvc.Models.Reply;

public interface ReplyDao {

	Reply findById(int id);
	
	List<Reply> findAllRepliesByCommendID(int id);
	
	void createReply(Reply reply);
	
	void deleteReply(Reply reply);
	
	void editReply(Reply reply);
	
}
