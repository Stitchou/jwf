package jwf.error;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.esgi.web.framework.context.interfaces.IContext;

public class JwfErrorHandler {

	public static void displayError(IContext context, int errorNumber, String message) {
		displayError(context._getResponse(), errorNumber, message);
	}
		
	public static void displayError(HttpServletResponse response, int errorNumber, String message) {
		try {
			PrintWriter out = response.getWriter( );

			response.setContentType("text/html");
			response.setStatus(errorNumber);
			out.print("Error : " + message);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
