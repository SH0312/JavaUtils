package sh.utils.designPattern.singleton;

public class Main {
	public static void main(String[] args) {
		
		//객체를 공유하여 사용
		Singleton singleton1 = Singleton.getInstance();
		Singleton singleton2 = Singleton.getInstance();
		
		System.out.println("== single ton ==");
		singleton1.test();
		singleton2.test();
		singleton1.test();
		singleton2.test();
	
		
		
		//객체를 개별로 사용
		Normal normal1 = new Normal();
		Normal normal2 = new Normal();
		
		System.out.println("== normal ==");
		normal1.test();
		normal2.test();
		normal1.test();
		normal2.test();
	}
}