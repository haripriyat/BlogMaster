package edu.cmu.hw5.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;


public class RegisterForm extends FormBean{
	private String firstName;
    private String lastName;
	private String userName;
    private String password;
    private String confirmPassword;
    private String button;

    public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
        
        if (userName.matches(".*[<>\"].*")) {
            errors.add("User Name may not contain angle brackets or quotes");
        }
        
        if (!password.equals(confirmPassword)) {
            errors.add("Passwords Do Not Match");
        }
        
//        if (button == null) {
//            errors.add("Action is required");
//        }

        if (errors.size() > 0) {
            return errors;
        }


        return errors;
    }

	
}