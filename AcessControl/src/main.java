import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		//create the ACM object
		accessControl acm = new accessControl();
		users user1 = new users("Jordan", "ADMINISTRATOR", "password");

		acm.createSubject(user1);
		String userInput = "";
		Scanner reader = new Scanner(System.in);
		while(userInput != "-1") {
			System.out.println("New User or Returning User?(New/Return)");
			userInput = reader.nextLine();
			if(userInput.toUpperCase().equals("QUIT"))
			{
				break;
			}
			switch (userInput.toUpperCase()) {
			case "NEW":
				System.out.println("Please enter username");
				userInput = reader.nextLine();
				users user = new users(userInput);	

				System.out.println("Please enter password(Case Sensitive)");
				userInput = reader.nextLine();
				user.setPassword(userInput);
				
				while(userInput != "RETURN") {
					System.out.println("Regular User, Security Officer, or Administrator?(1, 2, or 3)");
					userInput = reader.nextLine();
					switch (userInput) {
					case "1": 
						user.setPermissions("REGULAR USER");
						userInput = "RETURN";
						break;
					case "2": 
						user.setPermissions("SECURITY OFFICER");
						userInput = "RETURN";
						break;
					case "3": 
						user.setPermissions("ADMINISTRATOR");
						userInput = "RETURN";
						break;
					default:
						System.out.println("Not valid input");
						break;
					}
				}
				acm.createSubject(user);
				break;
			case "RETURN":
				System.out.println("Please login by entering your username");
				userInput = reader.nextLine();
				users currentUser = acm.returnUser(userInput);
				while(true)
				{
					if(currentUser != null)
					{
						break;
					}
					if(userInput.toUpperCase().equals("QUIT"))
					{
						System.out.println("quitting");
						acm.printUsers();
						userInput ="QUIT";
						break;
					}
					System.out.println("Please enter a vald username or QUIT to exit");
					userInput = reader.nextLine();
					currentUser = acm.returnUser(userInput);
				}
				System.out.println("Please enter your password");
				userInput = reader.nextLine();
				
				if(!userInput.equals(currentUser.getPassword()))
				{
					int counter = 3;
					while(counter > 0)
					{
	
						System.out.printf("Invalid password please enter the correct one %d attempts left\n", counter);
						userInput = reader.nextLine();
						if(userInput.equals(currentUser.getPassword()))
						{
							break;
						}						
						counter--;						
					}
					if(!userInput.equals(currentUser.getPassword()))
					{	
						System.out.println("Too many failed attempts logging out");
						userInput="QUIT";
					}						
				}
					
				if(userInput.equals("QUIT"))
				{
					break;
				}
				System.out.println("Login successful");
				while(!userInput.toUpperCase().equals("QUIT")) {
					System.out.println("Enter a command to run it, \"LIST\" to show all commands, or \"QUIT\" to logout");
					userInput = reader.nextLine().toUpperCase();
					boolean validCommand = acm.executeCommand(currentUser.getUsername(), userInput);
					if(!validCommand)
					{
						System.out.println("Invalid command or you do not have access to this command");
						continue;
					}
					switch(userInput) {
					case "LIST":
						acm.printCommands(currentUser);
						break;
					case "CREATE":
						System.out.println("Would you like to create a TABLE or a DATABASE");
						userInput = reader.nextLine();
					
						String objectType = userInput.toUpperCase();
						if(!objectType.equals("TABLE") && !objectType.equals("DATABASE"))
						{
							System.out.println("Must create either a table or a database");
							break;
						}
						System.out.printf("What is the name of the %s\n", objectType);
						userInput = reader.nextLine();
						String objectName = userInput;
						if(acm.returnObject(objectName)!=null)
						{
							System.out.println("An object with that name already exists");
							break;
						}
						System.out.printf("What is the contents of the %s\n", objectType);
						userInput = reader.nextLine();			
						String objectContents = userInput;
						acm.createObject(objectName, objectType, objectContents, currentUser.getUsername());
						break;
					case "READ":
						System.out.println("What would you like to read");
						userInput = reader.nextLine();
						acm.read(currentUser.getUsername(), userInput);
						break;
					case"DROP":
						System.out.println("What would you like to drop");
						userInput = reader.nextLine();
						acm.destroyObject(currentUser, userInput);
						break;
					case "DELETE USER":
                        System.out.println("Enter username of user to be deleted");
                        String username2 = reader.nextLine();
                        users deleteUser = acm.returnUser(username2);
                        acm.destroySubject(currentUser.getUsername(), username2);
                        System.out.println("User deleted");
                        if(currentUser == deleteUser) {
                            userInput = "QUIT";
                        }
                        break;
					case"WHOAMI":
						System.out.printf("i am user %s with %s\n", currentUser.getUsername(), currentUser.getPermissions());
						break;
					case"PRINTALL":
						acm.printUsers();
						break;
					case"PRINTALLOBJECTS":
						acm.printAllObject();
						break;
					default:
						if (userInput.toUpperCase().equals("QUIT")) {
							userInput = "QUIT";
							break;
						}
						String command = userInput;
						System.out.printf("Command %s Successfully Executed\n", command);
						//acm.executeCommand(currentUser.getUsername(), command);
						break;
					}
				}
				break;
			default:
				System.out.println("Not valid input");
				break;
			}
		}
		
	}

}
