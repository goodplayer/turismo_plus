package net.moetang.turismo_plus.goal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.moetang.turismo_plus.pipeline.action.ContineResult;
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
				Env.setResult(new ContineResult());
			}
		});
		_prefix("/");
		_default(new Action() {
			@Override
			public void action() {
				// TODO Auto-generated method stub
			}
		});
		
		_before(new BeforeAll(){
			@Override
			public boolean doBefore(HttpServletRequest request,
					HttpServletResponse response) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		_after(new AfterAll() {
			@Override
			public boolean doAfter(HttpServletRequest request,
					HttpServletResponse response) {
				// TODO Auto-generated method stub
				return false;
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
							public void doFilter(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) {
								// TODO Auto-generated method stub
								
							}
						}, new Filter() {
							@Override
							public void doFilter(HttpServletRequest request,
									HttpServletResponse response,
									FilterChain filterChain) {
								// TODO Auto-generated method stub
								
							}
						}) {
					@Override
					public void action() {
						// TODO Auto-generated method stub
						
					}
				});

		get("/lala", "/nihao");
	}
}
