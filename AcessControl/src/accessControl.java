import java.util.ArrayList;
//Role based access control matrix


public class accessControl extends accessControlMatrix
{
	//create array lists that store major variables
	//users/objects array list because its dynamic
	ArrayList<users> activeUsers = new ArrayList<users>();
	ArrayList<objects> activeObjects = new ArrayList<objects>();
	
	//list of commands is an arraylist, easy to add to the arraylist when needed.
	private ArrayList<String> allCommands = new ArrayList<String>();
	private ArrayList<String> defaultCommands = new ArrayList<String>();

	private ArrayList<String> debugCommands = new ArrayList<String>();
	//constructor
	public accessControl()
	{
		populateCommands();
	}
	//simple function to populate the list of commands.
	//as well as the debug commands(this will allow the user to perform debug options)
	//debug options are useful for testing but will not be inside finished project.
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
			allCommands.add("DELETE USER");
			allCommands.add("LOGIN");
			allCommands.add("CHECK PERMISSIONS");
			
			allCommands.add("LIST");
			allCommands.add("READ");
			
			allCommands.add("WHOAMI");
			allCommands.add("QUIT");
			
			
			allCommands.add("PRINTALL");
			allCommands.add("PRINTALLOBJECTS");
			
			
			defaultCommands.add("LIST");
			defaultCommands.add("QUIT");
			//in final version debug commands will be removed. they are here for testing
			debugCommands.add("WHOAMI");
			debugCommands.add("PRINTALL");
			debugCommands.add("PRINTALLOBJECTS");
			for(String s : debugCommands)
			{
				defaultCommands.add(s);
			}
			
	}

	@Override
	public void delete()
	{
		
	}
	public void grant()
	{
		//empty as we are doing role based no need to grant.
	}

	@Override
	public void transfer() {
		// TODO Auto-generated method stub
	}
	
	//read the contents of a table or database
	@Override
	public void read(String user, String object) 
	{
		String command = "READ";
		boolean permission = executeCommand(user, command);		
		objects currentObject = returnObject(object);
		if(!permission)
		{
			System.out.printf("The user %s does not have access to %s object %s\n", user, command, object);
			return;
		}		
		else if(currentObject == null)
		{
			System.out.printf("The %s object was not found\n",object);
			return;
		}
		else
		{
			currentObject.printContents();
		}
	}	
	//destroy an object(object = table or database)
	//create an object
	@Override
	public void createObject(String objectName, String objectType, String objectContents, String username) {
		objects currentObject = returnObject(objectName);
		String command = "CREATE";
		executeCommand(username, command);
		if(currentObject != null)
		{
			System.out.println("An object with that name already exists please choose a different name");
			return;
		}
		if(objectType.toUpperCase().equals("TABLE") || objectType.toUpperCase().equals("DATABASE"))
		{
			objects newObject = new objects(objectType, objectName, objectContents);
			System.out.println("Created an object");
			newObject.printContents();
			activeObjects.add(newObject);
			return;
		}
		System.out.println("ERROR has occured");	
		
	}
	//username1 is destroying username2.
	//username1 can either be admin/security or the same as username2.

	@Override
	public void destroyObject(users user, String object) 
	{
		String command = "DROP";
		objects currentObject = returnObject(object);
		if(currentObject == null)
		{
			System.out.printf("The %s object was not found",object);
			return;
		}
		else
		{
			System.out.printf("The %s named %s was dropped\n", currentObject.getType(), currentObject.getName());
			activeObjects.remove(currentObject);
		}
		
	}
		
	//create a subject, make sure the username isnt already present in the list
	@Override
	public void createSubject(users user)
	{
		users currentUser = this.returnUser(user.getUsername());
		if(currentUser == null)
		{ 
			activeUsers.add(user);
			return;
		}
		System.out.println("ERROR username already exists");
		
	}
	@Override
	public void destroySubject(String username1, String username2)
	{
		users user1 = returnUser(username1);		
		users user2 = returnUser(username2);
		String command = "DELETE USER";
		boolean permission = checkPermissions(username1, command);
		if(user1 == null)
		{
			System.out.printf("There is no user %s", username1);
			return;
		}
		else if(user2 == null)
		{
			System.out.printf("There is no user %s", username2);
			return;
		}
		else if(user1 == user2 || permission == true)
		{
			activeUsers.remove(user2);
			return;
		}
		else
		{
			System.out.printf("You do not have permissions to delete that user");
			return;
		}
	}
	//print all the commands function
	public void printCommands()
	{
		System.out.println(allCommands);
	}
	//print all of the users commands.
	public void printCommands(users currentUser)
	{
		ArrayList<String> myCommands = new ArrayList<String>();
		myCommands = convertRole(currentUser);
		System.out.println(myCommands);
	}
	//simple function to return the user
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
	//function to return the object
	public objects returnObject(String objectName)
	{
		
		objects currentObject = null;
		for(objects object : activeObjects)
		{
			if(object.getName().equals(objectName))
			{
				currentObject = object;
				break;
			}
		}
		return currentObject;
	}
	//create a user function
	public void createUser(users user)
	{
		activeUsers.add(user);
		
	}
	//if the command is an invalid command return false. IE the command is not found
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
		if(commandFound == false)
		{			
			System.out.println("That command was not found");
			permissionCategory = "Not Found";
		}
		
		return permissionCategory;
	}
		
	//check permissions has the option to have the user be passed to it or the username and perform a user lookup
	public boolean checkPermissions(users currentUser, String command)
	{
		
		boolean grantAccess = false;
		if(command.equals("Not Found"))
		{
			return grantAccess;
		}
		//check to see if the username is valid
		if(currentUser == null)
		{
			System.out.println("Username was not found");
			return grantAccess;
		}		
		ArrayList<String> totalPermissions = new ArrayList<String>();
		totalPermissions = convertRole(currentUser);
		System.out.printf("%s ", command);
		if(totalPermissions.contains(command))
		{
			grantAccess = true;
			System.out.println("Permission granted");
		}
		return grantAccess;
	}
	public boolean checkPermissions(String username, String command)
	{
		users currentUser = null;
		boolean grantAccess = false;
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
		System.out.printf("%s ", command);
		if(totalPermissions.contains(command))
		{
			grantAccess = true;
			System.out.println("Permission granted");
		}
		return grantAccess;
	}
	//function that converts the category to figure out which permissions per category
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
			totalPermissions = allCommands;
			/*
			totalPermissions.add("CREATE");			
			totalPermissions.add("DROP");
			totalPermissions.add("SELECT");			
			totalPermissions.add("INSERT");
			totalPermissions.add("DELETE");
			totalPermissions.add("COMMIT");			
			totalPermissions.add("ROLLBACK");
			totalPermissions.add("GRANT");			
			totalPermissions.add("REVOKE");
			totalPermissions.add("DELETE USER");
			totalPermissions.add("CHECK PERMISSIONS");
			*/
			break;
		default:
			System.out.println("ERROR invalid category");
			break;
		}
		return totalPermissions;
	}
	//function that adds the default commands to the list of user commands.
	public ArrayList<String> addDefaults(ArrayList<String> original)
	{
		for(String s : defaultCommands)
		{
			original.add(s);
		}
		return original;
	}
	//function to convert the users role to a list of permissions.
	public ArrayList<String> convertRole(users user)
	{
		ArrayList<String> totalPermissions = new ArrayList<String>();
		String role = user.getPermissions();
		switch (role)
		{
			case "SECURITY OFFICER":
				totalPermissions.add("ROLLBACK");
				totalPermissions.add("GRANT");
				totalPermissions.add("DELETE USER");
				totalPermissions = addDefaults(totalPermissions);
				break;
			case "REGULAR USER":
				
				totalPermissions = convertCategory("DML");
				totalPermissions = addDefaults(totalPermissions);
				break;
			case "ADMINISTRATOR":
				totalPermissions = convertCategory("ADMIN");
				break;
			default:
				System.out.println("ERROR Invalid permission");
		}
		return totalPermissions;		
		
	}
	//function to execute command and return permissions
	//returns users permissions
	//checks if the command is a valid command
	public boolean executeCommand(String username, String command)
	{
		command = command.toUpperCase();
		String permissions = checkCommand(command);
		return checkPermissions(username, command);
	}
	//print the list of users.
	public void printUsers()
	{		
		for(users user : activeUsers)
		{
			System.out.printf("Username = %s  -- Permissions = %s\n", user.getUsername(), user.getPermissions());
		}
	}
	

	//debug function to print all of the objects.
	public void printAllObject()
	{		
		for(objects object : activeObjects)
		{
			System.out.println(object.getName());
		}
	}

}
