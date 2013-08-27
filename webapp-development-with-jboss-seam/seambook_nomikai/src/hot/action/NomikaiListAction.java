package action;

import java.util.ArrayList;
import java.util.List;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;

import entity.Nomikai;

@Name("nomikaiListAction")
public class NomikaiListAction {
	@Out
	private List<Nomikai> nomikais;

	public void page() {
		// implement your business logic here
		nomikais = new ArrayList<Nomikai>();
	}
}
