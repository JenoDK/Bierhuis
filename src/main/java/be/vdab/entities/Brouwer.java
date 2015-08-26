package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import be.vdab.valueobjects.Adres;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * The persistent class for the brouwers database table.
 * 
 */
@Entity
@Table(name = "brouwers")
public class Brouwer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private long brouwerNr;

	@Column(nullable = false, length = 50)
	private String naam;

	@NumberFormat(style = Style.NUMBER)
	@Column(precision = 10, scale = 2)
	private BigDecimal omzet;

	@Embedded
	private Adres adres;
	// bi-directional many-to-one association to Bieren
	@OneToMany(mappedBy = "brouwer")
	@OrderBy("naam")
	private Set<Bier> bieren;

	public Brouwer() {
	}

	public Brouwer(String naam, BigDecimal omzet, Adres adres) {
		this.naam = naam;
		this.omzet = omzet;
		this.adres = adres;
		bieren = new LinkedHashSet<>();
	}

	public long getBrouwerNr() {
		return this.brouwerNr;
	}

	public String getNaam() {
		return this.naam;
	}

	public BigDecimal getOmzet() {
		return this.omzet;
	}

	public Adres getAdres() {
		return adres;
	}

	public Set<Bier> getBieren() {
		return Collections.unmodifiableSet(bieren);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((adres == null) ? 0 : adres.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + ((omzet == null) ? 0 : omzet.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Brouwer other = (Brouwer) obj;
		if (adres == null) {
			if (other.adres != null)
				return false;
		} else if (!adres.equals(other.adres))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (omzet == null) {
			if (other.omzet != null)
				return false;
		} else if (!omzet.equals(other.omzet))
			return false;
		return true;
	}

}