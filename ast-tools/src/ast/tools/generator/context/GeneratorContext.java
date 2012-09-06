package ast.tools.generator.context;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.text.StrSubstitutor;

public class GeneratorContext {
	private static Map<String, String> tokens = new HashMap<String, String>();

	public static void putToken(String key, String value) {
		GeneratorContext.tokens.put(key, value);
	}

	public static void clearTokens() {
		GeneratorContext.tokens.clear();
	}

	public static String replaceTokens(String source) {
		StrSubstitutor substitutor = new StrSubstitutor(tokens);
		return substitutor.replace(source);
	}
}
