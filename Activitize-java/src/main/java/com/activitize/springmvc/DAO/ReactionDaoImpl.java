package com.activitize.springmvc.DAO;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.Reaction;

@Repository("reactionDao")
public class ReactionDaoImpl extends AbstractDao<Integer, Reaction> implements ReactionDao {

}
