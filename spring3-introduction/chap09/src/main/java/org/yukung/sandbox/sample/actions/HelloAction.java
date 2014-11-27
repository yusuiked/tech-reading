package org.yukung.sandbox.sample.actions;

public class HelloAction {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String execute() {
		message = "World!";
		return "success";
	}
}
