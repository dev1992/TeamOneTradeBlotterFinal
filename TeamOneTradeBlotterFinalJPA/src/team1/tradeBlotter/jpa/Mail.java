package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;


/**
 * The persistent class for the mails database table.
 * 
 */
@Entity
@Table(name="mails")
@NamedQuery(name="Mail.findAll", query="SELECT m FROM Mail m")
public class Mail implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private MailPK id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int mailId;

	@Lob
	private String mailBody;
    
	@Column(insertable=false, updatable=false)
	private int traders_traderId;
	
	private int receiverId;

	//bi-directional many-to-one association to Trader
	@ManyToOne
	@JoinColumn(name="Traders_traderId")
	@JsonBackReference
	private Trader trader;

	public Mail() {
	}

	public int getId() {
		return this.mailId;
	}

	public void setId(int id) {
		this.mailId = id;
	}

	public String getMailBody() {
		return this.mailBody;
	}

	public void setMailBody(String mailBody) {
		this.mailBody = mailBody;
	}

	public int getReceiverId() {
		return this.receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	public int getTraders_traderId() {
		return traders_traderId;
	}

	public void setTraders_traderId(int traders_traderId) {
		this.traders_traderId = traders_traderId;
	}

	public int getMailId() {
		return mailId;
	}

	public void setMailId(int mailId) {
		this.mailId = mailId;
	}

}