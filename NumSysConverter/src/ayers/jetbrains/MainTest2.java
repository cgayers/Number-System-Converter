package ayers.jetbrains;

import static org.junit.Assert.*;
import org.junit.*;

public class MainTest2 {

	@Test
	public void testConvertFromDecimal() {
		assertEquals(new StringBuilder("13.14315").toString(), Main.convertFromDecimal(10.234, 7).toString());
		assertEquals(new StringBuilder("148.g88a8").toString(), Main.convertFromDecimal(365.97061224489795, 17).toString());
	}

	@Test
	public void testConvertToDecimal() {
		assertEquals(7.0, Main.convertToDecimal("1111111", 1),0);
		assertEquals(365.97061224489795, Main.convertToDecimal("af.xy", 35),0);
		assertEquals(699050.0, Main.convertToDecimal("aaaaa.0", 16),0);
	}
}
