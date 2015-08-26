package be.vdab.dao;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import be.vdab.datasource.CreateTestDataSourceBean;
import be.vdab.entities.Bestelbon;
import be.vdab.entities.Bier;
import be.vdab.valueobjects.Adres;
import be.vdab.valueobjects.Bestelbonlijn;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { CreateTestDAOBeans.class,
		CreateTestDataSourceBean.class })
// DataSource bean en DAO beans laden in IOC container
@Transactional
// omringt elke test met een transactie, na de test rollback
public class BestelbonDAOImplTest {
	@Autowired
	private BestelbonDAO bestelbonDAO;
	private Bestelbon bestelbon;

	@Before
	public void before() {
		bestelbon = new Bestelbon("Jeno", new Adres("Kapellestraat", "27",
				9960, "Assenede"));
		bestelbon.addBestelbonlijn(new Bestelbonlijn(new Bier(
				new BigDecimal(10), "Rochefort", new BigDecimal(40)), 4));
		bestelbonDAO.save(bestelbon);
	}

	@Test
	public void create() {
		assertNotEquals(0, bestelbon.getBonNr()); // id moet autonumber hebben:
	}

	@Test
	public void testIfBestelbonLijnenIsNotEmpty() {
		assertTrue(!bestelbon.getBestelbonlijnen().isEmpty());
	}

}
