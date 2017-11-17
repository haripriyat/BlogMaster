package edu.cmu.hw5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.genericdao.RollbackException;

import edu.cmu.hw5.databean.CommentMessage;
import edu.cmu.hw5.databean.PostMessage;
import edu.cmu.hw5.databean.User;
import edu.cmu.hw5.formbean.LoginForm;
import edu.cmu.hw5.model.CommentDAO;
import edu.cmu.hw5.model.Model;
import edu.cmu.hw5.model.PostDAO;
import edu.cmu.hw5.model.UserDAO;

public class HomePageAction extends Action {
	private PostDAO postDAO;
	private UserDAO userDAO;
	private CommentDAO commentDAO;

    public HomePageAction(Model model) {
    	postDAO = model.getPostDAO();
    	userDAO = model.getUserDAO();
    	commentDAO = model.getCommentDAO();
    }

    public String getName() {
        return "HomePage.do";
    }

    public String perform(HttpServletRequest request) {
	    	 HttpSession session = request.getSession();   
	    	System.out.println("HomePageAction");
	    	 
	    	try {
	    		
	    User[] userslist = userDAO.readListOfUsers();
        request.setAttribute("listOfUsers",userslist);
        
        User userName= (User) request.getSession().getAttribute("user");
        System.out.println("In home action"+userName);
        
        PostMessage[] posts = postDAO.getAllPosts(userName.getUserName());
        System.out.println("size"+posts.length);
        request.setAttribute("listOfPost", posts);
        CommentMessage[] comments = commentDAO.getAllComments();
        System.out.println("size"+comments.length);
        request.setAttribute("comments", comments);
        
        
        if (request.getMethod().equals("GET")) {
            return "HomePage.jsp";
        }
        
        return null;
        }
       
        catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        
		}
    }
}