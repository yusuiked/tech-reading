package junit.tutorial;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorTest {
	@Test
	public void multiplyで3と4の乗算結果が取得できる() {
		Calculator calc = new Calculator();
		int expected = 12;
		int actual = calc.multiply(3, 4);
		assertThat(actual, is(expected));
	}
	
	@Test
	public void multiplyで5と7の乗算結果が取得できる() throws Exception {
		Calculator calc = new Calculator();
		int expected = 12;
		int actual = calc.multiply(5, 7);
		assertThat(actual, is(expected));
	}
}
