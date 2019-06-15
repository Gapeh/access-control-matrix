import java.util.ArrayList;
//Role based access control matrix


public class accessControl 
{
	ArrayList<users> activeUsers = new ArrayList<users>();
	private ArrayList<String> allCommands = new ArrayList<String>();
	
	public accessControl()
	{
		populateCommands();
	}
	private void populateCommands()

	{
		
			//SQL COMMANDS
			allCommands.add("CREATE");	
			allCommands.add("DROP"); 
			//DUMMY SQL COMMANDS
			allCommands.add("SELECT");			
			allCommands.add("INSERT");
			allCommands.add("DELETE");
			allCommands.add("COMMIT");			
			allCommands.add("ROLLBACK");
			allCommands.add("GRANT");			
			allCommands.add("REVOKE");
			
			//ACM COMMANDS
			allCommands.add("CREATE USER");
			allCommands.add("LOGIN");
			allCommands.add("CHECK PERMISSIONS");
			allCommands.add("LIST");
		
	}
	public void printCommands()
	{
		System.out.println(allCommands);
	}
	public void transfer(String username, String permission)
	{
		//function to get the users authorization
		
	}
	
	public users returnUser(String username)
	{
		users currentUser = null;
		for(users user : activeUsers)
		{
			if(user.getUsername().equals(username.toUpperCase()))
			{
				currentUser = user;
				break;
			}
		}
		return currentUser;
	}
	public void createTable(String username, String tableName)
	{
		
		
	}
	public void createUser(String username, String permissions, String owner, String password)
	{
		users user = new users(username.toUpperCase(), permissions.toUpperCase(), owner.toUpperCase(), password);
		activeUsers.add(user);
		
	}
	public String checkCommand(String command)
	{
		String permissionCategory = "";
		command = command.toUpperCase();
		boolean commandFound = false;
		for(String commands : allCommands)
		{
			if(command.equals(commands))
			{
				commandFound = true;
				break;
			}
		}
		if(commandFound)
		{			
			System.out.println("That command was not found");
			permissionCategory = "Not Found";
		}
		
		return permissionCategory;
	}
		
		/*
		switch(command)
		{
		
		case "CREATE":
			permissionCategory = "DDL";
			break;
		case "DROP":
			permissionCategory = "DDL";
			break;
		case "SELECT":
			permissionCategory = "DML";
			break;
		case "INSERT":
			permissionCategory = "DML";
			break;
		case "DELETE":
			permissionCategory = "DML";
			break;
		case "COMMIT":
			permissionCategory = "TCL";
			break;
		case "ROLLBACK":
			permissionCategory = "TCL";
			break;
		case "GRANT":
			permissionCategory = "DCL";					
			break;
		case "REVOKE":
			permissionCategory = "DCL";		
			break;
		default:*/
	
	public boolean checkPermissions(String username, String command)
	{
		
		users currentUser = null;
		boolean grantAccess = false;
		boolean userFound = false;		
		if(command.equals("Not Found"))
		{
			return grantAccess;
		}
		//check to see if the username is valid
		currentUser = returnUser(username);
		if(currentUser == null)
		{
			System.out.println("Username was not found");
			return grantAccess;
		}
				
		ArrayList<String> totalPermissions = new ArrayList<String>();
		totalPermissions = convertRole(currentUser);
		System.out.printf("Command = %s ", command);
		if(totalPermissions.contains(command))
		{
			grantAccess = true;
			System.out.println("Permission granted");
		}
		return grantAccess;
	}
	public ArrayList<String> convertCategory(String category)
	{		
		ArrayList<String> totalPermissions = new ArrayList<String>();
		switch (category)
		{	
		case"DDL":			
			totalPermissions.add("CREATE");			
			totalPermissions.add("DROP");
			break;
		case"DML":
			totalPermissions.add("SELECT");			
			totalPermissions.add("INSERT");
			totalPermissions.add("DELETE");
			break;
		case"TCL":			
			totalPermissions.add("COMMIT");			
			totalPermissions.add("ROLLBACK");
			break;		
		case"DCL":			
			totalPermissions.add("GRANT");			
			totalPermissions.add("REVOKE");
			break;
		case"ADMIN":	
			totalPermissions.add("CREATE");			
			totalPermissions.add("DROP");
			totalPermissions.add("SELECT");			
			totalPermissions.add("INSERT");
			totalPermissions.add("DELETE");
			totalPermissions.add("COMMIT");			
			totalPermissions.add("ROLLBACK");
			totalPermissions.add("GRANT");			
			totalPermissions.add("REVOKE");
			break;
		default:
			System.out.println("ERROR invalid category");
			break;
		}
		return totalPermissions;
	}
	public ArrayList<String> convertRole(users user)
	{
		ArrayList<String> totalPermissions = new ArrayList<String>();
		String role = user.getPermissions();
		switch (role)
		{
			case "SECURITY OFFICER":
				totalPermissions.add("ROLLBACK");
				break;
			case "REGULAR USER":
				totalPermissions = convertCategory("DML");
				break;
			case "ADMINISTRATOR":
				totalPermissions = convertCategory("ADMIN");
				break;
			default:
				System.out.println("ERROR Invalid permission");
		}
		System.out.println(totalPermissions);
		return totalPermissions;		
		
	}
	public boolean executeCommand(String username, String command)
	{
		command = command.toUpperCase();
		String permissions = checkCommand(command);
		return(checkPermissions(username, command));
		
	}
	public void printUsers()
	{		
		for(users user : activeUsers)
		{
			System.out.printf("Username = %s  -- Permissions = %s\n", user.getUsername(), user.getPermissions());
		}
	}

}
