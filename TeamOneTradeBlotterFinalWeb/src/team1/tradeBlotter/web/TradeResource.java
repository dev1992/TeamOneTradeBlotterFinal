package team1.tradeBlotter.web;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import team1.tradeBlotter.ejb.TradeFilterBeanLocal;
import team1.tradeBlotter.jpa.Trade;

@Path("/trades")
public class TradeResource {

	private TradeFilterBeanLocal myLocalBean;

	public TradeResource() {
		super();
		try {
			InitialContext context = new InitialContext();
			myLocalBean = (TradeFilterBeanLocal) context.lookup(
					"java:global/TeamOneTradeBlotterFinal/TeamOneTradeBlotterFinalEJB/TradeFilterBean!team1.tradeBlotter.ejb.TradeFilterBeanLocal");
			//System.out.println(1);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

	@GET
	@Produces("application/json")
	public List<Trade> getTrades() {

		if (myLocalBean == null)
			return null;
		return myLocalBean.getAllTrades();

	}

	@GET
	@Path("/filterbytype")
	@Produces("application/json")
	public List<Trade> filterByProductType(@QueryParam("productType") String productType) {
		if (myLocalBean != null)
			return myLocalBean.filterByType(productType);
		else
			return null;

	}
	
	@GET
	@Path("/filterbyuser")
	@Produces("application/json")
	public List<Trade> filterByUserName(@QueryParam("userName") String userName) {
		if (myLocalBean != null)
			return myLocalBean.filterByUser(userName);
		else
			return null;

	}

	@POST
	@Path("/filterbytypesecure")
	@Consumes("application/x-www-form-urlencoded")
	public List<Trade> filterByProductTypeSecure(@FormParam("productType") String productType) {
		if (myLocalBean != null)
			return myLocalBean.filterByType(productType);
		else
			return null;

	}

<<<<<<< HEAD
=======
	@GET
	@Path("/filterbyname")
	@Produces("application/json")
	public List<Trade> filterByName(@QueryParam("productName") String productName) {
		if (myLocalBean != null)
			return myLocalBean.filterByName(productName);
		else
			return null;

	}

	@GET
	@Path("/filterbyquantity")
	@Produces("application/json")
	public List<Trade> filterByQuantity(@QueryParam("quantity") int quantity) {
		if (myLocalBean != null)
			return myLocalBean.filterByQuantity(quantity);
		else
			return null;
>>>>>>> master



//	@GET
//	@Path("/filterbyprice")
//	@Produces("application/json")
//	public List<Trade> filterByprice(@QueryParam("price1") double price1, @QueryParam("price2") double price2) {
//		if (myLocalBean != null)
//			return myLocalBean.filterByPrice(price1, price2);
//		else
//			return null;
//
//	}

	@GET
	@Path("/filterbyside")
	@Produces("application/json")
	public List<Trade> filterBySide(@QueryParam("side") byte side) {
		if (myLocalBean != null)
			return myLocalBean.filterBySide(side);
		else
			return null;

	}

	@GET
	@Path("/filterbydate")
	@Produces("application/json")
	public List<Trade> filterByDate(@QueryParam("startDate") String startTime, @QueryParam("endDate") String endTime) {
		if (myLocalBean != null)
			return myLocalBean.filterByDate(startTime, endTime);
		else
			return null;

	}

//	@GET
//	@Path("/filterbyprice")
//	@Produces("application/json")
//	public List<Trade> filterByPrice(@QueryParam("price1") double price1, @QueryParam("price2") double price2) {
//			return myLocalBean.filterByPrice(price1, price2);
//	}
	
	@GET
	@Path("/filterbyexecutiontime")
	@Produces("application/json")
	public List<Trade> filterByTime(@QueryParam("startTime") String startTime, @QueryParam("endTime") String endTime){
		System.out.println("In REST URL : "+startTime);
		System.out.println("In REST URL : "+endTime);
		return myLocalBean.filterByExecutionTime(startTime, endTime);
	}
	

	@GET
	@Path("/filterbycurrency")
	@Produces("application/json")
	public List<Trade> filterByCurrency(@QueryParam("currency") String currency) {
			return myLocalBean.filterByCurrency(currency);
	}
	
	

}
