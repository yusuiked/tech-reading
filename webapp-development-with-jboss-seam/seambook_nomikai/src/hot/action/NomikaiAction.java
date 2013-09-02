package action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Conversational;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.log.Log;

import dto.CandidateDto;
import dto.NomikaiDto;
import entity.Nomikai;
import entity.Nomikaimember;
import entity.User;

@Conversational
@Scope(ScopeType.CONVERSATION)
@Name("nomikaiAction")
public class NomikaiAction {

	@In
	private NomikaiDto nomikaiDto;

	@In(required = false)
	@Out
	private Nomikai nomikai;

	@In(required = false)
	@Out
	private List<CandidateDto> candidates;

	@Logger
	private Log log;

	@In
	private EntityManager entityManager;

	public String create() {
		entityUpdate();
		entityManager.persist(nomikai);
		nomikaiDto.setNomikaiid(nomikai.getNomikaiid());
		return "create";
	}

	public String delete() {
		log.info("delete Nomikai #0", nomikai.getNomikaiid());
		entityManager.remove(nomikai);
		return "delete";
	}

	public void page() {
		log.info("Nomikaiid is #0", nomikaiDto.getNomikaiid());
		this.nomikai = findOrNewNomikai(nomikaiDto.getNomikaiid());
		this.candidates = buildCandidates();
	}

	public String update() {
		log.info("update Nomikai #0", nomikai.getNomikaiid());
		entityUpdate();
		return "update";
	}

	private List<CandidateDto> buildCandidates() {
		List<CandidateDto> candidates = new ArrayList<CandidateDto>();
		List<User> members = entityManager.createQuery("select t from User t")
				.getResultList();
		for (User member : members) {
			Nomikaimember nomikaiMember = findRegisteredNomikaiMember(member);
			CandidateDto registerDto = new CandidateDto();
			if (nomikaiMember != null) {
				registerDto.setRegister(true);
				registerDto.setNomikaimember(nomikaiMember);
			} else {
				registerDto.setRegister(false);
				registerDto.setNomikaimember(createNomikaiMember(member));
			}
			candidates.add(registerDto);
		}
		return candidates;
	}

	private Nomikaimember createNomikaiMember(User member) {
		Nomikaimember nomikaimember = new Nomikaimember();
		nomikaimember.setNomikaiid(nomikai);
		nomikaimember.setUserid(member);
		return nomikaimember;
	}

	private void entityUpdate() {
		nomikai.setNomikaimemberCollection(new HashSet<Nomikaimember>());
		for (CandidateDto registerDto : candidates) {
			if (registerDto.isRegister()) {
				nomikai.getNomikaimemberCollection().add(
						registerDto.getNomikaimember());
			} else {
				entityManager.remove(registerDto.getNomikaimember());
			}
		}
	}

	private Nomikai findOrNewNomikai(Long nomikaiid) {
		if (nomikaiid != null) {
			return (Nomikai) entityManager.createNamedQuery("findById")
					.setParameter("nomikaiid", nomikaiid).getSingleResult();
		} else {
			return new Nomikai();
		}
	}

	private Nomikaimember findRegisteredNomikaiMember(User member) {
		if (nomikai.getNomikaimemberCollection() == null) {
			return null;
		}
		Nomikaimember rMember = null;
		for (Nomikaimember nomikaiMember : nomikai.getNomikaimemberCollection()) {
			if (member.getId().equals(nomikaiMember.getUserid().getId())) {
				rMember = nomikaiMember;
				break;
			}
		}
		return rMember;
	}
}
