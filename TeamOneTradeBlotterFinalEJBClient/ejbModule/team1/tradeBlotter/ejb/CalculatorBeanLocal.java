package team1.tradeBlotter.ejb;

import javax.ejb.Local;

@Local
public interface CalculatorBeanLocal {
	public double evaluate(String string);
}
