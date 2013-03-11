package net.moetang.turismo_plus.pipeline.routing;

import javax.servlet.http.HttpServletRequest;

public interface Condition {
	public boolean test(HttpServletRequest request);
}
