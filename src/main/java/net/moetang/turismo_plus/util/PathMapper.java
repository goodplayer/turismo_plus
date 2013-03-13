package net.moetang.turismo_plus.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.moetang.turismo_plus.pipeline.processing.Action;
import net.moetang.turismo_plus.pipeline.processing.IAction;
import net.moetang.turismo_plus.pipeline.routing.Condition;
import net.moetang.turismo_plus.util.UrlUtils.PathEntry;

public class PathMapper {
	private Map<String, DepthMapper> depth = new HashMap<>();

	public void add(final String method, final List<PathEntry> paths, final Condition[] conditions, final Action action) {
		if(paths.size() == 0)
			return;
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
		if(paths.size() == 0)
			return null;
		DepthMapper dm = depth.get(method);
		return ((dm==null)?null:dm.getAction(paths));
	}

	private class DepthMapper {
		// initial value : 9 depths = 1-9 avail  0 ommit
		private SearchTable[] table = new SearchTable[10];
		public void add(final List<PathEntry> paths, final Condition[] conditions, final Action action){
			int length = paths.size();
			if(table.length <= length){
				SearchTable[] tmp = new SearchTable[length+1];
				System.arraycopy(table, 0, tmp, 0, table.length);
				table = tmp;
			}
			SearchTable st = table[length];
			if(st == null){
				st = new SearchTable();
				table[length] = st;
			}
			st.add(paths, conditions, action);
		}
		public IAction getAction(final List<PathEntry> paths){
			int length = paths.size();
			if(length > table.length){
				return null;
			}
			SearchTable st = table[length];
			return st.getAction(paths);
		}
	}
	private class SearchTable {
		private final TN head = new TN();
		public void add(final List<PathEntry> paths, final Condition[] conditions, final Action action){
			Node n = new Node(paths, conditions, action);
			Iterator<PathEntry> iter = n.getIterator();
			TN parent = head;
			while(iter.hasNext()){
				PathEntry pe = iter.next();
				TN t = new TN();
				t.pe = pe;
				parent = this.addToArray(parent, t);
			}
			parent.node = n;
		}
		public IAction getAction(final List<PathEntry> paths){
			// when matches (path) -> (conditions), (resolve req uri) -> (return action)
			int length = paths.size();
			TN parent = head;
			for(int i = 0; i < length; i++){
				TN[] childNodes = parent.next;
				PathEntry toTest = paths.get(i);
				boolean match = false;
				for(TN n : childNodes){
					if(n.pe.match(toTest)){
						parent = n;
						match = true;
						break;
					}
				}
				if(!match){
					return null;
				}
			}
			Node node = parent.node;
			return node.getAction(paths);
		}
		//return curNode
		private TN addToArray(TN parentNode, TN curNode){
			TN[] nodes = parentNode.next;
			if(nodes == null){
				this.incArray(parentNode);
				nodes = parentNode.next;
				nodes[0] = curNode;
			}else{//with order
				PathEntry curEntry = curNode.pe;
				//the same node
				for(TN i : nodes){
					PathEntry pe = i.pe;
					if(pe.equals(curEntry)){
						return i;
					}
				}
				//no same node, then.....
				this.incArray(parentNode);
				nodes = parentNode.next;
				nodes[nodes.length-1] = curNode;
				Arrays.sort(nodes);
			}
			return curNode;
		}
		private void incArray(TN node){
			if(node.next == null){
				node.next = new TN[1];
			}else{
				TN[] tmp = new TN[node.next.length+1];
				System.arraycopy(node.next, 0, tmp, 0, node.next.length);
				node.next = tmp;
			}
		}
		private class TN implements Comparable<TN>{
			public PathEntry pe;
			public TN[] next;
			public Node node;
			@Override
			public int compareTo(TN o) {
				return this.pe.compareTo(o.pe);
			}
		}
	}
	private class Node {
		private List<PathEntry> paths;
		private Condition[] conditions;
		private IAction action;
		//resolve req uri when node matches
		private List<EntryResolver> resolvers = new ArrayList<EntryResolver>();

		public Node(List<PathEntry> paths, Condition[] conditions, IAction action) {
			this.paths = paths;
			this.conditions = conditions;
			this.action = action;
			for(PathEntry pe : paths){
				resolvers.add(pe.getPathEntryResolver());
			}
		}
		public IAction getAction(final List<PathEntry> paths){
			Env env = Env.get();
			for(Condition c : conditions){
				if(!c.test(env)){
					return null;
				}
			}
			for(int i = 0; i < resolvers.size(); i++){
				resolvers.get(i).resolve(paths.get(i));
			}
			return action;
		}
		public Iterator<PathEntry> getIterator(){
			return new Iterator<UrlUtils.PathEntry>() {
				private int i = 0;
				@Override
				public void remove() {
				}
				@Override
				public PathEntry next() {
					return paths.get(i++);
				}
				@Override
				public boolean hasNext() {
					return ((i < paths.size())?true:false);
				}
			};
		}
	}
}
