import java.util.ArrayList;
//Role based access control matrix


public class accessControl extends accessControlMatrix
{
	ArrayList<users> activeUsers = new ArrayList<users>();
	ArrayList<objects> activeObjects = new ArrayList<objects>();
	
	private ArrayList<String> allCommands = new ArrayList<String>();
	private ArrayList<String> defaultCommands = new ArrayList<String>();
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
			defaultCommands.add("WHOAMI");
			defaultCommands.add("PRINTALL");
			defaultCommands.add("PRINTALLOBJECTS");
			defaultCommands.add("QUIT");
	}
	
	public void grant()
	{
		
	}
	public void printCommands()
	{
		System.out.println(allCommands);
	}
	public void printCommands(users currentUser)
	{
		ArrayList<String> myCommands = new ArrayList<String>();
		myCommands = convertRole(currentUser);
		System.out.println(myCommands);
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
	public objects returnObject(String objectName)
	{
		
		objects currentObject = null;
		for(objects object : activeObjects)
		{
			if(object.getName().equals(objectName))
			{
				System.out.println("object found");
				currentObject = object;
				break;
			}
		}
		return currentObject;
	}
	public void createTable(String objectname, String tableName)
	{
		
		
	}
	/*
	@Override
	public void createSubject(String username, String permissions, String owner, String password)
	{
		users currentUser = this.returnUser(username);
		if(currentUser == null)
		{
			users user = new users(username.toUpperCase(), permissions.toUpperCase(), owner.toUpperCase(), password);
			activeUsers.add(user);
			return;
		}
		System.out.println("ERROR username already exists");
		
	}
	*/
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
	//username1 is destroying username2.
	//username1 can either be admin/security or the same as username2.
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
	public void createUser(users user)
	{
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
		if(commandFound == false)
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
	public ArrayList<String> addDefaults(ArrayList<String> original)
	{
		for(String s : defaultCommands)
		{
			original.add(s);
		}
		return original;
	}
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
	public boolean executeCommand(String username, String command)
	{
		command = command.toUpperCase();
		String permissions = checkCommand(command);
		return checkPermissions(username, command);
	}
	public void printUsers()
	{		
		for(users user : activeUsers)
		{
			System.out.printf("Username = %s  -- Permissions = %s\n", user.getUsername(), user.getPermissions());
		}
	}
	@Override
	public void transfer() {
		// TODO Auto-generated method stub
	}
	@Override
	public void destroyObject(String user, String object) {			
		users currentUser = returnUser(user);
		String command = "DROP";
		objects currentObject = returnObject(object);
		if(currentObject == null)
		{
			System.out.printf("The %s object was not found",object);
			return;
		}
		boolean permission = executeCommand(user, command);
		if(!permission)
		{			
			System.out.printf("The user %s does not have access to %s object %s", user, command, object);
			return;
		}
		else
		{
			activeObjects.remove(currentObject);
		}
		
	}
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
	@Override
	public void createObject() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void destroyObject() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void createSubject() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void destroySubject() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}
	public void sqlCommand()
	{
		
	}
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
	public void printAllObject()
	{		
		for(objects object : activeObjects)
		{
			System.out.println(object.getName());
		}
	}

}
