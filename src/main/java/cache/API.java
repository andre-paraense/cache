package cache;
/**
 * 
 */

/**
 * @author andre
 *
 */
public class API implements APIProvider{


	public String get(String key) {
		
		try {
			Thread.sleep(100L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return "Elephant";
	}

}
