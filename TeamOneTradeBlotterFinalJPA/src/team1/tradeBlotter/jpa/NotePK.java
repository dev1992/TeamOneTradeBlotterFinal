package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the notes database table.
 * 
 */
@Embeddable
public class NotePK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private int noteId;

	@Column(insertable=false, updatable=false)
	private int traders_traderId;

	public NotePK() {
	}
	public int getNoteId() {
		return this.noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
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
		if (!(other instanceof NotePK)) {
			return false;
		}
		NotePK castOther = (NotePK)other;
		return 
			(this.noteId == castOther.noteId)
			&& (this.traders_traderId == castOther.traders_traderId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.noteId;
		hash = hash * prime + this.traders_traderId;
		
		return hash;
	}
}