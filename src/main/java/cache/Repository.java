package cache;

/**
 * 
 */

/**
 * @author andre
 *
 */
public class Repository {
	
	private Cache<String, String> memCache;
	
	private APIProvider api;
	
	public Repository(APIProvider apiProvider, Cache<String, String> memCache) {
		
		this.memCache = memCache;		
		api = apiProvider;
		
	}
	
	public String getValue(String key, boolean ignoreCache) {
		
		if(ignoreCache) {
			return api.get(key);
		}
		
		String value = memCache.get(key);
		
		if(value != null) {
			
			return value;
			
		}else {		
			value = api.get(key);
			memCache.put(key,value);	
		}
		
		return value;		
	}
}
