package demo.bestbuy.com.scenariocontext;

import java.util.HashMap;

public class ScenarioContext<T> {

	
	private static HashMap<Class<?>, Object> scenarioContext = new HashMap<Class<?>, Object>();
	
	@SuppressWarnings("hiding")
	public <T> void setContext(Class<T> clazz, Object value) {
		scenarioContext.put(clazz, value);
	}
	
	@SuppressWarnings("unchecked")
	public T getContext(Class<T> clazz) {
		return (T) scenarioContext.get(clazz);
	}
}
