/*
 * Copyright (c) 2013 goodplayer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.moetang.turismo_plus.pipeline.router;

import java.util.HashSet;
import java.util.Set;

import net.moetang.turismo_plus.pipeline.actionresult.ActionResult;
import net.moetang.turismo_plus.pipeline.actionresult.ContinueResult;
import net.moetang.turismo_plus.pipeline.processing.Resolver;

public abstract class PrefixExcludeRouter implements Router {
	public PrefixExcludeRouter() {
		exclude();
		makeResolver();
	}
	
	protected abstract void exclude();
	
	protected final void addPrefix(String prefix){
		if(resolver == null){
			if(prefix == null || prefix.length() == 0)
				return;
			if(!prefix.endsWith("/"))
				prefix = prefix + "/";
			prefixes.add(prefix);
		}
	}
	
	private Set<String> prefixes = new HashSet<>();
	
	private void makeResolver(){
		this.resolver = new ResolverImpl();
	}
	private Resolver resolver;

	@Override
	public Resolver resolver() {
		return resolver;
	}
	
	private class ResolverImpl implements Resolver{
		private String[] prefixArray = prefixes.toArray(new String[0]);
		@Override
		public ActionResult resolve(String method, String uri) {
			for(String prefix : prefixArray){
				if(uri.startsWith(prefix))
					return CONTINUE;
			}
			return null;
		}
	}
	
	private static final ActionResult CONTINUE = new ContinueResult();

}
