package net.moetang.turismo_plus.goal;

import net.moetang.turismo_plus.util.UrlUtils;

import com.ghosthack.turismo.action.Action;
import com.ghosthack.turismo.routes.RoutesList;

/**
 * @author goodplayer
 * 
 */
public class WebAppRouters extends RoutesList {

	@Override
	protected void map() {
		_before(new Action() {
			@Override
			public void run() {
			}
		});

		_after(new Action() {
			@Override
			public void run() {
			}
		});

		get("/", new Action() {
			public void run() {
				print("Hello World!");
			}
		});
		
		post("/",
				check(hRegEx("header key", "header value"),
						hContain("header key", "header value")), 
				new Action(
						new ActionFilter() {

						}, new ActionFilter() {

						}) {
					@Override
					public void run() {

					}
				});
	}
}
