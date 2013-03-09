package net.moetang.turismo_plus.util;

import javax.servlet.http.HttpServletRequest;

import net.moetang.turismo_plus.util.UrlUtils.PathEntry;

public interface EntryResolver {
	public void resolve(PathEntry entryToResolve, HttpServletRequest req);
}
