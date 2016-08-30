import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import team1.tradeBlotter.ejb.TradeFilterBeanRemote;
import team1.tradeBlotter.jpa.Trade;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
try {
			
			// Create Properties for JNDI InitialContext.
			Properties prop = new Properties();
			prop.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName()); 
			prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
			prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
			prop.put("jboss.naming.client.ejb.context", true);
			
			// Create the JNDI InitialContext.
			Context context = new InitialContext(prop);
			
			// Formulate the full JNDI name for the Diary session bean.
			String appName     = "TeamOneTradeBlotterFinal";
			String moduleName  = "TeamOneTradeBlotterFinalEJB";
			String beanName    = "TradeFilterBean";
			String packageName = "team1.tradeBlotter.ejb";
			String className   = "TradeFilterBeanRemote";
			
			// Lookup the bean using the full JNDI name.
			System.out.println(0);
			String fullJndiName = String.format("%s/%s/%s!%s.%s", appName, moduleName, beanName, packageName, className);
			TradeFilterBeanRemote bean = (TradeFilterBeanRemote) context.lookup("TeamOneTradeBlotterFinal/TeamOneTradeBlotterFinalEJB/TradeFilterBean!team1.tradeBlotter.ejb.TradeFilterBeanRemote");
			System.out.println(1);
			List<Trade> products = bean.getAllTrades();
			displayProducts("All products", products);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Exception: " + ex.getMessage());
		}
	}

	/* (non-Java-doc)
	 * @see java.lang.Object#Object()
	 */
	public Main() {
		super();
	}
	
private static void displayProducts(String message, List<Trade> trades) {
		
		System.out.printf("\n%s\n", message);
		for (Trade trade: trades) {
			System.out.println(trade.getProductName());
		}
	}

}