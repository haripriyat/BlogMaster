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
import edu.cmu.hw5.formbean.RegisterForm;
import edu.cmu.hw5.model.Model;
import edu.cmu.hw5.model.UserDAO;

public class RegisterAction extends Action {
	
    private FormBeanFactory<RegisterForm> formBeanFactory = new FormBeanFactory<>(RegisterForm.class);
    
    private UserDAO userDAO;

    public RegisterAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "Register.do";
    }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();
        
        try {
        	
        	if (session.getAttribute("user") != null) {
	            return "HomePage.do";
	        }
	        
        	 User[] userslist = userDAO.readListOfUsers();
     	    request.setAttribute("listOfUsers",userslist);
	        
	        if (request.getMethod().equals("GET")) {
	            request.setAttribute("form", new RegisterForm());
	            return "Register.jsp";
	        }
        	RegisterForm form = formBeanFactory.create(request);
        	request.setAttribute("form", form);
        	
        	
        	User newUser = new User();
        	newUser.setFirstName(form.getFirstName());
        	newUser.setLastName(form.getLastName());
        	newUser.setUserName(form.getUserName());
        	newUser.setPassword(form.getPassword());
        	
        	List<String> errors = new ArrayList<String>();
			request.setAttribute("errors", errors);
			errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            		System.out.println("found errors in reg");
            		for(int i=0;i<errors.size();i++) {
            			System.out.println(errors.get(i));
            		}
                return "Register.jsp";
                
            	} 
           
        	
        	try {
                userDAO.create(newUser);
                session.setAttribute("user", newUser);
                return ("HomePage.do");
            } catch (DuplicateKeyException e) {
                errors.add("A user with this name already exists");
                return "Register.jsp";
            }
        	
        }
        catch (RollbackException e){
        		request.setAttribute("errors", e.getMessage());
        		return "errors.jsp";
        }
		
        
    }

}