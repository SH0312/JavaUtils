package sh.utils.designPattern.strategy;

public class StrategyInstance {
	private StrategyInterface strategyInterface;
	
	public StrategyInstance(StrategyInterface strategyInterface) {
		this.strategyInterface = strategyInterface;
	}
	
	public void action() {
		strategyInterface.action();
	}
	
	public void setStrategyInterface(StrategyInterface strategyInterface) {
		this.strategyInterface = strategyInterface;
	}
}
