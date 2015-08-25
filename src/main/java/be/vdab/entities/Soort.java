package be.vdab.entities;

import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "soorten")
public class Soort implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private int soortNr;

	@Column(nullable = false, length = 50)
	private String naam;

	// bi-directional many-to-one association to Bieren
	@OneToMany(mappedBy = "soort")
	private Set<Bier> bieren;

	public Soort() {
	}

	public int getSoortNr() {
		return this.soortNr;
	}

	public void setSoortNr(int soortNr) {
		this.soortNr = soortNr;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public Set<Bier> getBieren() {
		return Collections.unmodifiableSet(bieren);
	}

	public void addBier(Bier bier) {
		bieren.add(bier);
		if (bier.getSoort() != this) {
			bier.setSoort(this);
		}
	}

	public void removeBier(Bier bier) {
		bieren.remove(bier);
		if (bier.getSoort() == this) {
			bier.setSoort(null);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
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
		Soort other = (Soort) obj;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Soort [soortNr=" + soortNr + ", naam=" + naam + "]";
	}

}
