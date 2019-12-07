package sh.utils.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//parallenStream 사용시 병렬
//orders.스트림 생성().중간 연산().최종 연산() 
public class stream {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static void main(String[] args) {
		stream st = new stream();
		String[] arr = new String[] {"a","b","c","a","d"};
		int[] intArr = new int[] {3,5,6,7,11};
		List<String> list = Arrays.asList(arr);
		List<Integer> intList = Arrays.stream(intArr).boxed().collect(Collectors.toList());
		
		
		st.listContainCount(list);
		st.forEach(list);
		
		
	}
	
	/**
	 * 리스트 및 배열에서 containcount 
	 * @author SH
	 * @param List<String>
	 * @return count
	 */
	public int listContainCount(List<String> list) {
		//count(), min(), max(), sum(), average()
		int cnt = (int) list.stream().filter(x->x.contains("a")).count();
		logger.info(String.valueOf(cnt));
		
		return cnt;
	}
	
	/**
	 * 리스트 foreach
	 * @author SH
	 * @param List<String>
	 */
	public void forEach(List<String> list) {
		
		list.stream().forEach(x->logger.info(x));
	}
	
}
