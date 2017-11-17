package edu.cmu.hw4.formBean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class RegisterForm {
	private String firstName;
    private String lastName;
	private String userName;
    private String password;
    private String confirmPassword;
    private String button;

    public RegisterForm(HttpServletRequest request) {
    		firstName = request.getParameter("firstName");
    		lastName = request.getParameter("lastName");
        userName = request.getParameter("userName");
        password = request.getParameter("password");
        confirmPassword = request.getParameter("confirmPassword");
        button = request.getParameter("button");
        System.out.println("firstName : "+firstName);
        System.out.println("lastName : "+lastName);
        System.out.println("userName : "+userName);
        System.out.println("password : "+password);
        System.out.println("confirmPassword : "+confirmPassword);
        System.out.println("button : "+button);
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public String getLastName() {
        return lastName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
    
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public String getButton() {
        return button;
    }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        
        
        if (firstName == null || firstName.length() == 0) {
            errors.add("First Name is required");
        }
        
        if (lastName == null || lastName.length() == 0) {
            errors.add("Last Name is required");
        }

        if (userName == null || userName.length() == 0) {
            errors.add("User Name is required");
        }
        
        if (!userName.matches("^(.+)@(.+)$")) {
            errors.add("User Name should be of type email : x@y.z ");
        }
        
        if (password == null || password.length() == 0) {
            errors.add("Password is required");
        }
        
        if (confirmPassword == null || confirmPassword.length() == 0) {
            errors.add("Confirm Password is required");
        }
        
        if (button == null) {
            errors.add("Action is required");
        }

        if (errors.size() > 0) {
            return errors;
        }

        if (!password.equals(confirmPassword)) {
            errors.add("Passwords Do Not Match");
        }
        
        if (userName.matches(".*[<>\"].*")) {
            errors.add("User Name may not contain angle brackets or quotes");
        }

        return errors;
    }
}