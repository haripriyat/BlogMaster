package edu.cmu.hw5.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.cmu.hw5.databean.User;
import edu.cmu.hw5.model.Model;


public class Controller extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void init() throws ServletException {
        Model model = new Model(getServletConfig());
        Action.add(new LoginAction(model));
        Action.add(new AddAction(model));
        Action.add(new LogoutAction(model));
        Action.add(new RegisterAction(model));
        Action.add(new HomePageAction(model));
        Action.add(new VisitorPageAction(model));
        Action.add(new DeleteAction(model));
        Action.add(new DeleteActionVisitor(model));
        Action.add(new AddActionVisitor(model));
        Action.add(new VisitorLoggedInAction(model));

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nextPage = performTheAction(request);
        sendToNextPage(nextPage, request, response);
    }

    /*
     * Extracts the requested action and (depending on whether the user is
     * logged in) perform it (or make the user login).
     * 
     * @param request
     * 
     * @return the next page (the view)
     */
    private String performTheAction(HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        String servletPath = request.getServletPath();
        User user = (User) session.getAttribute("user");
        System.out.println("USER SESSION ATTRIBUTE : " + user);
        String action = getActionName(servletPath);
        System.out.println("Controlelr.java : Under perform function");
        
        if("Register".equals(request.getParameter("PageSelected"))) {
        		System.out.println("Going to register page");
        		return Action.perform("Register.do", request);
        }
        
        if("VisitorPage".equals(request.getParameter("PageSelectedVisitor"))) {
    		System.out.println("Going to Visitor page not logged in");
    		return Action.perform("VisitorPage.do", request);
        	}
        
        if("VisitorPageLogged".equals(request.getParameter("PageSelectedLogged"))) {
    		System.out.println("Going to Visitor page");
    		return Action.perform("VisitorLoggedIn.do", request);
        	}
        
        
        if (user == null) {
            // If the user hasn't logged in, so login is the only option
            return Action.perform("Login.do", request);
        }

        // Let the logged in user run his chosen action
        return Action.perform(action, request);
    }

    /*
     * If nextPage is null, send back 404 If nextPage ends with ".do", redirect
     * to this page. If nextPage ends with ".jsp", dispatch (forward) to the
     * page (the view) This is the common case
     */
    private void sendToNextPage(String nextPage, HttpServletRequest request,
            HttpServletResponse response) throws IOException, ServletException {
        if (nextPage == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND,
                    request.getServletPath());
            return;
        }

        if (nextPage.endsWith(".do")) {
            response.sendRedirect(nextPage);
            return;
        }

        if (nextPage.endsWith(".jsp")) {
            RequestDispatcher d = request.getRequestDispatcher("WEB-INF/"
                    + nextPage);
            d.forward(request, response);
            return;
        }

        throw new ServletException(Controller.class.getName()
                + ".sendToNextPage(\"" + nextPage + "\"): invalid extension.");
    }

    /*
     * Returns the path component after the last slash removing any "extension"
     * if present.
     */
    private String getActionName(String path) {
        // We're guaranteed that the path will start with a slash
        int slash = path.lastIndexOf('/');
        return path.substring(slash + 1);
    }
}