package edu.cmu.hw5.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import edu.cmu.hw5.formbean.LoginForm;
import edu.cmu.hw5.model.Model;


/*
 * Logs out by setting the "user" session attribute to null.
 * (Actions don't be much simpler than this.)
 */
public class LogoutAction extends Action {

    public LogoutAction(Model model) {
    }

    public String getName() {
        return "Logout.do";
    }

    public String perform(HttpServletRequest request) {
    	   
        HttpSession session = request.getSession(false);
        session.setAttribute("user", null);
        
       
        request.setAttribute("form", new LoginForm());
        return "Login.do";
   
    	    
}
}