package net.moetang.turismo_plus.util;

//depend on the last action in Befores
public interface BeforeChain {
	public void doNext();
	// skip all befores
	public void stopBefores();
	// skip all befores and action
	public void stopAction();
	// skip all - stop processing
	public void stopAll();
}
