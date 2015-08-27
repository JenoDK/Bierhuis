package be.vdab.entities;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import be.vdab.valueobjects.Adres;

public class BrouwerTest {
	private Brouwer brouwer;
	private Brouwer brouwer2;
	
	@Before
	public void setUp() {
		Brouwer brouwer = new Brouwer("Jeno", new BigDecimal(1000), new Adres(
				"straat1", "huisnr1", 1, "gemeente1"));
		Brouwer brouwer2 = new Brouwer("Jeno", new BigDecimal(1000), new Adres(
				"straat1", "huisnr1", 1, "gemeente1"));
	}

	@Test
	public void equalsTestOpBrouwer() {
		assertEquals(brouwer, brouwer2);
	}
	
}
