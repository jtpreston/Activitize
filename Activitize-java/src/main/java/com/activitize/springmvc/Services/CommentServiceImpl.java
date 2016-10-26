package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.CommentDao;
import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.Comment;

@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
    
	@Autowired
    private CommentDao dao;
	
}
