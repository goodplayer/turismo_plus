package net.moetang.turismo_plus.pipeline.router;

import java.util.HashSet;
import java.util.Set;

import net.moetang.turismo_plus.pipeline.actionresult.ActionResult;
import net.moetang.turismo_plus.pipeline.actionresult.ContinueResult;
import net.moetang.turismo_plus.pipeline.processing.Resolver;

public abstract class SuffixExcludeRouter implements Router {
	public SuffixExcludeRouter() {
		exclude();
		makeResolver();
	}
	
	protected abstract void exclude();
	
	protected final void addSuffix(String suffix){
		if(resolver == null){
			if(suffix == null || suffix.length() == 0)
				return;
			suffixes.add(suffix);
		}
	}
	
	private Set<String> suffixes = new HashSet<>();
	
	private void makeResolver(){
		this.resolver = new ResolverImpl();
	}
	private Resolver resolver;

	@Override
	public Resolver resolver() {
		return resolver;
	}
	
	private class ResolverImpl implements Resolver{
		private String[] suffixArray = suffixes.toArray(new String[0]);
		@Override
		public ActionResult resolve(String method, String uri) {
			for(String suffix : suffixArray){
				if(uri.endsWith(suffix))
					return CONTINUE;
			}
			return null;
		}
	}
	
	private static final ActionResult CONTINUE = new ContinueResult();
}
