
public class users {
	
	private String username;
	private String permissions;
	private String password;
	private String control;

	
	public users(String username, String permissions,  String password)
	{
		this.setUsername(username);
		this.setPermissions(permissions);
		this.setControl(username);
		this.setPassword(password);
	}
	public users(String username)
	{
		this.setUsername(username);
		this.setControl(username);
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getControl() {
		return control;
	}

	public void setControl(String control) {
		this.control = control;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username.toUpperCase();
	}
	
	public String getPermissions() {
		return permissions;
	}

	public void setPermissions(String permissions) {

		boolean notValid = false;
		while(!notValid)
		{
			if(permissions.equals("SECURITY OFFICER") || 
					permissions.equals("REGULAR USER") || 
					permissions.equals("ADMINISTRATOR"))
			{
				this.permissions = permissions.toUpperCase();
				notValid = true;
			}
			else
			{
				this.permissions = "NULL";
				System.out.println("Please enter a valid permission");
				break;
			}
		}	
	}
	
	

}
