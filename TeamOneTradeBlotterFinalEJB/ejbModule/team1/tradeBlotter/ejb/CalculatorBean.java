package team1.tradeBlotter.ejb;

import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


/**
 * Session Bean implementation class CalculatorBean
 */
@Stateless
@Remote(CalculatorBeanRemote.class)
@Local(CalculatorBeanLocal.class)
public class CalculatorBean implements CalculatorBeanRemote, CalculatorBeanLocal {

	/**
	 * Default constructor.
	 */
	public CalculatorBean() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public double evaluate(String string) {
		ScriptEngineManager mgr = new ScriptEngineManager();
	    ScriptEngine engine = mgr.getEngineByName("JavaScript");
	    //String foo = "40+2";
	    double result=0;
	    try {
			switch (engine.eval(string).getClass().toString()) {
			case "java.lang.Double":
				result = (Double) engine.eval(string);
				break;
			case "java.lang.Integer":
				result=(Integer) engine.eval(string);
				break;
			default:
				break;
			}
			result= (int) engine.eval(string);
		} catch (ScriptException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    return result;
	}

}
