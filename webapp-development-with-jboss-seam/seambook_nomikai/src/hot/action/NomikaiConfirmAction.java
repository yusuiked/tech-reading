package action;

import org.jboss.seam.annotations.Conversational;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.log.Log;

import entity.Nomikaimember;

@Conversational
@Name("nomikaiConfirmAction")
public class NomikaiConfirmAction {

	@In
	private Nomikaimember nomikaimember;

	@Logger
	private Log logger;

	public String invoke() {
		boolean attend = nomikaimember.isAttend();
		nomikaimember.setAttend(!attend);
		return "invoke";
	}

	public String reset() {
		return "reset";
	}

	public void page() {
		logger.info("nomikai member id is #0", nomikaimember
				.getNomikaimemberid());
	}
}
