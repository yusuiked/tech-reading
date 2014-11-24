package sample.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.struts.ActionSupport;

import sample.biz.service.SampleService;

/**
 * ActionSupportを使用したサンプル
 */
public class SampleAction2 extends ActionSupport {

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SampleForm sampleForm = (SampleForm) form;

		SampleService sampleService = (SampleService) getWebApplicationContext()
				.getBean(SampleService.class);
		String fullName = sampleService.createFullName(
				sampleForm.getFirstName(), sampleForm.getLastName());
		request.setAttribute("fullName", fullName);
		return mapping.findForward("success");
	}
}
