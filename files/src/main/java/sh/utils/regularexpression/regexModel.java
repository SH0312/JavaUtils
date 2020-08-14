package sh.utils.regularexpression;

import java.util.regex.Pattern;


public class regexModel {
	private final Pattern pattern;

	public regexModel(Pattern pattern) {
		this.pattern = pattern;
	}

	public Pattern getPattern() {
		return pattern;
	}
	
	
	
}
