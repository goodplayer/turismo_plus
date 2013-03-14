package net.moetang.turismo_plus.router;

import java.util.HashMap;
import java.util.Map;

import net.moetang.turismo_plus.pipeline.processing.IAction;
import net.moetang.turismo_plus.pipeline.processing.Resolver;

public abstract class FastRouter implements Router {
	
	protected abstract void map();
	
	private Map<String, Map<String, IAction>> map = new HashMap<>();

	@Override
	public Resolver resolver() {
		// TODO Auto-generated method stub
		return null;
	}

}
