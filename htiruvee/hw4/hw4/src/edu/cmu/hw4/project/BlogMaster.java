package edu.cmu.hw4.project;

import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import edu.cmu.hw4.dao.MyDAOException;
import edu.cmu.hw4.dao.UserDAO;
import edu.cmu.hw4.databean.User;
import edu.cmu.hw4.formBean.LoginForm;
import edu.cmu.hw4.formBean.RegisterForm;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/BlogMaster")
public class BlogMaster extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BlogMaster() {
        super();
        // TODO Auto-generated constructor stub
    }

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
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        

        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors", errors);
        
        List<String> listOfUsers = new ArrayList<String>();
        request.setAttribute("users", listOfUsers);
        
        try {
        		//request.setAttribute("form", UserDAO.getUser(user.getUserName()));
        		listOfUsers.addAll(userDAO.getAllUsers());
	        	if (user == null) {
	        		 RequestDispatcher d = request.getRequestDispatcher("Login");
	                 d.forward(request, response);
	                return;
	            }
            RegisterForm form = new RegisterForm(request);
            request.setAttribute("form", form);
            System.out.println(request.getMethod());
            
            if ("GET".equals(request.getMethod())) {
                RequestDispatcher d = request.getRequestDispatcher("HomePage.jsp");
                d.forward(request, response);
                
                return;
            }
            errors.addAll(form.getValidationErrors());
            if (errors.size() > 0) {
                RequestDispatcher d = request.getRequestDispatcher("HomePage.jsp");
                d.forward(request, response);
                return;
            }
            
          
        } 
        catch (Exception e) {
            errors.add(e.getMessage());
            RequestDispatcher d =  request.getRequestDispatcher("errors.jsp");
            d.forward(request, response);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
