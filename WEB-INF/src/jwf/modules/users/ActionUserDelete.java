package jwf.modules.users;

import java.io.IOException;

import jwf.error.JwfErrorHandler;
import jwf.modules.users.statics.DBUser;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionUserDelete implements IAction{
	DBUser instance = DBUser.getInstance();
	
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
			String log="",pwd="";
			
			//log =  context.getSessionAttribute("login");
			//pwd = context.getSessionAttribute("pwd");
			
			if(log != null){
				System.out.println(log);
				instance.removeUsers(log, pwd);
				context._getResponse().setContentType("text/html");
				context._getResponse().getOutputStream().println("<head>");
				context._getResponse().getOutputStream().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
				context._getResponse().getOutputStream().println("<h1>Index!!!</h1>");
				context._getResponse().getOutputStream().println("</head>");
				context._getResponse().getOutputStream().println("<body>");
				context._getResponse().getOutputStream().println("Déconnecté");
				context._getResponse().getOutputStream().println("<br> <br>");
				context._getResponse().getOutputStream().println("<A HREF=\"http://localhost:8080/jwf/\">Acceuil</A>");				
				context._getResponse().getOutputStream().println("</body>");
				context._getResponse().getOutputStream().println("</html>");
			}						
			else
				JwfErrorHandler.displayError(context._getResponse(), 500,"YOU SHALL NOT PASS!!! 404 DELETE FAILED");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
