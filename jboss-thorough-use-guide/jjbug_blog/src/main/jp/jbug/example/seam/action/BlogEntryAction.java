package jp.jbug.example.seam.action;

import java.io.Serializable;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import jp.jbug.example.seam.model.BlogEntry;

import org.jboss.seam.annotations.Begin;
import org.jboss.seam.annotations.Destroy;
import org.jboss.seam.annotations.End;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.core.Events;
import org.jboss.seam.faces.FacesMessages;
import org.jboss.seam.log.Log;

@Stateful
@Name("blogEntryAction")
public class BlogEntryAction implements Serializable, IBlogEntry {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@In(required = false)
	@Out(required = false)
	private BlogEntry blogEntry;

	@In
	private FacesMessages facesMessages;

	@In
	private Events events;

	@Logger
	private Log log;

	@Begin
	@Override
	public String init() {
		log.info("ブログ登録画面前処理");
		return "/BlogEntry.xhtml";
	}

	@Begin
	@Override
	public String init(BlogEntry entry) {
		log.info("ブログ変更画面前処理");
		this.blogEntry = entry;
		return "/BlogEntry.xhtml";
	}

	@End
	@Override
	public String save() {
		em.persist(blogEntry);
		facesMessages.add("登録しました。");
		log.info("#{blogEntry.blogEntryId} 登録しました。");
		events.raiseTransactionSuccessEvent("blogUpdated");
		return "/BlogEntryList.xhtml";
	}

	@End
	@Override
	public String update() {
		facesMessages.add("変更しました。");
		log.info("#{blogEntry.blogEntryId} 変更しました。");
		events.raiseTransactionSuccessEvent("blogUpdated");
		return "/BlogEntryList.xhtml";
	}

	@Remove
	@Destroy
	@Override
	public void destroy() {
		log.info(this + " は破棄されました。");
	}

}
