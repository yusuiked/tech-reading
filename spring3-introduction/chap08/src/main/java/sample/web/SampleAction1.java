package sample.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import sample.biz.SampleService;

@Controller("/sample1")
public class SampleAction1 extends Action {

	@Autowired
	private SampleService sampleService;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SampleForm sampleForm = (SampleForm) form;
		String fullName = sampleService.createFullName(sampleForm.getFirstName(), sampleForm.getLastName());
		request.setAttribute("fullName", fullName);
		return mapping.findForward("success");
	}
}
