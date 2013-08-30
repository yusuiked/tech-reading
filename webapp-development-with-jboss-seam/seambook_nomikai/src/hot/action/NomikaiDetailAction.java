package action;

import java.util.Set;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Conversational;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;
import org.jboss.seam.contexts.Context;
import org.jboss.seam.contexts.Contexts;
import org.jboss.seam.log.Log;

import dto.NomikaiDetailDto;
import entity.Nomikai;
import entity.Nomikaimember;

@Conversational
@Scope(ScopeType.CONVERSATION)
@Name("nomikaiDetailAction")
public class NomikaiDetailAction {

	@In
	private NomikaiDetailDto nomikaiDetailDto;

	@Out
	private Nomikai nomikai;

	@DataModel
	private Set<Nomikaimember> nomikaimembers;

	@DataModelSelection("nomikaimembers")
	private Nomikaimember nomikaimember;

	@In
	private EntityManager entityManager;

	@Logger
	private Log logger;

	public void page() {
		logger.info("find nomikaiid is #0", nomikaiDetailDto.getNomikaiid());
		this.nomikai = entityManager.find(Nomikai.class, nomikaiDetailDto
				.getNomikaiid());
		this.nomikaimembers = nomikai.getNomikaimemberCollection();
	}

	public String confirm() {
		logger.info("selected member is #0", nomikaimember.getUserid()
				.getName());
		Context conversationContext = Contexts.getConversationContext();
		conversationContext.set("nomikaimember", nomikaimember);
		return "confirm";
	}
}
