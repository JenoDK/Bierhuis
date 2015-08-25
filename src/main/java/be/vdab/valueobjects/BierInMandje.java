package be.vdab.valueobjects;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import be.vdab.entities.Bier;

public class BierInMandje {
	@NotNull
	@Min(1)
	private int aantal;

	private Bier bier;

	public BierInMandje() {
	}

	public BierInMandje(int aantal, Bier bier) {
		this.aantal = aantal;
		this.bier = bier;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public Bier getBier() {
		return bier;
	}

	public void setBier(Bier bier) {
		this.bier = bier;
	}

	public BigDecimal getTotaal() {
		return bier.getPrijs().multiply(new BigDecimal(aantal));
	}
}
