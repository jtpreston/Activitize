package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.UserHasEvent;

@Repository("userHasEventDao")
public class UserHasEventDaoImpl extends AbstractDao<Integer, UserHasEvent> implements UserHasEventDao {

}
