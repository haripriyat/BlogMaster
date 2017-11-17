package edu.cmu.cs.webapp.todolist6.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.formbean.ItemForm;
import edu.cmu.cs.webapp.todolist6.model.ItemDAO;
import edu.cmu.cs.webapp.todolist6.model.Model;

public class ToDoListAction extends Action {
	private ItemDAO itemDAO;

    public ToDoListAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() {
        return "todolist.do";
    }

    public String perform(HttpServletRequest request) {
        if (!request.getMethod().equals("GET")) {
            return null;  // Sends back 404
        }
        
        List<String> errors = new ArrayList<String>();
        request.setAttribute("errors",errors);
        
        try {
       		request.setAttribute("items", itemDAO.getItems());
            request.setAttribute("form",  new ItemForm());
       		return ("todolist.jsp");
        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
