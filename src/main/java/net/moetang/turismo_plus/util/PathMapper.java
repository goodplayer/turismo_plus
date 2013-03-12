package net.moetang.turismo_plus.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.moetang.turismo_plus.pipeline.processing.Action;
import net.moetang.turismo_plus.pipeline.processing.IAction;
import net.moetang.turismo_plus.pipeline.routing.Condition;
import net.moetang.turismo_plus.util.UrlUtils.PathEntry;

public class PathMapper {
	private Map<String, DepthMapper> depth = new HashMap<>();

	public void add(final String method, final List<PathEntry> paths, final Condition[] conditions, final Action action) {
		DepthMapper dm = depth.get(method);
		if(dm == null){
			dm = new DepthMapper();
			depth.put(method, dm);
		}
		dm.add(paths, conditions, action);
	}
	public IAction getAction(String method, final List<PathEntry> paths) {
		// 1st : method
		// 2nd : path depth levels
		// 3rd : path
		// 4nd : conditions
		DepthMapper dm = depth.get(method);
		return ((dm==null)?null:dm.getAction(paths));
	}

	private class DepthMapper {
		// initial value : 10 depths
		private SearchTable[] table = new SearchTable[10];
		public void add(final List<PathEntry> paths, final Condition[] conditions, final Action action){
			int length = paths.size();
			if(table.length < length){
				SearchTable[] tmp = new SearchTable[length];
				System.arraycopy(table, 0, tmp, 0, table.length);
				table = tmp;
			}
			SearchTable st = table[length-1];
			if(st == null){
				st = new SearchTable();
				table[length-1] = st;
			}
			st.add(paths, conditions, action);
		}
		public IAction getAction(final List<PathEntry> paths){
			int length = paths.size();
			if(length > table.length){
				return null;
			}
			SearchTable st = table[length-1];
			return st.getAction(paths);
		}
	}
	private class SearchTable {
		public void add(final List<PathEntry> paths, final Condition[] conditions, final Action action){
			//TODO
		}
		public IAction getAction(final List<PathEntry> paths){
			//TODO
			return null;
		}
	}
	private class Node {
		//TODO
		private List<PathEntry> paths;
		private Condition[] conditions;
		private IAction action;
		private List<EntryResolver> resolvers = new ArrayList<EntryResolver>();

		public Node(List<PathEntry> paths, Condition[] conditions, IAction action) {
			this.paths = paths;
			this.conditions = conditions;
			this.action = action;
			for(PathEntry pe : paths){
				resolvers.add(pe.getPathEntryResolver());
			}
		}
	}
}