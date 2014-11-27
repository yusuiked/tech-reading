package org.yukung.sandbox.sample.interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class FooInterceptor implements Interceptor {

	@Override
	public void destroy() {
	}

	@Override
	public void init() {
	}

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		System.out.println("Actionを呼ぶ前です。");
		String result = invocation.invoke();
		System.out.println("Actionを呼んだ後です。");
		return result;
	}

}
