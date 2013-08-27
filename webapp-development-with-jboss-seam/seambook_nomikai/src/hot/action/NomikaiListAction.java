package action;

import java.util.List;

import javax.persistence.EntityManager;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import entity.Nomikai;

@Name("nomikaiListAction")
public class NomikaiListAction {
	@In
	private EntityManager entityManager;
	@Out
	private List<Nomikai> nomikais;

	public void page() {
		this.nomikais = entityManager.createQuery("select t from Nomikai t")
				.getResultList();
	}
}
