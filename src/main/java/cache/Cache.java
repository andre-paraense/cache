package cache;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 
 */

/**
 * @author andre
 *
 */
public class Cache<T,V> {
	
	private Map<T, V> cacheMap;
	
	private Integer size;
	
	private Stack<T> keyStack;
	
	private Long timeToLive = 2000L;
	
	private Map<T,Long> keyBirthDateMap;
	
	public Cache(Integer size) {	
		cacheMap = new LinkedHashMap<T, V>();
		keyStack = new Stack<T>();
		this.size = size;
		keyBirthDateMap = new HashMap<T, Long>();
	}

	public V get(T key) {
		
		Long keyBirthDate = keyBirthDateMap.get(key);
		
		if(keyBirthDate == null) {
			return null;
		}
		
		Long lifeTime = System.currentTimeMillis() - keyBirthDate;
		
		if(lifeTime > timeToLive) {
			keyBirthDateMap.remove(key);
			cacheMap.remove(key);
			return null;
		}
		
		return cacheMap.get(key);
	}

	public void put(T key, V value) {
		
		if(key == null || value == null) {
			return;
		}
		
		if(cacheMap.size() >= size) {
			T keyFromStack = keyStack.pop();
			cacheMap.remove(keyFromStack);
		}
		
		cacheMap.put(key, value);
		keyStack.push(key);
		
		keyBirthDateMap.put(key,System.currentTimeMillis());
	}
}
