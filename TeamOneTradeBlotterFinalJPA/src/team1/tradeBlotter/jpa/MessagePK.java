package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the messages database table.
 * 
 */
@Embeddable
public class MessagePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int messageId;

	@Column(insertable=false, updatable=false)
	private int subjects_subjectId;

	@Column(insertable=false, updatable=false)
	private int traders_traderId;

	public MessagePK() {
	}
	public int getMessageId() {
		return this.messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public int getSubjects_subjectId() {
		return this.subjects_subjectId;
	}
	public void setSubjects_subjectId(int subjects_subjectId) {
		this.subjects_subjectId = subjects_subjectId;
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
		if (!(other instanceof MessagePK)) {
			return false;
		}
		MessagePK castOther = (MessagePK)other;
		return 
			(this.messageId == castOther.messageId)
			&& (this.subjects_subjectId == castOther.subjects_subjectId)
			&& (this.traders_traderId == castOther.traders_traderId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.messageId;
		hash = hash * prime + this.subjects_subjectId;
		hash = hash * prime + this.traders_traderId;
		
		return hash;
	}
}