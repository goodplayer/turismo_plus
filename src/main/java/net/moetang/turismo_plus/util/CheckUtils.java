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
package net.moetang.turismo_plus.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.moetang.turismo_plus.pipeline.routing.Condition;

public final class CheckUtils {
	public static Condition[] check(final Condition... filterList){
		return filterList;
	}

	public static Condition hRegEx(final String header, final String regex){
		return new Condition() {
			private Pattern p = Pattern.compile(regex);
			private String _header = header;
			@Override
			public boolean test(Env env) {
				String value = env.req().getHeader(_header);
				if(value == null)
					return false;
				Matcher m = p.matcher(value);
				return m.matches();
			}
		};
	}
	
	public static Condition hContains(final String header, final String content){
		return new Condition() {
			@Override
			public boolean test(Env env) {
				String value = env.req().getHeader(header);
				if(value == null)
					return false;
				return value.contains(content);
			}
		};
	}
}
