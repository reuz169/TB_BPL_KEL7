package tb;

import java.sql.SQLException;

public class User {
	
	private String username;
	private String loginTerakhir;
	private String password;
	private String email;
	
	public User(String username, String loginTerakhir, String email, String password) {
	super();
		
	    this.username = username;
	    this.loginTerakhir = loginTerakhir;
		this.email = email;
		this.password = password;
	}

	User(){
		
	}
	
	public String getusername() {
		return username;
	}
	
	public void setusername(String username) {
		this.username = username;
	}
	
	public String getlogin() {
		return loginTerakhir;
	}
	
	public String getemail() 
	{
		return email;
	}
	
	public void setemail(String email) 
	{
		this.email = email;
	}
	
	public String getpassword() {
		return password;
	}
	
	public void setpassword(String password) 
	{
		this.password = password;
	}
}
