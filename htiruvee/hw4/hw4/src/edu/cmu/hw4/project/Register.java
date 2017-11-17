package edu.cmu.hw4.project;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.cmu.hw4.dao.MyDAOException;
import edu.cmu.hw4.dao.UserDAO;
import edu.cmu.hw4.databean.User;
import edu.cmu.hw4.formBean.RegisterForm;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private UserDAO userDAO;

    public void init() throws ServletException {
        ServletContext context = getServletContext();
        String jdbcDriverName = context.getInitParameter("jdbcDriverName");
        String jdbcURL = context.getInitParameter("jdbcURL");

        try {
            userDAO = new UserDAO(jdbcDriverName, jdbcURL, "htiruvee_user");
        } catch (MyDAOException e) {
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		System.out.println("Test");
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        System.out.println(session.getAttribute("user"));
        
        
        List<String> listOfUsers = new ArrayList<String>();
        request.setAttribute("users", listOfUsers);


        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);

        try {
        	
	        	listOfUsers.addAll(userDAO.getAllUsers());
	        	if (session.getAttribute("user") != null) {
	                response.sendRedirect("BlogMaster");
	                return;
	            }
        		System.out.println("Inside register");
            RegisterForm form = new RegisterForm(request);
            request.setAttribute("form", form);
            
            if ("GET".equals(request.getMethod())) {
            		System.out.println("Going to get method of reg");
            		
                RequestDispatcher d = request.getRequestDispatcher("Register.jsp");
                d.forward(request, response);
                return;
            }

            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
            		System.out.println("found errors in reg");
            		for(int i=0;i<errors.size();i++) {
            			System.out.println(errors.get(i));
            		}
                RequestDispatcher d = request.getRequestDispatcher("Register.jsp");
                d.forward(request, response);
                return;
            }

            if (form.getButton().equals("Register")) {
            		System.out.println("Inside register form");
                User user = new User();
                user.setFirstName(form.getFirstName());
                user.setLastName(form.getLastName());
                user.setUserName(form.getUserName());
                user.setPassword(form.getPassword());
                
                User existingUser = userDAO.read(form.getUserName());
                if (existingUser != null) {
                    errors.add("User already exists with userName "+form.getUserName());
                    RequestDispatcher d = request.getRequestDispatcher("Register.jsp");
                    d.forward(request, response);
                    return;
                }
                else {
                	System.out.println("Creating user");
                		userDAO.create(user);
                		System.out.println("After creating");
                }
                
                session.setAttribute("user", user);
                System.out.println("session : "+session.getAttribute("user"));
                response.sendRedirect("BlogMaster");
            }


        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("errors.jsp");
            d.forward(request, response);
        }
    }
}