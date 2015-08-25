package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import be.vdab.dao.BrouwerDAO;
import be.vdab.entities.Brouwer;

@ReadOnlyTransactionalService
public class BrouwerServiceImpl implements BrouwerService {
	private final BrouwerDAO brouwerDAO;

	@Autowired
	BrouwerServiceImpl(BrouwerDAO brouwerDAO) {
		this.brouwerDAO = brouwerDAO;
	}

	@Override
	public Brouwer read(long id) {
		return brouwerDAO.findOne(id);
	}

	@Override
	public List<Brouwer> findAll() {
		return brouwerDAO.findAll(new Sort("naam"));
	}

	@Override
	public long findAantalBrouwers() {
		return brouwerDAO.count();
	}
	
	
}
