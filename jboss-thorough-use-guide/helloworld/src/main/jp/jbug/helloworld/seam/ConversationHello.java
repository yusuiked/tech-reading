package jp.jbug.helloworld.seam;

import javax.ejb.Local;

@Local
public interface ConversationHello {
	void init();

	String login();

	String register();

	String displayRegisterPage();

	String logout();

	ConversationHelloMessage getConversationHelloMessage();

	void destroy();
}
