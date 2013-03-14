package net.moetang.turismo_plus.goal;

import net.moetang.turismo_plus.pipeline.actionresult.ContinueResult;
import net.moetang.turismo_plus.pipeline.processing.Action;
import net.moetang.turismo_plus.pipeline.processing.Filter;
import net.moetang.turismo_plus.pipeline.router.FastMapRouter;
import net.moetang.turismo_plus.util.Env;
import net.moetang.turismo_plus.util.FilterChain;


/**
 * @author goodplayer
 * 
 */
public class FastWebAppRouters extends FastMapRouter {

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

		get("/", new Action() {
			@Override
			public void action(Env env) {
				print("Hello World!");
			}
		});

		post("/",
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
