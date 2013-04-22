package org.yukung.practice.chap11;

import static org.junit.Assert.*;

import org.junit.Test;

public class BankTest {

	@Test
	public void setName() {
		Bank bank = new Bank();
		bank.setName("ミヤビ");
	}

	@Test
	public void setNameWithNull() throws Exception {
		try {
			Bank bank = new Bank();
			bank.setName(null);
		} catch (IllegalArgumentException e) {
			return;
		}
		fail();
	}

	@Test(expected=IllegalArgumentException.class)
	public void throwsExceptionWithTwoCharName() throws Exception {
		Bank bank = new Bank();
		bank.setName("ミヤ");
	}
}
