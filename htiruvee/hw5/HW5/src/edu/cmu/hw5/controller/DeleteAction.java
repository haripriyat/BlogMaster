package edu.cmu.hw5.controller;

import javax.servlet.http.HttpServletRequest;

import org.formbeanfactory.FormBeanFactory;
import org.genericdao.RollbackException;

import edu.cmu.hw5.formbean.PostForm;
import edu.cmu.hw5.model.CommentDAO;
import edu.cmu.hw5.model.Model;
import edu.cmu.hw5.model.PostDAO;

public class DeleteAction extends Action {
    private FormBeanFactory<PostForm> formBeanFactory = new FormBeanFactory<>(PostForm.class);

    private PostDAO postDAO;
    private CommentDAO commentDAO;

	public DeleteAction(Model model) {
		postDAO = model.getPostDAO();
		commentDAO = model.getCommentDAO();
	}

	public String getName() {
		return "delete.do";
	}

	public String perform(HttpServletRequest request) {
        PostForm form = formBeanFactory.create(request);
        
//        if (form.hasValidationErrors()) {
//            return "error.jsp";
//        }

		try {
			System.out.println("Delete Actio entering");
			String deletedPost =  request.getParameter("postID");
			System.out.println("Delete Action running query"+deletedPost);
			if(deletedPost==null) {
				System.out.println("entering here postid is null");
				String commentPost = request.getParameter("commentID");
				System.out.println("Commentid "+commentPost);
				commentDAO.delete(Integer.parseInt(commentPost));
				System.out.println("comment deleted");
			}
			else {
				System.out.println("entering here commentid is null");
				postDAO.delete(Integer.parseInt(deletedPost));
				System.out.println("Delete Action returning to home");
			}
			
			return "HomePage.do";

        } catch (RollbackException e) {
            request.setAttribute("error",e.getMessage());
            return "error.jsp";
        }
	}
}