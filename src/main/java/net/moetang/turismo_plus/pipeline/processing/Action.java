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
package net.moetang.turismo_plus.pipeline.processing;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.moetang.turismo_plus.pipeline.actionresult.ActionResult;
import net.moetang.turismo_plus.pipeline.actionresult.DispatcherResult;
import net.moetang.turismo_plus.util.Env;
import net.moetang.turismo_plus.util.FilterChain;


public abstract class Action implements IAction {
	protected Filter[] filters;
	public Action() {
		this.filters = new Filter[0];
	}
	public Action(Filter...filters){
		this.filters = filters;
	}
	private FilterChain createFilterChain(final Filter[] filters){
		if(filters.length == 0){
			return new FilterChain() {
				@Override
				public void doNext(Env env) {
					action(env);
				}
			};
		}else{
			return new FilterChain() {
				private int index = 0;
				@Override
				public void doNext(Env env) {
					if(index < filters.length){
						filters[index++].doit(env, this);
					}else{
						action(env);
					}
				}
			};
		}
	}

	public abstract void action(Env env);

	@Override
	public void doAction(Env env) {
		this.createFilterChain(filters).doNext(env);
	}

    protected String params(String key) {
        return Env._getParam(key);
    }
    protected String[] paramSets(String key) {
		return Env._getParamArray(key);
	}
    
    protected Object attr(String key) {
		return Env._getAttri(key);
	}
	protected <T> T attr(String key, Class<T> clazz){
    	try {
			return clazz.cast(attr(key));
		} catch (ClassCastException e) {
			return null;
		}
    }
	protected void setAttr(String key, Object value){
		req().setAttribute(key, value);
	}

    protected HttpServletRequest req() {
        return Env._req();
    }

    protected HttpServletResponse res() {
        return Env._res();
    }

    protected ServletContext ctx() {
        return Env._ctx();
    }

    protected void alias(String target) {
        forward(target);
    }

    protected void forward(String target) {
    	Env._setResult(new DispatcherResult(target));
    }

    protected void jsp(String path) {
        forward(path);
    }

    protected void result(ActionResult actionResult){
    	Env._setResult(actionResult);
    }

    protected void movedPermanently(final String newLocation) {
        Env._setResult(new ActionResult() {
			@Override
			public void doResult() {
		        Env._res().setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
		        Env._res().setHeader("Location", String.valueOf(newLocation));
			}
        });
    }

    protected void movedTemporarily(final String newLocation) {
    	Env._setResult(new ActionResult() {
			@Override
			public void doResult() {
		        Env._res().setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
		        Env._res().setHeader("Location", newLocation);
			}
        });
    }

    protected void notFound() {
    	Env._setResult(new ActionResult() {
			@Override
			public void doResult() {
		        try {
		            Env._res().sendError(HttpServletResponse.SC_NOT_FOUND);
		        } catch (IOException e) {
		            throw new RuntimeException(e);
		        }
			}
        });
    }

    protected void redirect(final String newLocation) {
    	Env._setResult(new ActionResult() {
			@Override
			public void doResult() {
		        try {
		            Env._res().sendRedirect(String.valueOf(newLocation));
		        } catch (IOException e) {
		            throw new RuntimeException(e);
		        }
			}
        });
    }

    protected void print(String string) {
        try {
			Env._res().getWriter().write(string);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }

}
