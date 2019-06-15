import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
		//create the ACM object
		accessControl acm = new accessControl();
		acm.createUser("Jordan", "Administrator","Jordan", "password");
		String userInput = "";
		Scanner reader = new Scanner(System.in);
		
		while(userInput != "-1")
		{
			
			System.out.println("Please enter your command(type list for a list of commands)");						
			userInput = reader.nextLine();
			if(userInput.equals("-1"))
			{
				break;
			}
			if(userInput.equals("list"))
			{
				acm.printCommands();
				continue;
			}	
			String command = userInput;
			System.out.println("execute as who?");
			userInput = reader.nextLine();
			if(userInput.equals("-1"))
			{
				break;
			}
			String username = userInput;
			System.out.println(acm.executeCommand(username, command));
			
			
		}
		
		
	}

}
