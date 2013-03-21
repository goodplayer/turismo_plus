package net.moetang.turismo_plus.pipeline.actionresult;

import net.moetang.turismo_plus.util.Env;

public abstract class TemplateResult extends ActionResult {
	
	public TemplateResult() {
		this.initEngine();
	}

	@Override
	public void doResult() {
		this.render(Env.get());
	}
	
	protected abstract void initEngine();
	
	protected abstract void render(Env env);

}
