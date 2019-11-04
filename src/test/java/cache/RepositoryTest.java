/**
 * 
 */
package cache;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author andre
 *
 */
public class RepositoryTest {
	
	@Test
	public void cacheGetTest() {
		
		APIProvider api = new API();
		
		Cache<String, String> memCache = new Cache<String, String>(1);
		
		memCache.put("Animal", "Dog");
	
		Repository repository =  new Repository(api, memCache);
		
		String value1 = repository.getValue("Animal", false);
		
		assertEquals("Dog", value1);
		
	}
	
	@Test
	public void cacheStoretest() {
		
		APIProvider api = new API();
		
		Cache<String, String> memCache = new Cache<String, String>(1);
	
		Repository repository =  new Repository(api, memCache);
		
		String value1 = repository.getValue("Animal", false);
		
		assertEquals("Elephant", memCache.get("Animal"));
		
	}
	
	@Test
	public void ignoreCacheTest() {
		
		APIProvider api = new API();
		
		Cache<String, String> memCache = new Cache<String, String>(1);
		
		memCache.put("Animal", "Dog");
		
		Repository repository =  new Repository(api, memCache);
		
		String value1 = repository.getValue("Animal", true);
		
		assertEquals("Elephant", value1);
		
		assertEquals("Dog", memCache.get("Animal"));
		
	}
	
	@Test
	public void cacheCapacityTest() {
		
		Cache<String, String> memCache = new Cache<String, String>(1);
		
		memCache.put("Animal1", "Dog");
		
		assertEquals(memCache.get("Animal1"),"Dog");
		
		memCache.put("Animal2", "Elephant");
		
		assertNull(memCache.get("Animal1"));
		
		assertEquals(memCache.get("Animal2"), "Elephant");
		
	}
	
	@Test
	public void cacheTimeToLiveTest() {
		
		Cache<String, String> memCache = new Cache<String, String>(1);
		
		memCache.put("Animal1", "Dog");
		
		assertEquals(memCache.get("Animal1"),"Dog");
		
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		assertNull(memCache.get("Animal1"));
		
	}

}
