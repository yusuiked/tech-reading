package org.yukung.sandbox.sample.actions;

import com.opensymphony.xwork2.ActionSupport;

public class MultiAction extends ActionSupport {
	public String foo() {
		System.out.println("foo called");
		return SUCCESS;
	}
	public String bar() {
		System.out.println("bar called");
		return SUCCESS;
	}
}
