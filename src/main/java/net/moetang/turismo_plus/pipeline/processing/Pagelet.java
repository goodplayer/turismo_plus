package net.moetang.turismo_plus.pipeline.processing;

import net.moetang.turismo_plus.util.Env;

public interface Pagelet {
	public void flushPagelet(Env env);
}
