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

import team1.tradeBlotter.ejb.CalculatorBeanLocal;
@RequestScoped
@Path("/math")
@Produces({ "application/xml", "application/json" })
@Consumes({ "application/xml", "application/json" })
public class MathResource {
	private CalculatorBeanLocal calculatorBeanLocal;
	
	public MathResource(){
		super();
		
		try {
			InitialContext context = new InitialContext();
			calculatorBeanLocal = (CalculatorBeanLocal) context.lookup(
					"java:global/TeamOneTradeBlotterFinal/TeamOneTradeBlotterFinalEJB/CalculatorBean!team1.tradeBlotter.ejb.CalculatorBeanLocal");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@GET
	@Path("/calc")
	@Produces("text/plain")
	public String calculate(@QueryParam("exp") String string){
		Double res = calculatorBeanLocal.evaluate(string);
		return res.toString();
	}
	
	
	@POST
	@Path("/calcSecure")
	@Produces("text/plain")
	public String calculatePost(@FormParam("exp") String string){
		Double res = calculatorBeanLocal.evaluate(string);
		return res.toString();
	}
}
