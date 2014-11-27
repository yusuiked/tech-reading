package org.yukung.sandbox.sample.actions;

import com.opensymphony.xwork2.ActionSupport;

public class ExceptionMappingSampleAction extends ActionSupport {

	public String execute() {
		if(true) throw new RuntimeException();
		return SUCCESS;
	}
}
