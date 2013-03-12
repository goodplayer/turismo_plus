package net.moetang.turismo_plus.goal;

import net.moetang.turismo_plus.pipeline.actionresult.ContinueResult;
import net.moetang.turismo_plus.pipeline.processing.Action;
import net.moetang.turismo_plus.pipeline.processing.AfterAll;
import net.moetang.turismo_plus.pipeline.processing.BeforeAll;
import net.moetang.turismo_plus.pipeline.processing.Filter;
import net.moetang.turismo_plus.router.SimpleRouter;
import net.moetang.turismo_plus.util.Env;
import net.moetang.turismo_plus.util.FilterChain;

import static net.moetang.turismo_plus.util.CheckUtils.*;


/**
 * @author goodplayer
 * 
 */
public class WebAppRouters extends SimpleRouter {

	@Override
	protected void map() {
		_exclude("\\S+\\.jsp$", new Action() {
			@Override
			public void action() {
				Env.setResult(new ContinueResult());
			}
		});
		_prefix("/");
		_default(new Action() {
			@Override
			public void action() {
			}
		});
		
		_before(new BeforeAll(){
			@Override
			public NEXT_OP_BEFORE doBefore() {
				// TODO Auto-generated method stub
				return null;
			}
		});

		_after(new AfterAll() {
			@Override
			public NEXT_OP_AFTER doAfter() {
				// TODO Auto-generated method stub
				return null;
			}
		});

		get("/", new Action() {
			@Override
			public void action() {
				print("Hello World!");
			}
		});

		post("/",
				check(hRegEx("header key", "header value"),
						hContains("header key", "header value")), 
				new Action(
						new Filter() {
							@Override
							public void doFilter(FilterChain filterChain) {
								
							}
						}, new Filter() {
							@Override
							public void doFilter(FilterChain filterChain) {
								
							}
						}) {
					@Override
					public void action() {
						
					}
				});

		get("/lala", "/nihao");
	}
}
