package be.vdab.valueobjects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import be.vdab.entities.Bier;

@Embeddable
public class Bestelbonlijn implements Serializable {

	private static final long serialVersionUID = 1L;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "BierNr")
	private Bier bier;
	@Column(nullable = false)
	private int aantal;

	public Bestelbonlijn(Bier bier, int aantal) {
		this.bier = bier;
		this.aantal = aantal;
	}

	public Bestelbonlijn() {

	}

	public Bier getBier() {
		return bier;
	}

	public int getAantal() {
		return aantal;
	}

	@Override
	public String toString() {
		return "Bestelbonlijn [bier=" + bier + ", aantal=" + aantal + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bier == null) ? 0 : bier.hashCode());
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
		Bestelbonlijn other = (Bestelbonlijn) obj;
		if (bier == null) {
			if (other.bier != null)
				return false;
		} else if (!bier.equals(other.bier))
			return false;
		return true;
	}

}
