/*import java.io.IOException;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

public class LogProcessHandler implements HttpHandler{
  public void handle(HttpExchange he) throws IOException {
   
    //System.out.println("In FormProcessHandler");

    BufferedReader in = new BufferedReader(new InputStreamReader(he.getRequestBody()));

  public User login(String username, String password, Map<String, String> errors) throws IOException {
		if(username.trim().length()==0) {
			errors.put("username", "User name is mandatory");
		}
		
		if(password.trim().length()==0) {
			errors.put("password", "Password is mandatory");
		}
		
		if(errors.size()>0) {
			return null;
		}
		
		UserDAO dao = new UserDAO();
		User user = dao.getUser(username);
		
		if(user==null) {
			errors.put("username", "Invalid username/password");
		}
		else {
			boolean result = BCrypt.checkpw(password, user.getPassword());
			if(result==false) {
				errors.put("username", "Invalid username/password");
			}
		}
		
		if(errors.size()>0) {
			return null;
		}
		
		return user;
	}
 }
}*/


