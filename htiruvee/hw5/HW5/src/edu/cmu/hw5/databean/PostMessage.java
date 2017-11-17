package edu.cmu.hw5.databean;


import org.genericdao.PrimaryKey;

@PrimaryKey("postID")
public class PostMessage {
	private int postID;
	private String post;
	private String postTimeStamp;
	private String userName;
	
	public int getPostID() {
		return postID;
	}
	public void setPostID(int postID) {
		this.postID = postID;
	}
	public String getPost() {
		return post;
	}
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
