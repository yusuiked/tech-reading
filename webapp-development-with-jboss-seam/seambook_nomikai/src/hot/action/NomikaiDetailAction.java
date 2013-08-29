package action;

import java.util.List;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.datamodel.DataModel;

import dto.NomikaiDetailDto;
import entity.Nomikai;
import entity.Nomikaimember;

@Name("nomikaiDetailAction")
public class NomikaiDetailAction {

	@In
	private NomikaiDetailDto nomikaiDetailDto;

	@Out
	private Nomikai nomikai;

	@DataModel
	private List<Nomikaimember> nomikaimembers;

	public void page() {

	}

	public String confirm() {
		return "confirm";
	}
}
