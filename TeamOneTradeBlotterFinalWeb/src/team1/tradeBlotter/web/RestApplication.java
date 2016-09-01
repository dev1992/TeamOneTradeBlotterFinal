package team1.tradeBlotter.web;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

//import team1.blotterWeb.TraderResource;
import team1.tradeBlotter.web.TradeResource;
import team1.tradeBlotter.web.TraderResource;

@ApplicationPath("/rest")
public class RestApplication extends Application {
	
	private Set<Object> singletons = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();
	public RestApplication() {
		
		singletons.add(new TradeResource());
		singletons.add(new TraderResource());
		singletons.add(new MathResource());
	}
	
	@Override
	public Set<Class<?>> getClasses() {
	     return empty;
	}
	
	@Override
	public Set<Object> getSingletons() {
	     return singletons;
	}
	
	

}
