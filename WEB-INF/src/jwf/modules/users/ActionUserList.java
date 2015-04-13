package jwf.modules.users;

import java.io.IOException;
import java.util.ArrayList;

import jwf.modules.users.statics.DBUser;
import jwf.modules.users.statics.User;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionUserList implements IAction{

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

			DBUser instance = DBUser.getInstance();
			context._getResponse().setContentType("text/html");
			context._getResponse().getOutputStream().println("<head>");
			context._getResponse().getOutputStream().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
			context._getResponse().getOutputStream().println("<h1>Recherche!!!</h1>");
			context._getResponse().getOutputStream().println("</head>");
			context._getResponse().getOutputStream().println("<body>");
			context._getResponse().getOutputStream().println("<br> <br>");
			context._getResponse().getOutputStream().println("<br> <br>");
			context._getResponse().getOutputStream().println("<br> <br>");
			ArrayList<User> lUser = instance.getListUser();
			int i = 1;
			context._getResponse().getOutputStream().println(" LISTE DE BOUGNOULES TOUJOURS VIVANT <br>");
			for(User u : lUser)
			{				
				context._getResponse().getOutputStream().println(" <br>");
				context._getResponse().getOutputStream().println(" BOUGNOULES "+i);
				context._getResponse().getOutputStream().println("    User :"+u.getLogin() + "    Pass: "+u.getPassword());
				String [] array = u.getDroits();
				context._getResponse().getOutputStream().println("    Level: ");
				for(String s : array)
					context._getResponse().getOutputStream().println(" \""+s+"\"");
				i++;
			}
			context._getResponse().getOutputStream().println("<br> <br>");
			context._getResponse().getOutputStream().println("<A HREF=\"http://localhost:8080/jwf/index\">index</A>");				
			context._getResponse().getOutputStream().println("</body>");
			context._getResponse().getOutputStream().println("</html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
