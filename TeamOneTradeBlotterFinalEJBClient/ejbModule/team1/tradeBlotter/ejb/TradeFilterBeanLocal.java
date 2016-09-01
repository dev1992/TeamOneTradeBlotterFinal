package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Local;

import team1.tradeBlotter.jpa.Trader;
import team1.tradeBlotter.jpa.Trade;

@Local
public interface TradeFilterBeanLocal {

	public List<Trade> getAllTrades();

	//public List<Trader> getAllTraders();

	public boolean checkLogin(String username, String password);

	public boolean registerTrader(String userName, String password, String firstName, String lastName);

	public List<Trade> filterByType(String productType);

	public List<Trade> filterByName(String name);
<<<<<<< HEAD

	//public List<Trade> filterByPrice(double price1, double price2);
=======
	
	public List<Trade> filterByUser(String userName);

	public List<Trade> filterByPrice(double price1, double price2);

	public List<Trade> filterByQuantity(int quan);
>>>>>>> master

	public List<Trade> filterBySide(byte side);

	public List<Trade> filterByDate(String startTime, String endTime);

	public List<Trade> filterByExecutionTime(String startTime, String endTime);

	public List<Trade> filterByCurrency(String currency);
}
