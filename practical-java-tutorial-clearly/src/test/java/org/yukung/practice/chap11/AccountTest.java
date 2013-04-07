package org.yukung.practice.chap11;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 口座クラスの単体テスト
 * @author yukung
 *
 */
public class AccountTest {

	/**
	 * 実際に Account を new して使ってみるテスト
	 */
	@Test
	public void instatiate() {
		Account a = new Account("ミナト", 30000);
		assertEquals("ミナト", a.owner);
		assertEquals(30000, a.balance);
	}

	@Test
	public void transfer() throws Exception {
	}
}
