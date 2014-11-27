package org.yukung.sandbox.sample.actions;

import com.opensymphony.xwork2.ActionSupport;

public class InterceptorSampleAction extends ActionSupport {

	public String execute() {
		System.out.println("Actionを実行しました");
		return SUCCESS;
	}
}
