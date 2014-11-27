package org.yukung.sandbox.sample.actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

@Namespace("/greeting")
public class HelloAction {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Action(value="hi", results={@Result(name="success", location="/WEB-INF/jsp/hi.jsp")})
	public String execute() {
		message = "World!";
		return "success";
	}
}
