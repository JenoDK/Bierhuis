package be.vdab.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import be.vdab.entities.Brouwer;
import be.vdab.services.BrouwerService;
import be.vdab.valueobjects.Adres;

public class BrouwerControllerTest {
	private BrouwerController brouwerController;
	private List<Brouwer> brouwers;
	private BrouwerService brouwerService;
	private Brouwer brouwer;

	@Before
	public void setUp() {
		brouwers = Collections.emptyList();
		brouwerService = Mockito.mock(BrouwerService.class);
		Mockito.when(brouwerService.findAll()).thenReturn(brouwers);
		brouwerController = new BrouwerController(brouwerService);
		brouwer = new Brouwer("Jeno", new BigDecimal(1000), new Adres(
				"straat1", "huisnr1", 1, "gemeente1"));
		brouwers.add(brouwer);
		Mockito.when(brouwerService.read(1)).thenReturn(brouwer);
	}
//  Deze testen in comment omdat findAll() method een Pageable parameter nodig heeft
//	@Test
//	public void findAllActiveertJuisteView() {
//		assertEquals("brouwers/brouwers", brouwerController.findAll()
//				.getViewName());
//	}
//
//	@Test
//	public void findAllMaaktRequestAttribuutFilialen() {
//		assertSame(brouwers,
//				brouwerController.findAll().getModelMap().get("brouwers"));
//	}

	@Test
	public void readActiveertJuisteView() {
		assertEquals("brouwers/bieren", brouwerController.read(brouwer)
				.getViewName());
	}

	@Test
	public void readMetBestaandeIDGeeftBrouwerTerug() {
		assertSame(brouwer,
				brouwerController.read(brouwer).getModelMap().get("brouwer"));
	}
	

}
