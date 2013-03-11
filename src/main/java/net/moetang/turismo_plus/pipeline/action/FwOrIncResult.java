package net.moetang.turismo_plus.pipeline.action;

import net.moetang.turismo_plus.util.Env;

public class FwOrIncResult extends ActionResult {
	private String method;
	private String 

	@Override
	public void doResult() {
		Env.doReq("GET", "/sdfsf");
	}

}
