package org.yukung.sandbox.sample.actions;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;

public class AwareSampleAction extends ActionSupport implements
		ServletRequestAware, ServletResponseAware, ServletContextAware,
		SessionAware {
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Map<String, Object> session;
	private ServletContext context;

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public void setServletContext(ServletContext context) {
		this.context = context;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String execute() {
		request.setAttribute("val1", "foo");
		session.put("val2", "bar");
		context.setAttribute("val3", "baz");
		return SUCCESS;
	}
}
