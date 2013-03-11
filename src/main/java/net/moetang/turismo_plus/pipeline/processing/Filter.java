package net.moetang.turismo_plus.pipeline.processing;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.moetang.turismo_plus.util.FilterChain;

public interface Filter {
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain);
}
