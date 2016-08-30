package team1.tradeBlotter.ejb;

import java.util.List;

import javax.ejb.Remote;

import team1.tradeBlotter.jpa.Trade;

@Remote
public interface TradeFilterBeanRemote {

	public List<Trade> getAllTrades ();
}
