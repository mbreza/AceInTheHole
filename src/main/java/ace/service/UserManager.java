package ace.service;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import ace.domain.User;
import ace.domain.UserRole;
import ace.server.UserDAO;
import ace.server.UserRoleDAO;


public class UserManager implements UserDAO {
	String json;
	private static SessionFactory factory;		
	UserRoleDAO userRoleManager = new UserRoleManager();	
	
	@Override
    @SuppressWarnings("unchecked")
	public boolean logIn(String login, String password){
		
	    factory = new Configuration().configure().buildSessionFactory();
	    Session session = factory.openSession();
	    Transaction trans = null;
	    
	    try {
	    	 trans = session.beginTransaction();
	    	 
	    	 Criteria cr = session.createCriteria(User.class);
	    	 cr.add(Restrictions.eq("login", login));
	    	 List <User> users = cr.list();
	    	 
	    	 for (User user : users) {
	    		 if( user.getPassword().equals(password)) {
	    			 return true;
	    		 } else {
	    			 return false;
	    		 }
	    	 }
	    	 
	         trans.commit();
	      } catch (HibernateException e) {
	         if (trans!=null) trans.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
		
		
		
		return false;
	}
	
	
	@Override
	public Integer addUser(String login, String password, String email, int ID){
		
  	      factory = new Configuration().configure().buildSessionFactory();
	      Session session = factory.openSession();
	      Transaction trans = null;
	      Integer id_user = null;
	      
	      try {
	    	 UserRole rola = userRoleManager.getUserRoleByID(ID);
	    	 trans = session.beginTransaction();
	         User user = new User(login, password, email, rola);
	         id_user = (Integer) session.save(user); 
	         trans.commit();
	      } catch (HibernateException e) {
	         if (trans!=null) trans.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return id_user;
	   }
	

    @Override
    @SuppressWarnings("unchecked")
	public String getAllUsers(){
    	
    	  factory = new Configuration().configure().buildSessionFactory();
	      Session session = factory.openSession();
	      Transaction trans = null;
	      
	      try {
	    	 trans = session.beginTransaction();  	 
	    	 List<User> users = session.createQuery("FROM User").list();
	    	 for (User user : users) {
	    		 json = user.getLast_name();
	    	 }
	         trans.commit();
	         
	      } catch (HibernateException e) {
	         if (trans!=null) trans.rollback();
	         e.printStackTrace(); 
	      } finally {
	         session.close(); 
	      }
	      return json;
    }
  
}
