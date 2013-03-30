package net.moetang.turismo_plus.pipeline.processing;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;

import net.moetang.turismo_plus.util.Env;

public abstract class AsyncAction extends Action implements IAction, AsyncListener, Runnable {
	
	private AsyncContext asyncContext;
	private volatile boolean isComplete = false;
	
	public AsyncAction() {
		super();
	}
	
	public AsyncAction(Filter...filters){
		super(filters);
	}

	@Override
	public final void doAction(Env env) {
		asyncContext = env.req().startAsync(env.req(), env.res());
		asyncContext.addListener(this);
		
		asyncContext.start(this);
	}
	
	@Override
	public final void run() {
		super.doAction(Env.get());
		this.complete();
	}
	
	public final void complete(){
		if(isComplete == true){
			return;
		}
		if(asyncContext != null){
			try {
				asyncContext.complete();
			} catch (Exception e) {
			}
			this.isComplete = true;
		}
	}

	@Override
	public void onComplete(AsyncEvent event) throws IOException {
	}

	@Override
	public void onTimeout(AsyncEvent event) throws IOException {
	}

	@Override
	public void onError(AsyncEvent event) throws IOException {
	}

	@Override
	public void onStartAsync(AsyncEvent event) throws IOException {
	}

}
