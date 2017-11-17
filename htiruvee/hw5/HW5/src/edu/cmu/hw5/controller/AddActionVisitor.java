package edu.cmu.hw5.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import edu.cmu.hw5.databean.CommentMessage;
import edu.cmu.hw5.databean.PostMessage;
import edu.cmu.hw5.databean.User;
import edu.cmu.hw5.formbean.PostForm;
import edu.cmu.hw5.model.CommentDAO;
import edu.cmu.hw5.model.Model;
import edu.cmu.hw5.model.PostDAO;

public class AddActionVisitor extends Action{
	private FormBeanFactory<PostForm> formBeanFactory = new FormBeanFactory<>(PostForm.class);
	

  
    private PostDAO postDAO;
    private CommentDAO commentDAO;

    public AddActionVisitor(Model model) {
    	
    	postDAO = model.getPostDAO();
    	commentDAO = model.getCommentDAO();
    	
    }

	public String getName() {
		// TODO Auto-generated method stub
		return "addVisitor.do";
	}

	@Override
	public String perform(HttpServletRequest request) {
		System.out.println("Inside add actionVisitorLoggedIn");
		HttpSession session = request.getSession();
		
		if(!request.getMethod().equals("POST")) {
			return null;
		}
		try {
			//post
			System.out.println("Inside add actionVisitorLoggedIn try");
		PostForm form = formBeanFactory.create(request);
		PostMessage post = new PostMessage();
		System.out.println("setPost in add action"+form.getPost());
		String userName = (String) request.getParameter("usersPresent");
        System.out.println("firstName in visiotr's page" +userName);
		
		//comment
		
		CommentMessage comment = new CommentMessage();
		
		String postID= request.getParameter("postID");
		System.out.println("In add action"+postID);
		if(postID!=null) {
			System.out.println("Inside comment section");
			comment.setComment(request.getParameter("comment"));
			comment.setPostID(Integer.parseInt(request.getParameter("postID")));
			comment.setCommentTimeStamp(new Date().toString());
			comment.setUserName(userName);
			System.out.println("From addaction visitor"+userName);
			commentDAO.create(comment);
			
			System.out.println("created comment");
		}
		else {
			post.setPost(form.getPost());
			post.setPostTimeStamp(new Date().toString());
			post.setUserName(((User) request.getSession().getAttribute("user")).getUserName());
			request.setAttribute("form", new PostForm());
			request.setAttribute("posts", postDAO.getAllPosts(userName));
			postDAO.create(post);
		}
	
		return "VisitorLoggedIn.do";
	}
		catch(RollbackException e) {
			request.setAttribute("error",e.getMessage());
            return "error.jsp";
		}
	}

}
