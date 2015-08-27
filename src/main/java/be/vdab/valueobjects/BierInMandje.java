package be.vdab.valueobjects;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

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

	public Bier getBier() {
		return bier;
	}

	@NumberFormat(style = Style.NUMBER)
	public BigDecimal getTotaal() {
		return bier.getPrijs().multiply(new BigDecimal(aantal));
	}
}
