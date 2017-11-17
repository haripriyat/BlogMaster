package edu.cmu.hw5.formbean;


import org.formbeanfactory.FieldOrder;
import org.formbeanfactory.FormBean;
import org.formbeanfactory.Label;


public class PostForm extends FormBean {
	private String post;
	private String postTimeStamp;
	private String userName;
	private String Action;
	
	
	public String getAction() {
		return Action;
	}
	public void setAction(String action) {
		Action = action;
	}
	public String getPost() {
		return post;
	}
	@Label("post:")
	public void setPost(String post) {
		this.post = post;
	}
	public String getPostTimeStamp() {
		return postTimeStamp;
	}
	public void setPostTimeStamp(String postTimeStamp) {
		this.postTimeStamp = postTimeStamp;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}


