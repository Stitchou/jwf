package jwf.modules.users;

import java.io.IOException;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionUserCreate implements IAction{

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

	@Override
	public void proceed(IContext context) {
		try {
            context._getResponse().setContentType("text/html");
			context._getResponse().getOutputStream().println("<head>");
			context._getResponse().getOutputStream().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
			context._getResponse().getOutputStream().println("<h1>Création User !!!</h1>");
			context._getResponse().getOutputStream().println("</head>");
			context._getResponse().getOutputStream().println("<body>");
			context._getResponse().getOutputStream().println("<br> <br>");
			context._getResponse().getOutputStream().println("<form method=\"POST\" enctype=\"multipart/form-data\" action=\"http://localhost:8080/jwf/user/login\">");
			context._getResponse().getOutputStream().println("<label>Pseudo</label> : <input type=\"text\" name=\"login\" />");
			context._getResponse().getOutputStream().println("<label>Mot de passe</label> : <input type=\"password\" name=\"pwd\" /> ");
			context._getResponse().getOutputStream().println("<br> <br>");
			context._getResponse().getOutputStream().println("<input type=\"submit\" value=\"Inscription\" name=\"add\" />");
			context._getResponse().getOutputStream().println("</form>");
			context._getResponse().getOutputStream().println("</body>");
			context._getResponse().getOutputStream().println("</html>");
	    } catch (IOException e) {
	            e.printStackTrace();
	    }
		
	}

}
