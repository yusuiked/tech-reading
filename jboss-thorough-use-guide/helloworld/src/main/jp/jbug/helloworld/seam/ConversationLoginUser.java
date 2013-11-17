package jp.jbug.helloworld.seam;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Name("conversationLoginUser")
@Scope(ScopeType.CONVERSATION)
public class ConversationLoginUser {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
