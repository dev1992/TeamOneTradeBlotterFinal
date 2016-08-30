package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the trades database table.
 * 
 */
@Embeddable
public class TradePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int tradeId;

	@Column(insertable=false, updatable=false)
	private int traders_traderId;

	public TradePK() {
	}
	public int getTradeId() {
		return this.tradeId;
	}
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}
	public int getTraders_traderId() {
		return this.traders_traderId;
	}
	public void setTraders_traderId(int traders_traderId) {
		this.traders_traderId = traders_traderId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof TradePK)) {
			return false;
		}
		TradePK castOther = (TradePK)other;
		return 
			(this.tradeId == castOther.tradeId)
			&& (this.traders_traderId == castOther.traders_traderId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.tradeId;
		hash = hash * prime + this.traders_traderId;
		
		return hash;
	}
}