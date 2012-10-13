package org.yukung.practice.chap3;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.yukung.practice.chap3.KeyType;
import org.yukung.practice.chap3.StrongBox;

public class StrongBoxTest {

	private static final int PADLOCK_COUNT = 1024;
	private static final int BUTTON_COUNT = 10000;
	private static final int DIAL_COUNT = 30000;
	private static final int FINGER_COUNT = 1000000;

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testButton() {
		StrongBox<String> strongBox = new StrongBox<String>(KeyType.BUTTON);
		strongBox.put("大事なもの");
		String element = null;
		for (int i = 0; i < BUTTON_COUNT; i++) {
			element = strongBox.get();
			System.out.printf("count: %d%n", i);
			assertThat(element, is(nullValue()));
		}
		element = strongBox.get();
		assertThat(element, is("大事なもの"));
		strongBox.put("宝物");
		for (int i = 0; i < BUTTON_COUNT; i++) {
			element = strongBox.get();
			System.out.printf("count: %d%n", i);
			assertThat(element, is(nullValue()));
		}
		element = strongBox.get();
		assertThat(element, is("宝物"));
	}

	@Test
	public void testPadLock() {
		StrongBox<Integer> strongBox = new StrongBox<Integer>(KeyType.PADLOCK);
		strongBox.put(100);
		Integer element = null;
		for (int i = 0; i < PADLOCK_COUNT; i++) {
			element = strongBox.get();
			System.out.printf("count: %d%n", i);
			assertThat(element, is(nullValue()));
		}
		element = strongBox.get();
		assertThat(element, is(100));
		strongBox.put(200);
		for (int i = 0; i < 1024; i++) {
			element = strongBox.get();
			assertThat(element, is(nullValue()));
		}
		element = strongBox.get();
		assertThat(element, is(200));
	}


	@Test
	public void testDial() {
		StrongBox<Double> strongBox = new StrongBox<Double>(KeyType.DIAL);
		strongBox.put(2.0);
		Double element = null;
		for (int i = 0; i < DIAL_COUNT; i++) {
			element = strongBox.get();
			System.out.printf("count: %d%n", i);
			assertThat(element, is(nullValue()));
		}
		element = strongBox.get();
		assertThat(element, is(2.0));
		strongBox.put(11.2);
		for (int i = 0; i < DIAL_COUNT; i++) {
			element = strongBox.get();
			System.out.printf("count: %d%n", i);
			assertThat(element, is(nullValue()));
		}
		element = strongBox.get();
		assertThat(element, is(11.2));
	}

	@Test
	public void testFinger() throws Exception {
		StrongBox<Boolean> strongBox = new StrongBox<Boolean>(KeyType.FINGER);
		strongBox.put(false);
		Boolean element = null;
		for (int i = 0; i < FINGER_COUNT; i++) {
			element = strongBox.get();
			System.out.printf("count: %d%n", i);
			assertThat(element, is(nullValue()));
		}
		element = strongBox.get();
		assertThat(element, is(false));
		strongBox.put(true);
		for (int i = 0; i < FINGER_COUNT; i++) {
			element = strongBox.get();
			System.out.printf("count: %d%n", i);
			assertThat(element, is(nullValue()));
		}
		element = strongBox.get();
		assertThat(element, is(true));
	}
}
