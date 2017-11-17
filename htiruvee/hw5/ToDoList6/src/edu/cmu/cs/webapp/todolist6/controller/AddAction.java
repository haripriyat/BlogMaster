package edu.cmu.cs.webapp.todolist6.controller;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import edu.cmu.cs.webapp.todolist6.databean.ItemBean;
import edu.cmu.cs.webapp.todolist6.databean.User;
import edu.cmu.cs.webapp.todolist6.formbean.ItemForm;
import edu.cmu.cs.webapp.todolist6.model.ItemDAO;
import edu.cmu.cs.webapp.todolist6.model.Model;

public class AddAction extends Action {
    private FormBeanFactory<ItemForm> formBeanFactory = new FormBeanFactory<>(ItemForm.class);

    private ItemDAO itemDAO;

    public AddAction(Model model) {
        itemDAO = model.getItemDAO();
    }

    public String getName() {
        return "add.do";
    }

    public String perform(HttpServletRequest request) {
        if (!request.getMethod().equals("POST")) {
            return null;  // Sends back a 404
        }

        try {
            ItemForm form = formBeanFactory.create(request);
            if (form.hasValidationErrors()) {
                request.setAttribute("form", form);
                request.setAttribute("items", itemDAO.getItems());
                return "todolist.jsp";
            }

            ItemBean bean = new ItemBean();
            bean.setItem(form.getItem());
            bean.setIpAddress(request.getRemoteAddr());
            bean.setUserName(((User) request.getSession().getAttribute("user")).getUserName());

            if (form.getAction().equals("top")) {
                itemDAO.addToTop(bean);
            } else {
                itemDAO.addToBottom(bean);
            }

            request.setAttribute("form", new ItemForm());
            request.setAttribute("items", itemDAO.getItems());

            return "todolist.jsp";

        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
    }
}
