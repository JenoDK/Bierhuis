package be.vdab.services;

import org.springframework.beans.factory.annotation.Autowired;

import be.vdab.dao.BestelbonDAO;
import be.vdab.entities.Bestelbon;

@ReadOnlyTransactionalService
public class BestelbonServiceImpl implements BestelbonService {
	private final BestelbonDAO bestelbonDAO;

	@Autowired
	BestelbonServiceImpl(BestelbonDAO bestelbonDAO) {
		this.bestelbonDAO = bestelbonDAO;
	}

	@Override
	@ModifyingTransactionalServiceMethod
	public void create(Bestelbon bestelbon) {
		bestelbon.setBonNr(bestelbonDAO.save(bestelbon).getBonNr());
	}

}
