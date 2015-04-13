package jwf.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JPasswordField;

import jwf.context.Context;
import jwf.router.Dispatcher;
import jwf.router.RewriteRule;
import jwf.router.Rewriter;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;
import org.codehaus.jackson.map.JsonMappingException;
import org.esgi.web.framework.context.interfaces.IContext;
import org.esgi.web.framework.core.interfaces.IFrontController;


public class FrontController extends HttpServlet implements IFrontController {

	private static final long serialVersionUID = 1L;
	
	public static String URIroot = "/jwf";
	
	private Rewriter rewriter;
	private Dispatcher dispatcher;
	private Context c;
	
	public void init() {
		rewriter = new Rewriter();
		dispatcher = new Dispatcher();
		
		 
			 
			  
		
		rewriter.addRule(new RewriteRule(URIroot + "/explore/(.*)", "jwf.modules.statics.ActionUploadFile", new String[] { "path" }) {
			
			@Override
			protected boolean checkContext(IContext context) {
				if(context.getUploadedFiles().length > 0) {
					String[] param = (String[]) context.getParameter("path");
					
					if(param != null && param.length > 0) {
						File f = new File(Context.root.getPath() + param[0]);
						
						return f.exists() && f.isDirectory();
					}
				}
				
				return false;
			}
			
		});
		
		rewriter.addRule(new RewriteRule(URIroot + "/explore/(.*)", "jwf.modules.statics.ActionDisplayFolder", new String[] { "path" }) {
			
			@Override
			protected boolean checkContext(IContext context) {
				String[] param = (String[]) context.getParameter("path");
				
				if(param != null && param.length > 0) {
					File f = new File(Context.root.getPath() + param[0]);
					
					return f.exists() && f.isDirectory();
				}
				
				return false;
			}
			
		});
		
		rewriter.addRule(new RewriteRule(URIroot + "/explore/(.*)", "jwf.modules.statics.ActionDownloadFile", new String[] { "path" }) {
			
			@Override
			protected boolean checkContext(IContext context) {
				String[] param = (String[]) context.getParameter("path");
				
				if(param != null && param.length > 0) {
					System.out.println(param[0]);
					
					File f = new File(Context.root.getPath() + param[0]);
					System.out.println(f.getAbsolutePath());
					
					return f.exists() && f.isFile();
				}
				return false;
			}
			
		});		
		
		rewriter.addRule(new RewriteRule(URIroot + "/user/logout", "jwf.modules.users.ActionUserLogout"));
		rewriter.addRule(new RewriteRule(URIroot + "/user/login", "jwf.modules.users.ActionUserLogin"));
		rewriter.addRule(new RewriteRule(URIroot + "/user/delete", "jwf.modules.users.ActionUserDelete"));
		rewriter.addRule(new RewriteRule(URIroot + "/user/search", "jwf.modules.users.ActionUserSearch"));
		rewriter.addRule(new RewriteRule(URIroot + "/user/list", "jwf.modules.users.ActionUserList"));
		rewriter.addRule(new RewriteRule(URIroot + "/add", "jwf.modules.users.ActionUserLogin"));
		rewriter.addRule(new RewriteRule(URIroot + "/index", "jwf.modules.ActionIndex"));
		rewriter.addRule(new RewriteRule(URIroot + "/create", "jwf.modules.users.ActionUserCreate"));
		rewriter.addRule(new RewriteRule(URIroot + "/velocity", "jwf.velocity.ActionVelocityTest"));
		rewriter.addRule(new RewriteRule(URIroot, "jwf.modules.ActionHelloWorld"));

	}

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) {
		handle(request, response);
	}
	
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response) {

	    try {
			c = new Context(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rewriter.rewrite(c);
		
		/*try {
			int lvl=0,i=1;
			JsonFactory jfactory = new JsonFactory();
		 
			/*** read from file **

			JsonParser jParser = jfactory.createJsonParser(new File(c._getRequest().getRealPath("/").replace("\\", "/")+"WEB-INF/template/config.json"));
			//jParser.nextToken();
			// loop until token equal to "}"
			
			while (lvl > -1) {		 

				String fieldname = jParser.getText() ;
					System.out.println(fieldname+ " priorite "+(i-lvl));
				if(jParser.getCurrentToken() == JsonToken.START_OBJECT){
					lvl ++;
				}
				else if(jParser.getCurrentToken() == JsonToken.END_OBJECT){
					lvl--;
					i++;
				}

				jParser.nextToken();
				fieldname = jParser.getText() ;
					System.out.println(fieldname+ " priorite "+(i-lvl));
				jParser.nextToken();
			  }
			String fieldname = jParser.getText();
			System.out.println(fieldname);
			jParser.nextToken();
			System.out.println(jParser.getText());
			jParser.close();
		 
		     } catch (JsonGenerationException e) {
		 
			  e.printStackTrace();
		 
		     } catch (JsonMappingException e) {
		 
			  e.printStackTrace();
		 
		     } catch (IOException e) {
		 
			  e.printStackTrace();
		 
		     }*/
		dispatcher.dispatch(c);
					
					
	}
	
	
	
}
