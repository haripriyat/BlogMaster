package edu.cmu.hw5.formbean;

import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;

public class CommentForm extends FormBean{
	private int commentID;
	private String comment;
	private String commentTimeStamp;
	private String userName;
	private int postID;
//	private String commentAction;
//	
//	
//	public String getCommentAction() {
//		return commentAction;
//	}
//	public void setCommentAction(String commentAction) {
//		commentAction = commentAction;
//	}
	
	public int getCommentID() {
		return commentID;
	}
	public void setCommentID(int commentID) {
		this.commentID = commentID;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getCommentTimeStamp() {
		return commentTimeStamp;
	}
	public void setCommentTimeStamp(String commentTimeStamp) {
		this.commentTimeStamp = commentTimeStamp;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
}
