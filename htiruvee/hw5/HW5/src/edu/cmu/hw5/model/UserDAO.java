package edu.cmu.hw5.model;

import java.util.ArrayList;
import java.util.List;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.RollbackException;

import edu.cmu.hw5.databean.PostMessage;
import edu.cmu.hw5.databean.User;

public class UserDAO extends GenericDAO<User> {
	
	public UserDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(User.class, tableName, cp);
	}
	
	public User[] readListOfUsers() throws RollbackException {
		
		
		System.out.println("UserDAO : Test");
		User[] userslist = match();
		
        return userslist;
	}
}
