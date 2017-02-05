import java.util.List;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
public class EhcacheTest {

	CacheManager manager = CacheManager.getInstance();

	public EhcacheTest() {
		this.manager = CacheManager.getInstance();
	}

	public void getEhcacheDtoList(int jobNo, int status){

		List<EhcacheDto> ehcacheDtoList = null;
		if (status >= 10 && status <= 20){

			Cache myCache = manager.getCache("myCache");
			Element e = myCache.get(-2);
			if (e == null){
				ehcacheDtoList = java.util.Arrays.asList(null,null);
				myCache.put(new Element(-2, ehcacheDtoList));
			} else {
				ehcacheDtoList = (List<EhcacheDto>)e.getObjectValue();
			}
			if (!((status >= 12) && (status <= 17))){
				setEhcacheDto(jobNo, status, ehcacheDtoList);
			}

		} else {
			Cache myCache = manager.getCache("myCache");
			Element e = myCache.get(-2);
			if (e != null){
				ehcacheDtoList = (List<EhcacheDto>)e.getObjectValue();
				removeEhcacheDto(jobNo, ehcacheDtoList);
			}
		}
		debug(jobNo,status,ehcacheDtoList);


	}

	private void setEhcacheDto(int jobNo, int status,List<EhcacheDto> ehcacheDtoList) {

		boolean existFlg = false;
		for(int i = 0; i< ehcacheDtoList.size(); i++){
			if (ehcacheDtoList.get(i) != null && ehcacheDtoList.get(i).getJobNo() == jobNo) {
				existFlg = true;
				ehcacheDtoList.get(i).setStatus(status);
				break;
			}
		}
		if (!existFlg){
			for(int i = 0; i< ehcacheDtoList.size(); i++){
				if (ehcacheDtoList.get(i) == null) {
					ehcacheDtoList.set(i, new EhcacheDto(jobNo, status));
					break;
				}
			}
		}
	}

	private void removeEhcacheDto(int jobNo, List<EhcacheDto> ehcacheDtoList) {
		for(int i = 0; i< ehcacheDtoList.size(); i++) {
			if (ehcacheDtoList.get(i) != null && ehcacheDtoList.get(i).getJobNo() == jobNo) {
				ehcacheDtoList.set(i, null);
				break;
			}
		}
	}

	private void debug(int jobNo, int status,List<EhcacheDto> ehcacheDtoList) {

		System.out.println("jobNo=" + jobNo + " status=" + status);
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
	}



}
