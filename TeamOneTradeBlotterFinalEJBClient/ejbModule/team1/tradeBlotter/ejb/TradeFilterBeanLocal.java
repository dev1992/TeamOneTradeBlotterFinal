package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Local;

import team1.tradeBlotter.jpa.Trade;

@Local
public interface TradeFilterBeanLocal {

	public List<Trade> getAllTrades ();
}
