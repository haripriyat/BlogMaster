package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.formbean.IdForm;
import edu.cmu.cs.webapp.todolist6.formbean.ItemForm;
import edu.cmu.cs.webapp.todolist6.model.ItemDAO;
import edu.cmu.cs.webapp.todolist6.model.Model;

public class DeleteAction extends Action {
    private FormBeanFactory<PostForm> formBeanFactory = new FormBeanFactory<>(PostForm.class);

    private PostDAO postDAO;

	public DeleteAction(Model model) {
		postDAO = model.getPostDAO();
	}

	public String getName() {
		return "delete.do";
	}

	public String perform(HttpServletRequest request) {
        PostForm form = formBeanFactory.create(request);
        
        if (form.hasValidationErrors()) {
            return "error.jsp";
        }

		try {
			postDAO.delete(form.getIdAsInt());
			request.getAttribute("postID");
			
           
			return "HomePage.jsp";

        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
	}
}
