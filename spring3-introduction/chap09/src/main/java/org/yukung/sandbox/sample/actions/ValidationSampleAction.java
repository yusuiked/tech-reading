package org.yukung.sandbox.sample.actions;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionSupport;

public class ValidationSampleAction extends ActionSupport {
	private String userName;
	private String password;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() {
		System.out.println(userName);
		System.out.println(password);
		return SUCCESS;
	}

//	アクションクラス名 - (メソッド名) - validation.xml で validation することもできる
//	@Override
//	public void validate() {
//		if (StringUtils.isBlank(userName)) {
//			addFieldError("userName", getText("required", new String[]{"ユーザ名"}));
//		}
//		if (StringUtils.isBlank(password)) {
//			addFieldError("password", getText("required", new String[]{"パスワード"}));
//		}
//	}
}
