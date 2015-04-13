package jwf.router;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jwf.context.Context;

import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.router.interfaces.IRewriteRule;

public class RewriteRule implements IRewriteRule {
	
	private Pattern regex;
	private String className;
	private String[] substitutions;
	private Matcher m;

	public RewriteRule(String regex, String className, String[] substitutions) {
		this.regex =  Pattern.compile(regex);
		this.className = className;
		this.substitutions = substitutions;
	}

	public RewriteRule(String regex, String className) {
		this.regex =  Pattern.compile(regex);
		this.className = className;
		this.substitutions = new String[0];
	}
	
	protected boolean checkContext(IContext context) {
		return true;
	}

	@Override
	public boolean matches(IContext context) {
		m = regex.matcher(context._getRequest().getRequestURI());
		
		try {
			if(m.find()) {
				for(int i = 0; i < substitutions.length && i < m.groupCount();) {
					((Context)context).setParamater(substitutions[i], new String[] { URLDecoder.decode(m.group(++i), "UTF-8").toString() });
				}
				return checkContext(context);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public void rewrite(IContext context) {
		context.setActionClass(className);
	}

}
