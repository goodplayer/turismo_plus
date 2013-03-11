package net.moetang.turismo_plus.router;

import net.moetang.turismo_plus.pipeline.action.ActionResult;
import net.moetang.turismo_plus.pipeline.action.ContineResult;
import net.moetang.turismo_plus.pipeline.processing.Action;
import net.moetang.turismo_plus.pipeline.processing.AfterAll;
import net.moetang.turismo_plus.pipeline.processing.BeforeAll;
import net.moetang.turismo_plus.pipeline.processing.Resolver;
import net.moetang.turismo_plus.pipeline.routing.Condition;
import net.moetang.turismo_plus.util.Env;

public abstract class AbstractRouter implements Router {
	public AbstractRouter() {
		map();
	}

	@Override
	public Resolver resolver() {
		// TODO Auto-generated method stub
		return null;
	}
	
	protected abstract void map();
	
	private String g_prefix;

	//map all url with 'prefix' uri
	//no wildcard or regex support
	//should start with '/'
	protected void _prefix(final String prefix){
		this.g_prefix = prefix;
	}
	protected void _before(final BeforeAll before){
		//TODO
	}
	protected void _after(final AfterAll after){
		//TODO
	}
	protected void _default(final Action action){
		//TODO
	}
	protected void _exclude(final String pathRegEx, final Action action) {
		//TODO
	}
	private Action continueAction = new Action() {
		private ActionResult continueResult = new ContineResult();
		@Override
		public void action() {
			Env.setResult(continueResult);
		}
	};
	/**
	 * push request to next one to handle
	 * @param pathRegEx
	 */
	protected void _exclude(final String pathRegEx) {
		_exclude(pathRegEx, continueAction);
	}

    // Route-alias shortcut methods
	protected void post(final String fromPath, final String targetPath) {
		//TODO
    }
	protected void get(final String fromPath, final String targetPath) {
		//TODO
    }
	protected void put(final String fromPath, final String targetPath) {
		//TODO
    }

    // Shortcuts methods
	protected void get(final String path, final Action action) {
		get(path, null, action);
    }
	protected void post(final String path, final Action action) {
		post(path, null, action);
    }
	protected void put(final String path, final Action action) {
		put(path, null, action);
    }
	protected void head(final String path, final Action action) {
		head(path, null, action);
    }
	protected void options(final String path, final Action action) {
		options(path, null, action);
    }
	protected void delete(final String path, final Action action) {
		delete(path, null, action);
    }
	protected void trace(final String path, final Action action) {
		trace(path, null, action);
    }
	protected void custom(final String method, final String path, final Action action){
		custom(method, path, null, action);
	}
	protected void custom(final String method, final String path, final Condition[] filters, final Action action) {
		//TODO
    }
	protected void get(final String path, final Condition[] filters, final Action action) {
		//TODO
    }
	protected void post(final String path, final Condition[] filters, final Action action) {
		//TODO
    }
	protected void put(final String path, final Condition[] filters, final Action action) {
		//TODO
    }
	protected void head(final String path, final Condition[] filters, final Action action) {
		//TODO
    }
	protected void options(final String path, final Condition[] filters, final Action action) {
		//TODO
    }
	protected void delete(final String path, final Condition[] filters, final Action action) {
		//TODO
    }
	protected void trace(final String path, final Condition[] filters, final Action action) {
		//TODO
    }

    protected static final String POST = "POST";
    protected static final String GET = "GET";
    protected static final String HEAD = "HEAD";
    protected static final String OPTIONS = "OPTIONS";
    protected static final String PUT = "PUT";
    protected static final String DELETE = "DELETE";
    protected static final String TRACE = "TRACE";
    
    
}
