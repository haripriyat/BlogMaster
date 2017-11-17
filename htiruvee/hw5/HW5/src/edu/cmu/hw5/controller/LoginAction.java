package edu.cmu.hw5.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import edu.cmu.hw5.databean.User;
import edu.cmu.hw5.formbean.LoginForm;
import edu.cmu.hw5.model.Model;
import edu.cmu.hw5.model.UserDAO;

public class LoginAction extends Action {
	
    private FormBeanFactory<LoginForm> formBeanFactory = new FormBeanFactory<>(LoginForm.class);
    
    private UserDAO userDAO;

    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "Login.do";
    }
    
  

    
    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        // If user is already logged in, redirect to todolist.do
       
		try {
			// Look up the user
			
			 if (session.getAttribute("user") != null) {
		            return "HomePage.do";
		        }
		        
			 	User[] userslist = userDAO.readListOfUsers();
			    request.setAttribute("listOfUsers",userslist);
		     
		        for(User emailId : userslist) {
		        		System.out.println("Printing list in login action:"+emailId.getUserName());
		        }
		        System.out.println("login action - list of users size"+userslist.length);
		        request.setAttribute("listOfUsers",userslist);
					
		        
		        if (request.getMethod().equals("GET")) {
		            request.setAttribute("form", new LoginForm());
		            return "Login.jsp";
		        }
			
			LoginForm form = formBeanFactory.create(request);
	        request.setAttribute("form", form);
            User user = userDAO.read(form.getUserName());
            
            
            List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);
			errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            		System.out.println("found errors in reg");
            		for(int i=0;i<errors.size();i++) {
            			System.out.println(errors.get(i));
            		}
                return "Login.jsp";
                
            	} 
            
            if (user == null) {
                errors.add( "User Name not found");
                return "Login.jsp";
            }

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
            		errors.add( "Incorrect password");
                return "Login.jsp";
            }
            
            System.out.println("user session attribute userName : " + user.getUserName());
            System.out.println("user session attribute password : " + user.getPassword());
            
            // Attach (this copy of) the user bean to the session
            session.setAttribute("user", user);

            // If redirectTo is null, redirect to the "todolist" action
            return "HomePage.do";
            
            
		}catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "errors.jsp";
        
		}

        
    }

}