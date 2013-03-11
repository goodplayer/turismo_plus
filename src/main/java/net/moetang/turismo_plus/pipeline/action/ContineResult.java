package net.moetang.turismo_plus.pipeline.action;

import net.moetang.turismo_plus.util.Env;

public class ContineResult extends ActionResult {

	@Override
	public void doResult() {
		Env.doFilterChain();
	}

}
