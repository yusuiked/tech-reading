package sample.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;

import sample.biz.service.SampleService;

/**
 * AutowiringRequestProcessorを使用したサンプル
 */
public class SampleAction3 extends Action {
	@Autowired
	private SampleService sampleService;

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SampleForm sampleForm = (SampleForm) form;
		String fullName = sampleService.createFullName(
				sampleForm.getFirstName(), sampleForm.getLastName());
		request.setAttribute("fullName", fullName);
		return mapping.findForward("success");
	}
}
