package be.vdab.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import be.vdab.entities.Bier;

public interface BierDAO extends JpaRepository<Bier, Long> {
}
