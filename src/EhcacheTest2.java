import java.util.Collections;
import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
public class EhcacheTest2 {

	CacheManager manager = CacheManager.getInstance();

	public EhcacheTest2() {
		this.manager = CacheManager.getInstance();
	}

	public void getEhcacheDtoList(String name, int jobNo, int status){

		List<EhcacheDto> ehcacheDtoList = null;

		Cache myCache = manager.getCache("myCache");
		Element e = myCache.get(-2);
		if (e == null){
			ehcacheDtoList = java.util.Arrays.asList(null,null);
			myCache.put(new Element(-2, ehcacheDtoList));
		} else {
			ehcacheDtoList = (List<EhcacheDto>)e.getObjectValue();
		}
		ehcacheDtoList = Collections.synchronizedList(ehcacheDtoList);

		setEhcacheDto(1,1,ehcacheDtoList);
		setEhcacheDto(2,2,ehcacheDtoList);

		myCache = manager.getCache("myCache");
		e = myCache.get(-2);
		if (e == null){
			ehcacheDtoList = java.util.Arrays.asList(null,null);
			myCache.put(new Element(-2, ehcacheDtoList));
		} else {
			ehcacheDtoList = (List<EhcacheDto>)e.getObjectValue();
		}

		debug(name + " set後", jobNo,status,ehcacheDtoList);



		removeEhcacheDto(1,ehcacheDtoList);

		myCache = manager.getCache("myCache");
		e = myCache.get(-2);
		if (e == null){
			ehcacheDtoList = java.util.Arrays.asList(null,null);
			myCache.put(new Element(-2, ehcacheDtoList));
		} else {
			ehcacheDtoList = (List<EhcacheDto>)e.getObjectValue();
		}

		debug(name + " remove後", jobNo,status,ehcacheDtoList);


	}

	private void setEhcacheDto(int jobNo, int status,List<EhcacheDto> ehcacheDtoList) {

		ehcacheDtoList.set(0, new EhcacheDto(jobNo, status));
		ehcacheDtoList.set(1, new EhcacheDto(jobNo, status));

	}

	private void removeEhcacheDto(int jobNo, List<EhcacheDto> ehcacheDtoList) {

		ehcacheDtoList.set(0, null);

	}

	private void debug(String message, int jobNo, int status,List<EhcacheDto> ehcacheDtoList) {

		System.out.println(message + "_start");
//		System.out.println("jobNo=" + jobNo + " status=" + status);
		if (ehcacheDtoList == null){
			System.out.println("なし");
		} else {
			for(int i = 0; i< ehcacheDtoList.size(); i++) {
				if (ehcacheDtoList.get(i) == null) {
					System.out.println("i=" + i + " null");
				} else {
					System.out.println("i=" + i + " " + ehcacheDtoList.get(i).getJobNo() + " " + ehcacheDtoList.get(i).getStatus());
				}
			}
		}
		System.out.println(message + "_end");

	}



}
