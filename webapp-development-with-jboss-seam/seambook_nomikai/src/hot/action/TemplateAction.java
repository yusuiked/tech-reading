package action;

import org.jboss.seam.annotations.Name;

@Name("templateAction")
public class TemplateAction {

	public String home() {
		return "home";
	}

	public String create() {
		return "create";
	}

	public String update() {
		return "update";
	}
}
