package jwf.velocity;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import jwf.error.JwfErrorHandler;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionVelocityTest implements IAction {

	/*
	public static String[] getCredentials() {
		return new String[] { "user" } ;
	}*/

	@SuppressWarnings("deprecation")
	@Override
	public void proceed(IContext context) {
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, context._getRequest().getRealPath("/").replace("\\", "/")+"WEB-INF/template");
        
        ve.init();
		
		VelocityContext vcontext = new VelocityContext();
		List<String> cities = new ArrayList<String>();
		cities.add("paris");
		cities.add("londres");
		cities.add("chicago");
		cities.add("tokyo");

		vcontext.put("name", "Velocityyy");
		vcontext.put("cities", cities);
		
		Template t = null;

		try {
			t = ve.getTemplate("test.vm");
						
			StringWriter sw = new StringWriter();
			t.merge(vcontext, sw);
			
			context._getResponse().getWriter().write(sw.toString());
		} catch(Exception e) {
			JwfErrorHandler.displayError(context, 500, "error while writting response : " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public int setPriority(int priority) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPriority() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addCredential(String role) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean needsCredentials() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasCredential(String[] roles) {
		// TODO Auto-generated method stub
		return false;
	}


}
