package sh.utils.designPattern.strategy;

public class Main {
	public static void main(String[] args) {
		
		StrategyInstance si;
		
		//타겟 메소드 설정
		si = new StrategyInstance(new Process_1());
		si.action();
		
		//타겟 메소드 설정
		si.setStrategyInterface(new Process_2());
		si.action();
		
		//타겟 메소드 설정
		si.setStrategyInterface(new Process_3());
		si.action();
		
		//타겟 메소드 설정
		si.setStrategyInterface(new Process_4());
		si.action();
		
	}
}