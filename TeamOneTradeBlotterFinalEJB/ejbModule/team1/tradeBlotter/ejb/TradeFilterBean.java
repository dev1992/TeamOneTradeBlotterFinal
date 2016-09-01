package team1.tradeBlotter.ejb;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import team1.tradeBlotter.jpa.Trader;
import team1.tradeBlotter.jpa.Trade;

/**
 * Session Bean implementation class TradeFilterBean
 */
@Stateful
@Remote(TradeFilterBeanRemote.class)
@Local(TradeFilterBeanLocal.class)
public class TradeFilterBean implements TradeFilterBeanRemote, TradeFilterBeanLocal {

	@PersistenceContext(name = "TeamOneTradeBlotterFinalJPA")
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public TradeFilterBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Trade> getAllTrades() {
		//System.out.println("print for get trades");
		TypedQuery<Trade> query = em.createQuery("SELECT t FROM Trade AS t", Trade.class);
		List<Trade> trades = query.getResultList();
		return trades;
	}

//	@Override
//	public List<Trader> getAllTraders() {
//		System.out.println("prev");
//		TypedQuery<Trader> query = em.createQuery("SELECT t FROM Trader AS t", Trader.class);
//		List<Trader> traders = query.getResultList();
//		return traders;
//	}

	@Override
	public boolean checkLogin(String userName, String password) {
		if(userName==null || password==null || userName.isEmpty() || password.isEmpty())
			return false;	
		Query query = em.createQuery("Select userName from Trader t where userName = :userName");
		query.setParameter("userName", userName);
		List<String> results = query.getResultList();
		List<Integer> passwords;
		if (results.isEmpty()) {
			return false;
		} else {
			query = em.createQuery("Select password from Trader t where username = :username");
			query.setParameter("username", userName);
			passwords = query.getResultList();
			System.out.println(results);
			Integer actualPassword = passwords.get(0);
			System.out.println("Actual Passwd:---> "+actualPassword);
			System.out.println("Hash code --> "+password.hashCode());
			return actualPassword == password.hashCode();
		}
	}

	@Override
	public boolean registerTrader(String userName, String password, String firstName, String lastName) {
		if(userName.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty())
			return false;
		Query query = em.createQuery("Select userName from Trader u where userName = :userName");
		int passwd = password.hashCode();
		query.setParameter("userName", userName);
		if (query.getResultList().isEmpty()) {
			Trader trader = new Trader(userName, passwd, firstName, lastName);
			em.persist(trader);
			em.flush();
			return true;
		} else
			return false;
	}

	// creating filter queries

	public List<Trade> filterByType(String productType) {

		String sql = "SELECT t FROM Trade AS t WHERE t.productType = :productType";
		TypedQuery<Trade> query = em.createQuery(sql, Trade.class);
		query.setParameter("productType", productType);
		// Execute the query, and get a collection of products back.
		List<Trade> trades = query.getResultList();

		return trades;
	}

	public List<Trade> filterByName(String productName) {

		String sql = "SELECT t FROM Trade AS t WHERE t.productName = :productName";
		// System.out.println(sql);
		TypedQuery<Trade> query = em.createQuery(sql, Trade.class);
		query.setParameter("productName", productName);

		// Execute the query, and get a collection of products back.
		List<Trade> trades = query.getResultList();

		// for (Trade trade: trades) {
		// displayProductOnServerConsole("Got product in getProductsByName()",
		// trades);
		// }

		return trades;
	}



//	public List<Trade> filterByPrice(double price1, double price2) {
//
//		String sql = "SELECT t FROM Trade AS t WHERE t.price BETWEEN ?1 AND ?2";
//		// System.out.println(sql);
//		TypedQuery<Trade> query = em.createQuery(sql, Trade.class);
//		query.setParameter(1, price1);
//		query.setParameter(2, price2);
//
//		// Execute the query, and get a collection of products back.
//		List<Trade> trades = query.getResultList();
//
//		// for (Trade trade: trades) {
//		// displayProductOnServerConsole("Got product in getProductsByName()",
//		// trades);
//		// }
//
//		return trades;
//	}

	public List<Trade> filterBySide(byte side) {
		
		if(side<0 || side >1)
			return null;

		String sql = "SELECT t FROM Trade AS t WHERE t.side = :side";
		// System.out.println(sql);
		TypedQuery<Trade> query = em.createQuery(sql, Trade.class);
		query.setParameter("side", side);

		// Execute the query, and get a collection of products back.
		List<Trade> trades = query.getResultList();

		// for (Trade trade: trades) {
		// displayProductOnServerConsole("Got product in getProductsByName()",
		// trades);
		// }

		return trades;
	}

	@Override
	public List<Trade> filterByDate(String startTime, String endTime) {
		System.out.println("date1 str : " + startTime);
		System.out.println("date2 str : " + endTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date1, date2;

		try {
			date1 = formatter.parse(startTime);
			date2 = formatter.parse(endTime);
			if(date1.after(date2))
				return null;
			 System.out.println("date1 : "+date1);
			 System.out.println("date2 : "+date2);
			String sql = "SELECT t FROM Trade t WHERE date(t.executionTime) BETWEEN ?1 AND ?2";
			TypedQuery<Trade> query = em.createQuery(sql, Trade.class);
			query.setParameter(1, date1, TemporalType.DATE);
			query.setParameter(2, date2, TemporalType.DATE);
			List<Trade> trades = query.getResultList();
			return trades;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Trade> filterByExecutionTime(String startTime, String endTime) {
		// TODO Auto-generated method stub
		System.out.println("date1 str : " + startTime);
		System.out.println("date2 str : " + endTime);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date date1, date2;

		try {
			date1 = formatter.parse(startTime);
			date2 = formatter.parse(endTime);
			if(date1.after(date2)||date1.equals(date2)==true)
				return null;
			 System.out.println("date1 : "+date1);
			 System.out.println("date2 : "+date2);
			String sql = "SELECT t FROM Trade t WHERE t.executionTime BETWEEN ?1 AND ?2";
			TypedQuery<Trade> query = em.createQuery(sql, Trade.class);
			query.setParameter(1, date1, TemporalType.TIMESTAMP);
			query.setParameter(2, date2, TemporalType.TIMESTAMP);
			List<Trade> trades = query.getResultList();
			return trades;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public List<Trade> filterByCurrency(String currency){
		String sql = "SELECT t FROM Trade AS t WHERE t.currency = :currency";
		
		TypedQuery<Trade> query = em.createQuery(sql, Trade.class);
		query.setParameter("currency", currency);

		List<Trade> trades = query.getResultList();
		return trades;
	}
	
	

}
