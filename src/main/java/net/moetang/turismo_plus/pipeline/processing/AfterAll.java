package net.moetang.turismo_plus.pipeline.processing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AfterAll {
	/**
	 * @param request
	 * @param response
	 * @return is ready to next 'after'
	 */
	public boolean doAfter(HttpServletRequest request, HttpServletResponse response);
}
