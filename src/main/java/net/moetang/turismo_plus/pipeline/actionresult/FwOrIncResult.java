/*
 * Copyright (c) 2013 goodplayer
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package net.moetang.turismo_plus.pipeline.actionresult;

import net.moetang.turismo_plus.pipeline.actionresult.type.InSameEnv;
import net.moetang.turismo_plus.util.Env;

public class FwOrIncResult extends ActionResult implements InSameEnv {
	private String method;
	private String uri;
	
	public FwOrIncResult(String method, String uri) {
		this.method = method;
		this.uri = uri;
	}

	@Override
	public void doResult() {
		Env.doReq(method, uri);
	}

}