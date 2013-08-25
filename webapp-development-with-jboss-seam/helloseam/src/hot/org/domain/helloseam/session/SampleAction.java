package org.domain.helloseam.session;

import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.international.StatusMessages;
import org.jboss.seam.log.Log;

@Name("sampleAction")
public class SampleAction {
	@Logger
	private Log log;

	@In
	StatusMessages statusMessages;

	public void say() {
		// implement your business logic here
		log.info("sampleAction.say() action called");
		statusMessages.add("Hello Seam!!");
	}

	// add additional action methods

}
