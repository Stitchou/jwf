package jwf.modules.users.statics;

import java.util.ArrayList;
import java.util.Iterator;

public class DBUser {
	
	public ArrayList<User> listUser = new ArrayList<User>();
	private boolean isUser;
	private boolean isResearched;
	private User alkaida;
	
	private DBUser()
	{
		isUser = false;
		alkaida = null;
		isResearched = false;
		listUser.add(new User("admin", "admin", new String[] {"admin", "user"}));
		listUser.add(new User("reda", "reda", new String[] {"user"}));
		listUser.add(new User("vuzi", "vuzi", new String[] {"user"}));		
	}
	public boolean isResearched() {
		return isResearched;
	}

	public void setResearched(boolean isResearched) {
		this.isResearched = isResearched;
	}

	public ArrayList<User> getListUser() {
		return listUser;
	}

	public void setListUser(ArrayList<User> listUser) {
		this.listUser = listUser;
	}

	public boolean isUser() {
		return isUser;
	}

	public void setUser(boolean isUser) {
		this.isUser = isUser;
	}

	public User getAlkaida() {
		return alkaida;
	}

	public void setAlkaida(User alkaida) {
		this.alkaida = alkaida;
	}

	private static DBUser INSTANCE;
 
	public static DBUser getInstance()
	{	
		if(INSTANCE == null){
			INSTANCE = new DBUser();
		}
		
		return INSTANCE;
	}
	
	public boolean addUsers(String login,String password){
		
		for(User tmp : listUser){
			if(tmp.getLogin().equals(login)){	
				return false;
			}
		}
		listUser.add(new User(login,password,new String[]{"user"}));
		return true;
	}
	
	public void removeUsers(String login,String password){
		Iterator<User> iter = listUser.iterator();

		while (iter.hasNext()) {
		    User str = iter.next();

		    if (str.getLogin().equals(login) && str.getPassword().equals(password)){
		        iter.remove();
		    }
		}
	}
	
	public void searchUser(String login){
		isUser = false;
		isResearched = true;
		for(User tmp : listUser){
			if(tmp.getLogin().equals(login)){
				isUser = true;
				alkaida = tmp;
				return;
			}
		}
		
	}
}
