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
package net.moetang.turismo_plus.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.moetang.nekocore.ioc.IocContainer;
import net.moetang.nekocore.ioc.container.helper.SimpleAnnotationIocContainerHelper;
import net.moetang.turismo_plus.pipeline.router.Router;
import net.moetang.turismo_plus.util.AutoLoaded;
import net.moetang.turismo_plus.util.Env;
public class StartPointFilter implements Filter {
	private List<Router> routerList;
	private List<AutoLoaded> loadedModule;
	
	private IocContainer iocContainer;
	private SimpleAnnotationIocContainerHelper iocContainerHelper;

	@Override
	public final void destroy() {
		iocContainer = null;
		iocContainerHelper = null;
		Env.setIocContainer(null);
		if(loadedModule != null){
			for(AutoLoaded al : loadedModule){
				try {
					al.unload();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public final void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		try {
			Env.createReq((HttpServletRequest)request, (HttpServletResponse)response, filterChain);
			Env env = Env.get();
			Env.doReq(routerList);
			Env.doResult(env.getResult());
		} finally{
			Env.endCurReq();
		}
	}

	@Override
	public final void init(FilterConfig filterConfig) throws ServletException {
		//加载Ioc容器
		iocContainerHelper = new SimpleAnnotationIocContainerHelper();
		iocInit(iocContainerHelper);
		this.iocContainer = iocContainerHelper.createContainer();
		Env.setIocContainer(iocContainer);
		
		//加载自动装载
        autoLoad();

		routerList = new ArrayList<>();
		loadedModule = new ArrayList<>();
        final String routesParam = filterConfig.getInitParameter(ROUTES);
        if(routesParam != null){
            String[] routes = routesParam.split(",");
            for(String route : routes){
        		try {
					routerList.add(createInstance(route, Router.class));
				} catch (Exception e) {
					e.printStackTrace();
				}
        	}
        }else{
        	// way 1st : subclass loads all routers
        	routers();
        }
	}

	private static final String ROUTES = "routes";
	
	protected void iocInit(SimpleAnnotationIocContainerHelper helper) {
		
	}
    
    protected void routers() {
		
	}
    protected final void add(Class<? extends Router> routerClazz) {
    	try {
			routerList.add(routerClazz.newInstance());
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
    protected final void add(Router... routers) {
		for(Router router : routers){
			routerList.add(router);
		}
	}
    
    protected void autoLoad() {
		
	}
    protected final void addAutoLoad(AutoLoaded... autoLoad) {
		for(AutoLoaded a : autoLoad){
			try {
				a.load();
				loadedModule.add(a);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

    private final Router createInstance(String route, Class<Router> class1) {
    	try {
			return (Router) Class.forName(route).newInstance();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

}
