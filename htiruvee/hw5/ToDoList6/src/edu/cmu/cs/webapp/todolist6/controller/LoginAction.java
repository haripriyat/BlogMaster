package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.DuplicateKeyException;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.databean.User;
import edu.cmu.cs.webapp.todolist6.formbean.LoginForm;
import edu.cmu.cs.webapp.todolist6.model.Model;
import edu.cmu.cs.webapp.todolist6.model.UserDAO;

public class LoginAction extends Action {
    private FormBeanFactory<LoginForm> formBeanFactory = new FormBeanFactory<>(LoginForm.class);
    
    private UserDAO userDAO;

    public LoginAction(Model model) {
        userDAO = model.getUserDAO();
    }

    public String getName() {
        return "login.do";
    }

    public String perform(HttpServletRequest request) {
        HttpSession session = request.getSession();

        // If user is already logged in, redirect to todolist.do
        if (session.getAttribute("user") != null) {
            return "todolist.do";
        }
        
        if (request.getMethod().equals("GET")) {
            request.setAttribute("form", new LoginForm());
            return "login.jsp";
        }

        try {
            LoginForm form = formBeanFactory.create(request);
            request.setAttribute("form", form);

            // Any validation errors?
            if (form.hasValidationErrors()) {
                return "login.jsp";
            }

            if (form.getAction().equals("Register")) {
                User newUser = new User();
                newUser.setUserName(form.getUserName());
                newUser.setPassword(form.getPassword());
                try {
                    userDAO.create(newUser);

                    session.setAttribute("user", newUser);
                    return ("todolist.do");
                } catch (DuplicateKeyException e) {
                    form.addFieldError("userName", "A user with this name already exists");
                    return "login.jsp";
                }
            }

            // Look up the user
            User user = userDAO.read(form.getUserName());

            if (user == null) {
                form.addFieldError("userName", "User Name not found");
                return "login.jsp";
            }

            // Check the password
            if (!user.getPassword().equals(form.getPassword())) {
                form.addFieldError("password", "Incorrect password");
                return "login.jsp";
            }

            // Attach (this copy of) the user bean to the session
            session.setAttribute("user", user);

            // If redirectTo is null, redirect to the "todolist" action
            return "todolist.do";
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
