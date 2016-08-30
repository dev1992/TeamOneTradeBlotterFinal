package team1.tradeBlotter.web;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import team1.tradeBlotter.ejb.TradeFilterBeanLocal;
import team1.tradeBlotter.jpa.Trade;

@Path("/trades")
public class TradeResource {

	private TradeFilterBeanLocal tradeFilterBean;

	public TradeResource() {
		super();
		try {
			InitialContext context = new InitialContext();
			this.tradeFilterBean = (TradeFilterBeanLocal) context.lookup(
					"java:global/TeamOneTradeBlotterFinal/TeamOneTradeBlotterFinalEJB/TradeFilterBean!team1.tradeBlotter.ejb.TradeFilterBeanLocal");
			System.out.println(1);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	@GET
	@Produces("application/json")
	public List<Trade> getAllTrades() {
		List<Trade> allTrades = tradeFilterBean.getAllTrades();
		return allTrades;
	}

}
