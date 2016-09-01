package team1.tradeBlotter.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.util.Date;


/**
 * The persistent class for the trades database table.
 * 
 */
@Entity
@Table(name="trades")
@NamedQuery(name="Trade.findAll", query="SELECT t FROM Trade t")
public class Trade implements Serializable {
	private static final long serialVersionUID = 1L;

//	@EmbeddedId
//	private TradePK id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int tradeId;

	@Column(insertable=false, updatable=false)
	private int traders_traderId;

	private String buyerFirm;

	private String currency;

	@Temporal(TemporalType.TIMESTAMP)
	private Date executionTime;

	private byte media;

	private double price;

	private String productName;

	private String productType;

	private int quantity;

	private String sellerFirm;

	private byte side;

	private String status;

	@Temporal(TemporalType.TIMESTAMP)
	private Date submitTime;

	//bi-directional many-to-one association to Trader
	@ManyToOne
	@JoinColumn(name="Traders_traderId")
	@JsonBackReference
	private Trader trader;

	public Trade() {
	}

//	public TradePK getId() {
//		return this.id;
//	}
//
//	public void setId(TradePK id) {
//		this.id = id;
//	}

	public String getBuyerFirm() {
		return this.buyerFirm;
	}

	public void setBuyerFirm(String buyerFirm) {
		this.buyerFirm = buyerFirm;
	}

	public String getCurrency() {
		return this.currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Date getExecutionTime() {
		return this.executionTime;
	}

	public void setExecutionTime(Date executionTime) {
		this.executionTime = executionTime;
	}

	public byte getMedia() {
		return this.media;
	}

	public void setMedia(byte media) {
		this.media = media;
	}

	public double getPrice() {
		return this.price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductType() {
		return this.productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSellerFirm() {
		return this.sellerFirm;
	}

	public void setSellerFirm(String sellerFirm) {
		this.sellerFirm = sellerFirm;
	}

	public byte getSide() {
		return this.side;
	}

	public void setSide(byte side) {
		this.side = side;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getSubmitTime() {
		return this.submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public void setTrader(Trader trader) {
		this.trader = trader;
	}

	/**
	 * @return the tradeId
	 */
	public int getTradeId() {
		return tradeId;
	}

	/**
	 * @param tradeId the tradeId to set
	 */
	public void setTradeId(int tradeId) {
		this.tradeId = tradeId;
	}

	/**
	 * @return the traders_traderId
	 */
	public int getTraders_traderId() {
		return traders_traderId;
	}

	/**
	 * @param traders_traderId the traders_traderId to set
	 */
	public void setTraders_traderId(int traders_traderId) {
		this.traders_traderId = traders_traderId;
	}

}