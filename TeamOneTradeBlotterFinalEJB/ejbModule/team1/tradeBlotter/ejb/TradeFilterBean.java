package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import team1.tradeBlotter.jpa.Trade;

/**
 * Session Bean implementation class TradeFilterBean
 */
@Stateful
@Remote(TradeFilterBeanRemote.class)
@Local(TradeFilterBeanLocal.class)
public class TradeFilterBean implements TradeFilterBeanRemote, TradeFilterBeanLocal {

	@PersistenceContext(name = "TeamOneTradeBlotterFinalJPA")
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public TradeFilterBean() {
		// TODO Auto-generated constructor stub
	}

	public List<Trade> getAllTrades() {

		System.out.println("In TradeFilterBean, method getAllTrades");

		TypedQuery<Trade> query = entityManager.createQuery("SELECT p FROM Trade AS p", Trade.class);
		List<Trade> trades = query.getResultList();
		return trades;
	}
}
