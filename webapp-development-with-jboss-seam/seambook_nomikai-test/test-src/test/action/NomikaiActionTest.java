package test.action;

import static org.testng.Assert.*;

import org.jboss.seam.mock.SeamTest;
import org.testng.annotations.Test;

import action.NomikaiAction;
import entity.Nomikai;

public class NomikaiActionTest extends SeamTest {

	@Test
	public void testNomikaiAction() {
		Long testId = 1L;
		NomikaiAction action = new NomikaiAction();
		Nomikai result = null;
		// Test No ID
		result = action.findOrNewNomikai(null);

		assertNotNull(result);

		result = action.findOrNewNomikai(testId);

		assertEquals((Long) result.getNomikaiid(), (Long) testId);
	}
}
