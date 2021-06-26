package demo.bestbuy.com.scenariocontext;

import java.util.HashMap;

public class ScenarioContext {

	@SuppressWarnings("rawtypes")
	private static HashMap<Class, Object> scenarioContext = new HashMap<Class, Object>();
	
	public void setContext(@SuppressWarnings("rawtypes") Class clazz, Object value) {
		scenarioContext.put(clazz, value);
	}
	
	public Object getContext(@SuppressWarnings("rawtypes") Class clazz) {
		return scenarioContext.get(clazz);
	}
}
