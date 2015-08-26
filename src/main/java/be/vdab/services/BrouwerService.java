package be.vdab.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import be.vdab.entities.Brouwer;

public interface BrouwerService {
	Brouwer read(long id);
	
	Page<Brouwer> findAll(Pageable pageable);

	List<Brouwer> findAll();
	
	long findAantalBrouwers();
}
