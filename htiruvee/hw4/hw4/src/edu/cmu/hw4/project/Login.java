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
import edu.cmu.hw4.formBean.LoginForm;


@WebServlet("/Login")
public class Login extends HttpServlet {

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
        

        List<String> users = new ArrayList<String>();
        request.setAttribute("user", users);
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
        List<String> listOfUsers = new ArrayList<String>();
        request.setAttribute("users", listOfUsers);
        
   

        try {
        		listOfUsers.addAll(userDAO.getAllUsers());
        		
        		for(String user : listOfUsers) {
        			System.out.println("User - Login : "+ user);
        		}
        	
	        	if (session.getAttribute("user") != null) {
	                response.sendRedirect("BlogMaster");
	                return;
	            }
            LoginForm form = new LoginForm(request);
            request.setAttribute("form", form);
            System.out.println("Inside Login");
            
            if ("GET".equals(request.getMethod())) {
            		System.out.println("Going to get method of login");
                RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
                d.forward(request, response);
                return;
            }

            errors.addAll(form.getValidationErrors());
            if (errors.size() != 0) {
	            	System.out.println("found errors in login");
	        		for(int i=0;i<errors.size();i++) {
	        			System.out.println(errors.get(i));
	        		}
                RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
                d.forward(request, response);
                return;
            }

            User user = userDAO.read(form.getUserName());
            if (user == null) {
                errors.add("No such user");
                RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
                d.forward(request, response);
                return;
            }

            if (!form.getPassword().equals(user.getPassword())) {
                errors.add("Incorrect password");
                RequestDispatcher d = request.getRequestDispatcher("Login.jsp");
                d.forward(request, response);
                return;
            }
            
            
          
            session.setAttribute("user", user);
            System.out.println("session : "+session.getAttribute("user"));
            response.sendRedirect("BlogMaster");

        } catch (MyDAOException e) {
            errors.add(e.getMessage());
            RequestDispatcher d = request.getRequestDispatcher("errors.jsp");
            d.forward(request, response);
        }
    }
}