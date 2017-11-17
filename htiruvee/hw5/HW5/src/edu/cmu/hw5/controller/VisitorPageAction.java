package edu.cmu.hw5.controller;

import java.util.ArrayList;
import java.util.List;

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
import edu.cmu.hw5.model.UserDAO;

public class VisitorPageAction extends Action{
	
	private FormBeanFactory<PostForm> formBeanFactory = new FormBeanFactory<>(PostForm.class);

	private PostDAO postDAO;
	private UserDAO userDAO;
	private CommentDAO commentDAO;

    public VisitorPageAction(Model model) {
    	postDAO = model.getPostDAO();
    	userDAO = model.getUserDAO();
    	commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "VisitorPage.do";
    }

    public String perform(HttpServletRequest request) {
	    	 HttpSession session = request.getSession();   
	    	System.out.println("VisitorPageAction");
	    	 if (!request.getMethod().equals("POST")) {
	         	
	             return "VisitorPage.jsp";
	         }
	    	 
	    	 
	    	try {
	    		
	    User[] userslist = userDAO.readListOfUsers();
	    request.setAttribute("listOfUsers",userslist);
        System.out.println("Coming here Visitor Action");
        request.setAttribute("listOfUsers",userslist);
        System.out.println("Coming here Visitor Action set attr");
        
        String userName = (String) request.getParameter("usersPresent");
        System.out.println("firstName in visiotr's page" +userName);
        User visitorName = userDAO.read(userName);
        request.setAttribute("visitorNamePage", visitorName);
        
       
        System.out.println("In home action"+userName);
        PostMessage[] posts = postDAO.getAllPosts(visitorName.getUserName());
        System.out.println("size"+posts.length);
        request.setAttribute("listOfPost", posts);
        CommentMessage[] comments = commentDAO.getAllComments();
        System.out.println("size"+comments.length);
        request.setAttribute("comments", comments);
        
        if(session.getAttribute("user") == null) {
   		 System.out.println("VisitorPageAction : Forwarding to visitor page not logged in");
   		 return "VisitorPage.jsp";
   	 }
        
        return "VisitorPage.jsp";
        }
       
        catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "errors.jsp";
        
		}
    }
}