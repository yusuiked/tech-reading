package jp.jbug.helloworld.seam;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Create;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.core.Conversation;
import org.jboss.seam.log.Log;

@Stateful
@Name("conversationHelloAction")
public class ConversationHelloAction implements ConversationHello {

	@Logger
	Log log;

	@In(required = false, value = "conversationHelloMessage")
	private ConversationHelloMessage message;

	@In(required = false, scope = ScopeType.APPLICATION)
	@Out(required = false, scope = ScopeType.APPLICATION)
	private List<ConversationHelloMessage> conversationHelloMessages;
	
	@In(required = false)
	@Out(required = false)
	private ConversationLoginUser conversationLoginUser;

	@Create
	@Override
	public void init() {
		if (conversationHelloMessages == null) {
			conversationHelloMessages = new ArrayList<ConversationHelloMessage>();
		}
	}

	@Begin
	@Override
	public String login() {
		log.info(conversationLoginUser.getName() + " がログインしました。");
		return "/conversation-register.xhtml";
	}

	@Begin(join = true)
	@Override
	public String register() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
				.getExternalContext().getSession(false);
		Conversation conversation = (Conversation) Contexts
				.getConversationContext().get(
						"org.jboss.seam.core.conversation");
		message.setSessionId(session.getId());
		message.setObjectId(this.toString().substring(
				this.toString().lastIndexOf("@")));
		message.setConversationId(conversation.getId());

		conversationHelloMessages.add(message);
		log.info(message + " を登録しました。");
		return "/conversation-registered.xhtml";
	}

	@Begin(join = true)
	@Override
	public String displayRegisterPage() {
		log.info("登録準備");
		return "/conversation-register.xhtml";
	}

	@End
	@Override
	public String logout() {
		log.info(conversationLoginUser.getName() + " がログアウトしました。");
		return "/conversation-login.xhtml";
	}

	@Out(required = false, value = "conversationHelloMessage")
	@Override
	public ConversationHelloMessage getConversationHelloMessage() {
		message = new ConversationHelloMessage();
		message.setName(conversationLoginUser.getName());
		message.setMessage("");
		return message;
	}

	@Remove
	@Destroy
	@Override
	public void destroy() {
		log.info(this + " は破棄されました。");
	}

}
