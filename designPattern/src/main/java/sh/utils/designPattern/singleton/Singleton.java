package sh.utils.designPattern.singleton;

public class Singleton {
	private static Singleton singleton = null;
	
	//test
	private int test = 0;
	
	
	/**
	 * 다른 사람은 접근 못하도록 생성자 구현
	 * 
	 */
	private Singleton() {
	}
	
	/**
	 * 싱글톤 객체 생성
	 * 스레드의 안정성을 위해 synchronized 사용하여 객체 생성/ 속도 이슈
	 *스레드 사용하지 않을 경우 synchronized  없이 사용
	 * 
	 * @return
	 */
	public synchronized static Singleton getInstance() {
		if(singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}
	
	//test
	public void test() {
		test++;
		System.out.println(test);
	}

}
