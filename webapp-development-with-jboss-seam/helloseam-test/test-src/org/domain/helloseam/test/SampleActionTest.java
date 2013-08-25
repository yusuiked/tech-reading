package org.domain.helloseam.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class SampleActionTest extends SeamTest {

	@Test
	public void test_say() throws Exception {
		new FacesRequest() {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{sampleAction.say}");
			}
		}.run();
	}
}
