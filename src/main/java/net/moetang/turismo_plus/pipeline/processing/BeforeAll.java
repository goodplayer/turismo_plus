package net.moetang.turismo_plus.pipeline.processing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BeforeAll {
	/**
	 * @param request
	 * @param response
	 * @return is ready to next 'before'
	 */
	public boolean doBefore(HttpServletRequest request, HttpServletResponse response);
}
