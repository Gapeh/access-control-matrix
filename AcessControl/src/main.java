import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		//create the ACM object
		accessControl acm = new accessControl();
		acm.createSubject("Jordan", "Administrator","Jordan", "password");
		String userInput = "";
		Scanner reader = new Scanner(System.in);
		while(userInput != "-1") {
			System.out.println("New User or Returning User?(New/Return)");
			userInput = reader.nextLine();
			
			switch (userInput.toUpperCase()) {
			case "NEW":
				System.out.println("Please enter username");
				userInput = reader.nextLine();
				users user = new users(userInput);
				
				System.out.println("Please enter name");
				userInput = reader.nextLine();
				user.setOwner(userInput);
				
				System.out.println("Please enter password(Case Sensitive)");
				userInput = reader.nextLine();
				user.setPassword(userInput);
				
				while(userInput != "RETURN") {
					System.out.println("Regular User, Security Officer, or Administrator?(1, 2, or 3)");
					userInput = reader.nextLine();
					switch (userInput) {
					case "1": 
						user.setPermissions("REGULAR USER");
						acm.createUser(user);
						userInput = "RETURN";
						break;
					case "2": 
						user.setPermissions("SECURITY OFFICER");
						acm.createUser(user);
						userInput = "RETURN";
						break;
					case "3": 
						user.setPermissions("ADMINISTRATOR");
						acm.createUser(user);
						userInput = "RETURN";
						break;
					default:
						System.out.println("Not valid input");
						break;
					}
				}
				break;
			case "RETURN":
				while(userInput.toUpperCase() != "QUIT") {
					System.out.println("Enter a command to run it, \"LIST\" to show all commands, or \"QUIT\" to logout");
					userInput = reader.nextLine();
					switch(userInput) {
					case "list":
						acm.printCommands();
						break;
					default:
						if (userInput.toUpperCase().equals("QUIT")) {
							userInput = "QUIT";
							break;
						}
						String command = userInput;
						System.out.println("Enter username");
						
						userInput = reader.nextLine();
						String username = userInput;
						System.out.println(acm.executeCommand(username, command));
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
