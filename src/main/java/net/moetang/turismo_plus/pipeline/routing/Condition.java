package net.moetang.turismo_plus.pipeline.routing;

import net.moetang.turismo_plus.util.Env;


public interface Condition {
	public boolean test(Env env);
}
