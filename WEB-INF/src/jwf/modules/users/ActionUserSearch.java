package jwf.modules.users;

import java.io.IOException;

import jwf.modules.users.statics.DBUser;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionUserSearch implements IAction{

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
		
		DBUser instance = DBUser.getInstance();
		
		try {
			String log="";
			
			String [] arrayLogin = (String[]) context.getParameter("searchLogin");
			if(arrayLogin != null){
				for(String s : arrayLogin)
					log = s;			
			}
			if(!log.isEmpty()){
				//instance.setResearched(true);
				instance.searchUser(log);
			}	
			else
				instance.setResearched(false);
			
			context._getResponse().setContentType("text/html");
			context._getResponse().getOutputStream().println("<head>");
			context._getResponse().getOutputStream().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
			context._getResponse().getOutputStream().println("<h1>Recherche!!!</h1>");
			context._getResponse().getOutputStream().println("</head>");
			context._getResponse().getOutputStream().println("<body>");
			context._getResponse().getOutputStream().println("<br> <br>");
			context._getResponse().getOutputStream().println("<form method=\"POST\" enctype=\"multipart/form-data\" action=\"http://localhost:8080/jwf/user/search/\">");
			context._getResponse().getOutputStream().println("<label>Pseudo</label> : <input type=\"text\" name=\"searchLogin\" />");			context._getResponse().getOutputStream().println("<br> <br>");
			context._getResponse().getOutputStream().println("<input type=\"submit\" value=\"Envoyer\" />");
			context._getResponse().getOutputStream().println("</form>");
			context._getResponse().getOutputStream().println("<br> <br>");
			context._getResponse().getOutputStream().println("<br> <br>");
			if(instance.isResearched())
			{
				if(instance.isUser()){
					context._getResponse().getOutputStream().println("User :"+instance.getAlkaida().getLogin() + " Pass: "+instance.getAlkaida().getPassword());
					String [] array = instance.getAlkaida().getDroits();
					context._getResponse().getOutputStream().println(" Level: ");
					for(String s : array)
						context._getResponse().getOutputStream().println(" \""+s+"\"");
				}
				else
					context._getResponse().getOutputStream().println("BOUGNOULE INEXISTANT!!! DEJA PARTI EN MISSION");
				
				instance.setResearched(false);
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
