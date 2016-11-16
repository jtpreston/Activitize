package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.ReplyDao;
import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.Reply;

@Service("replyService")
@Transactional
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyDao dao;

	public Reply findById(int id) {
		return dao.findById(id);
	}

	public List<Reply> findAllRepliesByCommendID(int id) {
		return dao.findAllRepliesByCommendID(id);
	}

	public void createReply(Reply reply) {
		dao.createReply(reply);
	}

	public void deleteReply(Reply reply) {
		dao.deleteReply(reply);
	}

	public void editReply(Reply reply) {
		dao.editReply(reply);
	}
	
}
