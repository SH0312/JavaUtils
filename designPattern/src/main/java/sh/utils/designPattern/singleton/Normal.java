package sh.utils.designPattern.singleton;

public class Normal {
	
	//test
	private int test = 0;
	
	/**
	 * 기본 생성자
	 * 
	 */
	public Normal() {
	}
	
	
	public void test() {
		test++;
		System.out.println(test);
	}

}
