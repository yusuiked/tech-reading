package org.domain.seambooknomikai.test;

import org.testng.annotations.Test;
import org.jboss.seam.mock.SeamTest;

public class NomikaiListActionTest extends SeamTest {

	@Test
	public void test_page() throws Exception {
		new FacesRequest() {
			@Override
			protected void invokeApplication() {
				//call action methods here
				invokeMethod("#{nomikaiListAction.page}");
			}
		}.run();
	}
}
