package edu.cmu.hw5.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.genericdao.ConnectionPool;
import org.genericdao.DAOException;
import org.genericdao.GenericDAO;
import org.genericdao.MatchArg;
import org.genericdao.RollbackException;

import edu.cmu.hw5.databean.PostMessage;
import edu.cmu.hw5.databean.User;

public class PostDAO extends GenericDAO<PostMessage> {

	public PostDAO(ConnectionPool cp, String tableName) throws DAOException {
		super(PostMessage.class, tableName, cp);
		
	}
	
	public PostMessage[] getAllPosts(String userName) throws RollbackException {
		
		PostMessage[] posts = match(MatchArg.equals("userName", userName));
		System.out.println("postdao posts are printing..");
		
		if(posts!=null) {
			List<PostMessage> postList = (List<PostMessage>) Arrays.asList(posts);
			Collections.reverse(postList);
			posts = postList.toArray(new PostMessage[postList.size()]);
			
			for(PostMessage post:posts) {
				System.out.println("postDAO:"+post.getPost());
			
		}
		}
		
		
		
		return posts;
	}
}
	