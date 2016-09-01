package team1.tradeBlotter.ejb;

import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import team1.tradeBlotter.jpa.Trade;
import team1.tradeBlotter.jpa.Trader;
// this is the junit test file, we tested TradeFilterBean functionalities here
public class TradeFilterBeanTest {
	
	TradeFilterBeanRemote tfbRemote;

	public TradeFilterBeanTest() {
		Properties prop = new Properties();
		prop.put(Context.INITIAL_CONTEXT_FACTORY, org.jboss.naming.remote.client.InitialContextFactory.class.getName()); 
		prop.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		prop.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		prop.put("jboss.naming.client.ejb.context", true);
		
		// Create the JNDI InitialContext.
		Context context;
		try {
			context = new InitialContext(prop);
			tfbRemote = (TradeFilterBeanRemote) context.lookup("TeamOneTradeBlotterFinal/TeamOneTradeBlotterFinalEJB/TradeFilterBean!team1.tradeBlotter.ejb.TradeFilterBeanRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	
	@Ignore
	public void getAllTradesTest() {
		List<Trade> trades = tfbRemote.getAllTrades();
		Assert.assertTrue( trades.size() != 0);
	}
	
	@Ignore
	public void checkLoginValidate() {
		Assert.assertTrue(tfbRemote.checkLogin("user1", "password"));
	}

	@Ignore
	public void checkLoginInvalidate(){
		Assert.assertFalse(tfbRemote.checkLogin("anonymous", null));
	}

	@Ignore
	public void registerTraderValidate() {
		String userName="loszhko";
		String password="!+~yi&ui";
		String firstName = "rahul";
		String lastName = "marathe";
		Assert.assertTrue(tfbRemote.registerTrader(userName,password,firstName,lastName));
		Assert.assertTrue(tfbRemote.checkLogin(userName,password));
	}
	
	@Ignore
	public void registerTraderNoUserName() {
		String userName="";
		String password="!+~yi&ui";
		String firstName = "rahul";
		String lastName = "marathe";
		Assert.assertFalse(tfbRemote.registerTrader(userName,password,firstName,lastName));
	}
	
	@Ignore
	public void registerTraderNoPassword() {
		String userName="87987iuehj";
		String password="";
		String firstName = "rahul";
		String lastName = "marathe";
		Assert.assertFalse(tfbRemote.registerTrader(userName,password,firstName,lastName));
	}
	@Ignore
	public void registerTraderNoFirstName() {
		String userName="urejdfkjd";
		String password="!+~yi&ui";
		String firstName = "";
		String lastName = "marathe";
		Assert.assertFalse(tfbRemote.registerTrader(userName,password,firstName,lastName));
	}
	@Ignore
	public void registerTraderNoLastName() {
		String userName="77jfdkjdkds";
		String password="!+~yi&ui";
		String firstName = "rahul";
		String lastName = "";
		Assert.assertFalse(tfbRemote.registerTrader(userName,password,firstName,lastName));
	}
	

	
	@Ignore
	public void filterByTypeValidate(){
		List <Trade> trades=tfbRemote.filterByType("FX");
		Assert.assertTrue(trades.size()>0);
	}
	
	@Ignore
	public void filterByTypeInvalidate(){
		List <Trade> trades=tfbRemote.filterByType("equity");
		Assert.assertTrue(trades.size()==0);
	}
	
	@Ignore
	public void filterByNameValidate(){
		List <Trade> trades = tfbRemote.filterByName("INFY");
		Assert.assertTrue(trades.size()>0);
	}
	
	@Ignore
	public void filterByNameInvalidate(){
		List <Trade> trades = tfbRemote.filterByName(null);
		Assert.assertTrue(trades.size()==0);
	}
	
	
	@Ignore
	public void filterBySideValidate(){
		List <Trade> trades = tfbRemote.filterBySide((byte) 1);
		Assert.assertTrue(trades.size() >0);
	}
	
	@Ignore
	public void filterBySideInvalidate(){
		List <Trade> trades = tfbRemote.filterBySide((byte) 6);
		Assert.assertTrue(trades==null || trades.size() ==0);
	}
	
	@Ignore
	public void filterByDateValidateAfterDate(){
		List <Trade> trades = tfbRemote.filterByDate("2011-01-01","2012-01-01");
		Assert.assertTrue(trades!=null);
	}
	
	@Ignore
	public void filterByDateValidateEqualDate(){
		List <Trade> trades = tfbRemote.filterByDate("2011-01-01","2011-01-01");
		Assert.assertTrue(trades!=null);
	}
	
	@Ignore
	public void filterByDateInvalidateBeforeDate(){
		List <Trade> trades = tfbRemote.filterByDate("2011-01-01","2010-01-01");
		Assert.assertTrue(trades==null);
	}
	
	@Ignore
	public void filterByExecutionTimeValidateAfterDateTime(){
		List <Trade> trades = tfbRemote.filterByExecutionTime("2013-08-03 08:40:40","2013-08-03 08:50:59");
		Assert.assertTrue(trades!=null);
	}
	
	
	@Ignore
	public void filterByExecutionTimeInvalidateEqualDateTime(){
		List <Trade> trades = tfbRemote.filterByExecutionTime("2013-08-03 08:40:40","2013-08-03 08:40:40");
		Assert.assertTrue(trades==null);
	}
	
	@Ignore
	public void filterByExecutionTimeInvalidateBeforeDateTime(){
		List <Trade> trades = tfbRemote.filterByExecutionTime("2013-08-03 08:40:40","2013-08-03 08:39:59");
		Assert.assertTrue(trades==null);
	}
	
	@Ignore
	public void filterByCurrencyValidate(){
		List <Trade> trades = tfbRemote.filterByCurrency("EUR");
		Assert.assertTrue(trades.size()>0);
	}
	
	@Ignore
	public void filterByCurrencyInvalidate(){
		List <Trade> trades = tfbRemote.filterByCurrency("ABC");
		Assert.assertTrue(trades.size()==0);
	}
}
