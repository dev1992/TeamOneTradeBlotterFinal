package team1.tradeBlotter.ejb;

import javax.ejb.Remote;

@Remote
public interface CalculatorBeanRemote {
	public double evaluate(String string);
}
