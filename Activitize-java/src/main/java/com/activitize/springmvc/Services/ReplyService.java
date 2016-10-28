package com.activitize.springmvc.Services;

import java.util.List;

import com.activitize.springmvc.Models.Reply;

public interface ReplyService {

	Reply findById(int id);
	
	List<Reply> findAllRepliesByCommendID(int id);
	
	void createReply(Reply reply);
	
	void deleteReply(Reply reply);
	
	void editReply(Reply reply);
	
}
