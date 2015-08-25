package be.vdab.services;

import java.util.List;

import be.vdab.entities.Bier;

public interface BierService {

	Bier read(long id);

	List<Bier> findAll();

	long findAantalBieren();
}
