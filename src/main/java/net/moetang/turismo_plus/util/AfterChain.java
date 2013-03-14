package net.moetang.turismo_plus.util;

//depend on the last action in AfterAlls
public interface AfterChain {
	public void doNext();
	public void stopAfters();
}
