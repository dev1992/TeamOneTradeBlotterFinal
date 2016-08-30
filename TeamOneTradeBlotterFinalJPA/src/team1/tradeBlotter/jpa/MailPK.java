package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the mails database table.
 * 
 */
@Embeddable
public class MailPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int mailId;

	@Column(insertable=false, updatable=false)
	private int traders_traderId;

	public MailPK() {
	}
	public int getMailId() {
		return this.mailId;
	}
	public void setMailId(int mailId) {
		this.mailId = mailId;
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
		if (!(other instanceof MailPK)) {
			return false;
		}
		MailPK castOther = (MailPK)other;
		return 
			(this.mailId == castOther.mailId)
			&& (this.traders_traderId == castOther.traders_traderId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.mailId;
		hash = hash * prime + this.traders_traderId;
		
		return hash;
	}
}