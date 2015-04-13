package jwf.modules;

import java.io.IOException;

import jwf.error.JwfErrorHandler;
import jwf.modules.users.statics.DBUser;
import jwf.modules.users.statics.User;

import org.esgi.web.framework.action.interfaces.IAction;
import org.esgi.web.framework.context.interfaces.IContext;

public class ActionIndex implements IAction{

	private User connecte;
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
			if(TestLogin(context)){
				context._getResponse().setContentType("text/html");
				context._getResponse().getOutputStream().println("<head>");
				context._getResponse().getOutputStream().println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">");
				context._getResponse().getOutputStream().println("<h1>Index!!!</h1>");
				context._getResponse().getOutputStream().println("</head>");
				context._getResponse().getOutputStream().println("<body>");
				context._getResponse().getOutputStream().println("hello");
				context._getResponse().getOutputStream().println("<br> <br>");
				context._getResponse().getOutputStream().println("<A HREF=\"http://localhost:8080/jwf/user/logout\">Déconnecter</A>");
				context._getResponse().getOutputStream().println("<br> <br>");
				context._getResponse().getOutputStream().println("<A HREF=\"http://localhost:8080/jwf/user/search\">Rechercher</A>");
				context._getResponse().getOutputStream().println("<br> <br>");
				context._getResponse().getOutputStream().println("<A HREF=\"http://localhost:8080/jwf/user/list\">Lister</A>");
				context._getResponse().getOutputStream().println("<br> <br>");
				context._getResponse().getOutputStream().println("<A HREF=\"http://localhost:8080/jwf/user/delete\">Supprimer</A>");				
				context._getResponse().getOutputStream().println("</body>");
				context._getResponse().getOutputStream().println("</html>");
			}	
			else
				JwfErrorHandler.displayError(context._getResponse(), 500,"Login or Password incorrect");	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	private boolean TestLogin(IContext context){
		if(context.getParameterKeys().length >0)
		{
			if(context.getParameter("login") != null)
			{
				DBUser instance = DBUser.getInstance();
				String [] arrayLogin = (String[]) context.getParameter("login");
				String [] arrayPassword =(String[]) context.getParameter("pwd");
				
				for(User us : instance.listUser)
				{
					for(String s : arrayLogin)
						if(us.getLogin().equals(s))
							for(String p : arrayPassword)
								if(us.getPassword().equals(p)){
									connecte = new User(s, p, us.getDroits());
									context.setSessionAttribute("login", connecte.getLogin());
									context.setSessionAttribute("pwd", connecte.getPassword());
									return true;
								}
				}
			}
		}
		else 
			return true;
		
		return false;
	}

}
