package net.moetang.turismo_plus.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.moetang.turismo_plus.pipeline.action.ActionResult;

public class Env {
    private static ThreadLocal<Env> locals = new ThreadLocal<Env>();
    
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain filterChain;
    private ActionResult actionResult;
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
		return locals.get().actionResult;
	}
	
	public static void setResult(ActionResult actionResult){
		locals.get().actionResult = actionResult;
	}
	
	public static void doFilterChain(){
		try {
			locals.get().filterChain.doFilter(locals.get().request, locals.get().response);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

	public static void setParam(String key, String value) {
		locals.get().params.put(key, value);
	}

	public static HttpServletRequest req() {
		return locals.get().request;
	}

	public static HttpServletResponse res() {
		return locals.get().response;
	}

	public static ServletContext ctx() {
		return locals.get().request.getServletContext();
	}

	public static void doResult(ActionResult result) {
		if(result != null)
			result.doResult();
	}

	//查找表
	//request暂存
	
}
