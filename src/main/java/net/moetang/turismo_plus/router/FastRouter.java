package net.moetang.turismo_plus.router;

import net.moetang.turismo_plus.pipeline.processing.Resolver;

public abstract class FastRouter implements Router {
	
	protected abstract void map();

	@Override
	public Resolver resolver() {
		// TODO Auto-generated method stub
		return null;
	}

}
