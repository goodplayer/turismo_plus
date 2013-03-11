package net.moetang.turismo_plus.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
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
			public boolean test(HttpServletRequest request) {
				String value = request.getHeader(_header);
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
			public boolean test(HttpServletRequest request) {
				String value = request.getHeader(header);
				if(value == null)
					return false;
				return value.contains(content);
			}
		};
	}
}
