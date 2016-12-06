package com.activitize.springmvc.DAO;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.activitize.springmvc.Models.User;
import com.activitize.springmvc.Models.UserProfile;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<Integer, User> implements UserDao {

	private static final Logger logger = LogManager.getLogger(UserDaoImpl.class); 

	public User findById(int id) {
		return getByKey(id);
	}

	public User findByEmail(String email) {
		boolean exists = (Long) getSession().createQuery("select count(*) from User where email = :email").setParameter("email", email).uniqueResult() > 0;
		if (exists) {
			return new User();
		}
		else {
			return null;
		}
	}

	public User findByUsername(String username) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", username));
		User user = (User)crit.uniqueResult();
		if (user != null) {
			Hibernate.initialize(user.getUserProfiles());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return null;
	}

	public void createUser(User user) {
		UserProfile userProfile = new UserProfile();
		userProfile.setType("USER");
		userProfile.setId(1);
		Set<UserProfile> userProfiles = new HashSet<UserProfile>();
		userProfiles.add(userProfile);
		user.setUserProfiles(userProfiles);
		persist(user);
	}

	public void deleteUser(User user) {
		Criteria crit = createEntityCriteria();
		crit.add(Restrictions.eq("username", user.getUsername()));
		User userTemp = (User)crit.uniqueResult();
		Query query = getSession().createSQLQuery("delete from users_user_profile where users_user_id=:id").setParameter("id", userTemp.getUserId());
		query.executeUpdate();
		query = getSession().createSQLQuery("delete from users_has_events where users_user_id=:id").setParameter("id", userTemp.getUserId());
		query.executeUpdate();
		query = getSession().createQuery("delete User where username = :username");
		query.setParameter("username", user.getUsername());
		query.executeUpdate();
	}

	public void editUser(User user) {
		//All valid fields are being updated
		if (user.getFirstName() != null && user.getLastName() != null && user.getNickname() != null && user.getAge() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, last_name = :last_name, nickname = :nickname, age = :age, phone_number = :phone_number where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("last_name", user.getLastName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("age", user.getAge());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except first name are being updated
		else if (user.getLastName() != null && user.getNickname() != null && user.getAge() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set last_name = :last_name, nickname = :nickname, age = :age, phone_number = :phone_number where username = :username");
			q.setParameter("last_name", user.getLastName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("age", user.getAge());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except last name are being updated
		else if (user.getFirstName() != null && user.getNickname() != null && user.getAge() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, nickname = :nickname, age = :age, phone_number = :phone_number where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("age", user.getAge());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except nickname are being updated
		else if (user.getFirstName() != null && user.getLastName() != null && user.getAge() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, last_name = :last_name, age = :age, phone_number = :phone_number where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("last_name", user.getLastName());
			q.setParameter("age", user.getAge());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except age are being updated
		else if (user.getFirstName() != null && user.getLastName() != null && user.getNickname() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, last_name = :last_name, nickname = :nickname, phone_number = :phone_number where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("last_name", user.getLastName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except phone number are being updated
		else if (user.getFirstName() != null && user.getLastName() != null && user.getNickname() != null && user.getAge() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, last_name = :last_name, nickname = :nickname, age = :age where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("last_name", user.getLastName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("age", user.getAge());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except first name and last name are being updated
		else if (user.getNickname() != null && user.getAge() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set nickname = :nickname, age = :age, phone_number = :phone_number where username = :username");
			q.setParameter("nickname", user.getNickname());
			q.setParameter("age", user.getAge());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except first name and nickname are being updated
		else if (user.getLastName() != null && user.getAge() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set last_name = :last_name, age = :age, phone_number = :phone_number where username = :username");
			q.setParameter("last_name", user.getLastName());
			q.setParameter("age", user.getAge());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except first name and age are being updated
		else if (user.getLastName() != null && user.getNickname() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set last_name = :last_name, nickname = :nickname, phone_number = :phone_number where username = :username");
			q.setParameter("last_name", user.getLastName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except first name and phone number are being updated
		else if (user.getLastName() != null && user.getNickname() != null && user.getAge() != null) {
			Query q = getSession().createQuery("update User set last_name = :last_name, nickname = :nickname, age = :age where username = :username");
			q.setParameter("last_name", user.getLastName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("age", user.getAge());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except last name and nickname are being updated
		else if (user.getFirstName() != null && user.getAge() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, age = :age, phone_number = :phone_number where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("age", user.getAge());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except last name and age are being updated
		else if (user.getFirstName() != null && user.getNickname() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, nickname = :nickname, phone_number = :phone_number where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except last name and phone number are being updated
		else if (user.getFirstName() != null && user.getNickname() != null && user.getAge() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, nickname = :nickname, age = :age where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("age", user.getAge());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except nickname and phone number are being updated
		else if (user.getFirstName() != null && user.getLastName() != null && user.getAge() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, last_name = :last_name, age = :age where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("last_name", user.getLastName());
			q.setParameter("age", user.getAge());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except nickname and age are being updated
		else if (user.getFirstName() != null && user.getLastName() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, last_name = :last_name, phone_number = :phone_number where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("last_name", user.getLastName());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();		
		}
		//All valid fields except age and phone number are being updated
		else if (user.getFirstName() != null && user.getLastName() != null && user.getNickname() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, last_name = :last_name, nickname = :nickname where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("last_name", user.getLastName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except nickname, age, and phone number are being updated
		else if (user.getFirstName() != null && user.getLastName() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, last_name = :last_name where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("last_name", user.getLastName());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except last name, age, and phone number are being updated
		else if (user.getFirstName() != null && user.getNickname() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, nickname = :nickname where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except last name, nickname, and phone number are being updated
		else if (user.getFirstName() != null && user.getAge() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, age = :age where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("age", user.getAge());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All valid fields except last name, nickname, and age are being updated
		else if (user.getFirstName() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name, phone_number = :phone_number where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();		
		}
		//All valid fields except first name, age, and phone number are being updated
		else if (user.getLastName() != null && user.getNickname() != null) {
			Query q = getSession().createQuery("update User set last_name = :last_name, nickname = :nickname where username = :username");
			q.setParameter("last_name", user.getLastName());
			q.setParameter("nickname", user.getNickname());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();		
		}
		//All valid fields except first name, nickname, and phone number are being updated
		else if (user.getLastName() != null && user.getAge() != null) {
			Query q = getSession().createQuery("update User set last_name = :last_name, age = :age where username = :username");
			q.setParameter("last_name", user.getLastName());
			q.setParameter("age", user.getAge());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();				
		}
		//All valid fields except first name, nickname, and age are being updated
		else if (user.getLastName() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set last_name = :last_name, phone_number = :phone_number where username = :username");
			q.setParameter("last_name", user.getLastName());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();				
		}
		//All valid fields except first name, last name, and phone number are being updated
		else if (user.getNickname() != null && user.getAge() != null) {
			Query q = getSession().createQuery("update User set ickname = :nickname, age = :age where username = :username");
			q.setParameter("nickname", user.getNickname());
			q.setParameter("age", user.getAge());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();						
		}
		//All valid fields except first name, last name, and age are being updated
		else if (user.getNickname() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set nickname = :nickname, phone_number = :phone_number where username = :username");
			q.setParameter("nickname", user.getNickname());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();								
		}
		//All valid fields except first name, last name, and nickname are being updated
		else if (user.getAge() != null && user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set age = :age, phone_number = :phone_number where username = :username");
			q.setParameter("age", user.getAge());
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();										
		}
		//Only updating first name
		else if (user.getFirstName() != null) {
			Query q = getSession().createQuery("update User set first_name = :first_name where username = :username");
			q.setParameter("first_name", user.getFirstName());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//Only updating last name
		else if (user.getLastName() != null) {
			Query q = getSession().createQuery("update User set last_name = :last_name where username = :username");
			q.setParameter("last_name", user.getLastName());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();		
		}
		//Only updating nickname
		else if (user.getNickname() != null) {
			Query q = getSession().createQuery("update User set nickname = :nickname where username = :username");
			q.setParameter("nickname", user.getNickname());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();	
		}
		//Only updating age
		else if (user.getAge() != null) {
			Query q = getSession().createQuery("update User set age = :age where username = :username");
			q.setParameter("age", user.getAge());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//Only updating phone number
		else if (user.getPhoneNumber() != null) {
			Query q = getSession().createQuery("update User set phone_number = :phone_number where username = :username");
			q.setParameter("phone_number", user.getPhoneNumber());
			q.setParameter("username", user.getUsername());
			q.executeUpdate();
		}
		//All fields were null
		else {
			logger.error("Invalid request! Nothing was received to update!");
		}

	}

}
