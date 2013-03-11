package net.moetang.turismo_plus.pipeline.processing;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.moetang.turismo_plus.util.Env;


public abstract class Action implements IAction {
	protected Filter[] filters;
	public Action() {
		this.filters = new Filter[0];
	}
	public Action(Filter...filters){
		this.filters = filters;
	}
	public abstract void action();

	@Override
	public void doAction() {
		//TODO
		action();
	}

    protected String params(String key) {
    	//TODO
        return Env.params(key);
    }

    protected HttpServletRequest req() {
        return Env.req();
    }

    protected HttpServletResponse res() {
        return Env.res();
    }

    protected ServletContext ctx() {
        return Env.ctx();
    }

    protected void alias(String target) {
    	//TODO
        forward(target);
    }

    protected void forward(String target) {
    	//TODO
        new Alias().forward(target);
    }
    
    protected void jsp(String path) {
    	//TODO
        forward(path);
    }

    protected void movedPermanently(String newLocation) {
    	//TODO
        new MovedPermanently().send301(newLocation);
    }

    protected void movedTemporarily(String newLocation) {
    	//TODO
        new MovedTemporarily().send302(newLocation);
    }

    protected void notFound() {
    	//TODO
        new NotFound().send404();
    }

    protected void redirect(String newLocation) {
    	//TODO
        new Redirect().redirect(newLocation);
    }

    protected void print(String string) {
    	//TODO
        new StringPrinter().print(string);
    }

}
