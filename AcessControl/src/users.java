
public class users {
	
	private String username;
	private String permissions;
	
	private String owner;
	private String control;

	public users(String username, String permissions, String owner)
	{
		this.setUsername(username);
		this.setPermissions(permissions);
		this.setOwner(owner);
		this.setControl(username);
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

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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
				System.out.println("Please enter a valid permission");
			}
		}	
	}
	
	

}
