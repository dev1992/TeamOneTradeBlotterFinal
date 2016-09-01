package team1.tradeBlotter.web;

import javax.enterprise.context.RequestScoped;
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
import team1.tradeBlotter.jpa.Trader;

//import team1.blotterSB.BlotterSessionBeanLocal;
//import team1.blotterWeb.Trader;

@RequestScoped
@Path("/traders")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class TraderResource {

	private TradeFilterBeanLocal myLocalBean;

	public TraderResource() {
		super();
		try {
			InitialContext context = new InitialContext();
			myLocalBean = (TradeFilterBeanLocal) context.lookup(
					"java:global/TeamOneTradeBlotterFinal/TeamOneTradeBlotterFinalEJB/TradeFilterBean!team1.tradeBlotter.ejb.TradeFilterBeanLocal");
			System.out.println(1);
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}

//	@GET
//	@Produces("application/json")
//	public List<Trader> getTraders() {
//
//		if (myLocalBean == null)
//			return null;
//		return myLocalBean.getAllTraders();
//	}

	@GET
	@Path("/signin")
	@Produces("text/plain")
	public String checkLoginCredentials(@QueryParam("username") String username,
			@QueryParam("password") String password) {
		if (myLocalBean.checkLogin(username, password))
			return "true";
		else
			return "false";
	}

	@POST
	@Path("/signinsecure")
	@Consumes("application/x-www-form-urlencoded")
	public String checkLoginCredentialsSecure(@FormParam("username") String username,
			@FormParam("password") String password) {
		if (myLocalBean.checkLogin(username, password)) {
			return "true";
		} else {
			return "false";
		}
	}

	@GET
	@Path("/register")
	@Produces("text/plain")
	public String registerTrader(@QueryParam("userName") String username, @QueryParam("password") String password,
			@QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) {
		if (myLocalBean.registerTrader(username, password, firstName, lastName))
			return "true";
		else
			return "false";
	}

	@GET
	@Path("/registersecure")
	@Produces("text/plain")
	public String registerTraderSecure(@FormParam("userName") String username, @FormParam("password") String password,
			@FormParam("firstName") String firstName, @FormParam("lastName") String lastName) {
		if (myLocalBean.registerTrader(username, password, firstName, lastName))
			return "true";
		else
			return "false";
	}

}
