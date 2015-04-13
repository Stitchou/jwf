package jwf.modules.users.statics;

public class User {
	
	private String login;
	private String password;
	private String [] droits;
	
	
	public User(String login, String password, String[] droits) {
		super();
		this.login = login;
		this.password = password;
		this.droits = droits;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String[] getDroits() {
		return droits;
	}
	public void setDroits(String[] droits) {
		this.droits = droits;
	}
}
