package jp.jbug.helloworld.seam;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

@Name("helloAction")
// @Scope(ScopeType.STATELESS)
public class HelloAction {

	@In
	private HelloMessage helloMessage;

	@In(required = false, scope = ScopeType.APPLICATION)
	@Out(required = false, scope = ScopeType.APPLICATION)
	private List<HelloMessage> helloMessages;

	public String register() {
		if (helloMessages == null) {
			helloMessages = new ArrayList<>();
		}

		helloMessage.setName(helloMessage.getName() + "さん");
		helloMessages.add(helloMessage);

		return "/stateless-registered.xhtml";
	}
}
