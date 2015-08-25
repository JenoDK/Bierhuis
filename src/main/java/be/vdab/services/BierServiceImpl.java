package be.vdab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import be.vdab.dao.BierDAO;
import be.vdab.entities.Bier;

@ReadOnlyTransactionalService
public class BierServiceImpl implements BierService {
	private final BierDAO bierDAO;

	@Autowired
	BierServiceImpl(BierDAO bierDAO) {
		this.bierDAO = bierDAO;
	}

	@Override
	public Bier read(long id) {
		return bierDAO.findOne(id);
	}

	@Override
	public List<Bier> findAll() {
		return bierDAO.findAll(new Sort("naam"));
	}

	@Override
	public long findAantalBieren() {
		return bierDAO.count();
	}

}
