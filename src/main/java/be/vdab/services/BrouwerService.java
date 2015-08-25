package be.vdab.services;

import java.util.List;

import be.vdab.entities.Brouwer;

public interface BrouwerService {
	Brouwer read(long id);

	List<Brouwer> findAll();
	
	long findAantalBrouwers();
}
