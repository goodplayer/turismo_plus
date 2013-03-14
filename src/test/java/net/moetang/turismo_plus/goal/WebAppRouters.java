package net.moetang.turismo_plus.goal;

import net.moetang.turismo_plus.pipeline.actionresult.ContinueResult;
import net.moetang.turismo_plus.pipeline.processing.Action;
import net.moetang.turismo_plus.pipeline.processing.AfterAll;
import net.moetang.turismo_plus.pipeline.processing.BeforeAll;
import net.moetang.turismo_plus.pipeline.processing.Filter;
import net.moetang.turismo_plus.pipeline.router.SimpleRouter;
import net.moetang.turismo_plus.util.AfterChain;
import net.moetang.turismo_plus.util.BeforeChain;
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
		_prefix("/");
		_exclude("\\S+\\.jsp$", new Action() {
			@Override
			public void action(Env env) {
				env.setResult(new ContinueResult());
			}
		});
		_default(new Action() {
			@Override
			public void action(Env env) {
			}
		});
		
		_before(new BeforeAll(){
			@Override
			public void doBefore(Env env, BeforeChain beforeChain) {
				
			}
		});

		_after(new AfterAll() {
			@Override
			public void doAfter(Env env, AfterChain afterChain) {
			}
		});

		get("/", new Action() {
			@Override
			public void action(Env env) {
				print("Hello World!");
			}
		});

		post("/",
				check(hRegEx("header key", "header value"),
						hContains("header key", "header value")), 
				new Action(
						new Filter() {
							@Override
							public void doFilter(Env env, FilterChain filterChain) {
								filterChain.doNext(env);
							}
						},
						new Filter() {
							@Override
							public void doFilter(Env env, FilterChain filterChain) {
								filterChain.doNext(env);
							}
						}) {
					@Override
					public void action(Env env) {
						
					}
				});

		get("/lala", "/nihao");
	}
}
