package net.moetang.turismo_plus.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.moetang.turismo_plus.pipeline.actionresult.ActionResult;
import net.moetang.turismo_plus.router.Router;

public class Env {
    private static ThreadLocal<Env> locals = new ThreadLocal<Env>();
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;
    private ActionResult actionResult;
    private List<Router> curRouters;
    private Map<String, String> params =  new HashMap<>();

    private Env(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain) {
    	this.request = request;
    	this.response = response;
	}

	public static void createReq(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain) {
		locals.set(new Env(request, response, filterChain));
	}

	public static void endCurReq() {
		locals.remove();
	}
	
	public static ActionResult getResult(){
		return get().actionResult;
	}
	
	public static void setResult(ActionResult actionResult){
		get().actionResult = actionResult;
	}
	
	/**
	 * push request to next one , no matter when there is another handler in the container's handle chain
	 */
	public static void doChain(){
		try {
			get().filterChain.doFilter(get().request, get().response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	public static void setParam(String key, String value) {
		get().params.put(key, value);
	}
	public static String getParam(String key){
        Map<String, String> params2 = get().params;
        String string = params2.get(key);
        if(string == null) {
            return Env.req().getParameter(key);
        }
        return string;
	}
	public static String[] getParamArray(String key){
		return Env.req().getParameterValues(key);
	}
	
    public static Env get() {
        return locals.get();
    }
    
	public static HttpServletRequest req() {
		return get().request;
	}

	public static HttpServletResponse res() {
		return get().response;
	}

	public static ServletContext ctx() {
		return get().request.getServletContext();
	}

	public static void doResult(ActionResult result) {
		if(result != null){
			Env.clearResult();
			result.doResult();
		}
	}

	private static void clearResult() {
		get().actionResult = null;
	}

	public static void doReq(List<Router> routerList) {
		HttpServletRequest r = Env.req();
		String uri = r.getRequestURI();
		String method = r.getMethod();
		get().curRouters = routerList;
		doReq(routerList, method, uri);
	}

	public static void doReq(String method, String uri) {
		doReq(get().curRouters, method, uri);
	}

	public static void doReq(List<Router> routerList, String method, String uri){
		ActionResult ar = null;
		ActionResult ar2 = null;
		for(Router router : routerList){
			//need to handle exception
			try {
				ar = router.resolver().resolve(method, uri);
			} catch (Exception e) {
				e.printStackTrace();
			}
			ar2 = Env.getResult();
			if(ar2 == null){
				if(ar != null){
					Env.setResult(ar);
					break;
				}
			}else{
				break;
			}
		}
		//when no result or usually no router maps this req
//		if(Env.getResult()==null){
//		}
	}

}
