package org.yukung.sandbox.sample.actions;

import com.opensymphony.xwork2.ActionSupport;

public class ZeroConfigSampleAction extends ActionSupport {

	public String execute() {
		System.out.println("zeroconfig!");
		return SUCCESS;
	}

}
