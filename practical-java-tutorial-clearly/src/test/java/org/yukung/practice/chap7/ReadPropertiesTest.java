package org.yukung.practice.chap7;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class ReadPropertiesTest {

	@Test
	public void testRead() {
		ReadProperties readProperties = new ReadProperties("src/test/resources/pref.properties");
		Pref aichi = readProperties.getPref("aichi");
		assertThat(aichi.toString(), is("名古屋：味噌カツ"));
		Pref tokyo = readProperties.getPref("tokyo");
		assertThat(tokyo.toString(), is("東京：寿司"));

	}

	public static class Pref {
		private String capital;
		private String food;
		public String getCapital() {
			return capital;
		}
		public void setCapital(String capital) {
			this.capital = capital;
		}
		public String getFood() {
			return food;
		}
		public void setFood(String food) {
			this.food = food;
		}
		@Override
		public String toString() {
			return capital + "：" + food;
		}

	}
}
