package com.activitize.springmvc.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.activitize.springmvc.DAO.FriendDao;
import com.activitize.springmvc.DAO.UserDao;
import com.activitize.springmvc.Models.Friend;

@Service("friendService")
@Transactional
public class FriendServiceImpl implements FriendService {

	@Autowired
    private FriendDao dao;
	
}
