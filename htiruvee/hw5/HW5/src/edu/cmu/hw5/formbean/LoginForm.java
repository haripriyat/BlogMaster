package edu.cmu.hw5.formbean;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;

public class LoginForm extends FormBean{
    private String userName;
    private String password;
    private String button;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setButton(String button) {
		this.button = button;
	}

	public String getPassword() {
        return password;
    }

    public String getButton() {
        return button;
    }

    public List<String> getValidationErrors() {
        List<String> errors = new ArrayList<String>();
        
 
        if (userName == null || userName.length() == 0) {
            errors.add("User Name is required");
        }
        
        
        if (password == null || password.length() == 0) {
            errors.add("Password is required");
        }
        
        if (!userName.matches("^(.+)@(.+)$")) {
            errors.add("User Name should be of type email : x@y.z ");
        }
        
        if (button == null) {
            errors.add("Action is required");
        }

        if (errors.size() > 0) {
            return errors;
        }

        if (userName.matches(".*[<>\"].*")) {
            errors.add("User Name may not contain angle brackets or quotes");
        }

        return errors;
    }
}