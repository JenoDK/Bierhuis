package be.vdab.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

@Entity
@Table(name = "bieren")
public class Bier implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false, name = "BierNr")
	private long bierNr;

	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal alcohol;

	@Column(nullable = false, length = 100)
	private String naam;

	@NumberFormat(style = Style.NUMBER)
	@Column(nullable = false, precision = 10, scale = 2)
	private BigDecimal prijs;

	// bi-directional many-to-one association to Brouwer
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "BrouwerNr", nullable = false)
	private Brouwer brouwer;

	// bi-directional many-to-one association to Soorten
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "SoortNr", nullable = false)
	private Soort soort;

	public Bier() {
	}

	public Bier(BigDecimal alcohol, String naam, BigDecimal prijs) {
		this.alcohol = alcohol;
		this.naam = naam;
		this.prijs = prijs;
	}

	public long getBierNr() {
		return this.bierNr;
	}

	public BigDecimal getAlcohol() {
		return this.alcohol;
	}

	public String getNaam() {
		return this.naam;
	}

	public BigDecimal getPrijs() {
		return this.prijs;
	}

	public Brouwer getBrouwer() {
		return this.brouwer;
	}

	public Soort getSoort() {
		return soort;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alcohol == null) ? 0 : alcohol.hashCode());
		result = prime * result + ((brouwer == null) ? 0 : brouwer.hashCode());
		result = prime * result + ((naam == null) ? 0 : naam.hashCode());
		result = prime * result + ((prijs == null) ? 0 : prijs.hashCode());
		result = prime * result + ((soort == null) ? 0 : soort.hashCode());
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
		Bier other = (Bier) obj;
		if (alcohol == null) {
			if (other.alcohol != null)
				return false;
		} else if (!alcohol.equals(other.alcohol))
			return false;
		if (brouwer == null) {
			if (other.brouwer != null)
				return false;
		} else if (!brouwer.equals(other.brouwer))
			return false;
		if (naam == null) {
			if (other.naam != null)
				return false;
		} else if (!naam.equals(other.naam))
			return false;
		if (prijs == null) {
			if (other.prijs != null)
				return false;
		} else if (!prijs.equals(other.prijs))
			return false;
		if (soort == null) {
			if (other.soort != null)
				return false;
		} else if (!soort.equals(other.soort))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bier [alcohol=" + alcohol + ", naam=" + naam + ", prijs="
				+ prijs + ", brouwer=" + brouwer + ", soort=" + soort + "]";
	}

}
