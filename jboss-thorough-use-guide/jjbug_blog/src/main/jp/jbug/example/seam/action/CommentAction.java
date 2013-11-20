package jp.jbug.example.seam.action;

import java.io.Serializable;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import jp.jbug.example.seam.model.BlogEntry;
import jp.jbug.example.seam.model.Comment;

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
@Name("commentAction")
public class CommentAction implements Serializable, IComment {

	private static final long serialVersionUID = 1L;

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@In(required = false)
	@Out(required = false)
	private BlogEntry blogEntry;

	@In(required = false)
	@Out(required = false)
	private Comment comment;

	@In
	private FacesMessages facesMessages;

	@In
	private Events events;

	@Logger
	private Log log;

	@Begin(join = true)
	@Override
	public void init(BlogEntry entry) {
		comment = new Comment();
		comment.setBlogEntry(entry);
		log.info("コメント画面初期処理");
	}

	@End
	@Override
	public void save() {
		em.persist(comment);
		facesMessages.add("コメント登録しました。");
		log.info("コメント登録しました。（#{comment.commentId}");
		events.raiseTransactionSuccessEvent("blogUpdated");
	}

	@Remove
	@Destroy
	@Override
	public void destroy() {
		log.info(this + " は破棄されました。");
	}

}
