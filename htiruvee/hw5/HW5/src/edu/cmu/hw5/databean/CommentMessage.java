package edu.cmu.hw5.databean;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.genericdao.PrimaryKey;

@PrimaryKey("commentID")
public class CommentMessage {
	private int commentID;
	private String comment;
	private String commentTimeStamp;
	private String userName;
	private int postID;
	
	
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
