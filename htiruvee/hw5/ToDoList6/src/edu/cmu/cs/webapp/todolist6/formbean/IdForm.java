package edu.cmu.cs.webapp.todolist6.formbean;

import org.formbeanfactory.FormBean;

public class IdForm extends FormBean {
	private String id;

	public String getId() { return id;    }
	
	public int getIdAsInt() {
		// The call validate() to ensures that errors will be detected before
		//  NullPointerException or NumberFormatException are thrown!
		return Integer.parseInt(id);
	}
	
	public void setId(String id) { this.id = id; }

	public void validate() {
	    super.validate();

		try {
			Integer.parseInt(id);
		} catch (NumberFormatException e) {
			this.addFormError("Id is not an integer");
		}
	}
}
