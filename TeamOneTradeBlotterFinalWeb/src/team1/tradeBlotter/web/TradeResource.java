package team1.tradeBlotter.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/trade")
public class TradeResource {
	
	@GET
	@Produces("text/plain")
	public String getPersonName() {
		System.out.println("Received a GET request for persons");
		return "Hello from rest";
	}

}
