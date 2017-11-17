package edu.cmu.hw5.model;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.hw5.databean.CommentMessage;
import edu.cmu.hw5.databean.PostMessage;

public class CommentDAO extends GenericDAO<CommentMessage> {
	
	public CommentDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(CommentMessage.class, tableName, cp);
	}
	
	public CommentMessage[] getAllComments() throws RollbackException {
		
		CommentMessage[] comments = match();
			System.out.println("postdao posts are printing..");
			
			for(CommentMessage comment:comments) {
					System.out.println("postDAO:"+comment.getComment());
				
			}
			
			return comments;
		}
}
	