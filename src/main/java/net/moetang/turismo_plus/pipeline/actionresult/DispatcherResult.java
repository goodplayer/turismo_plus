package net.moetang.turismo_plus.pipeline.actionresult;

import java.io.IOException;

import javax.servlet.ServletException;

import net.moetang.turismo_plus.pipeline.actionresult.type.InNewEnv;
import net.moetang.turismo_plus.util.Env;

public class DispatcherResult extends ActionResult implements InNewEnv {
	private String path;
	public DispatcherResult(String path) {
		this.path = path;
	}

	@Override
	public void doResult() {
		try {
			Env.req().getRequestDispatcher(path).forward(Env.req(), Env.res());
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}
