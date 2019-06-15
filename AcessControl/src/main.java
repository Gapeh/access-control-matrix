import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class main {

	public static void main(String[] args) throws IOException {
			
		accessControl acm = new accessControl();
		acm.createUser("Jordan", "Administrator","Jordan");
		String userInput = "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		while(userInput != "-1")
		{
			
			
			System.out.println("Please enter your command(type list for a list of commands)");						
			userInput = reader.readLine();
			if(userInput.equals("-1"))
			{
				break;
			}
			if(userInput.equals("list"))
			{
				acm.printCommands();
			}
			System.out.println("Creat user?");	
			userInput = reader.readLine();
			if(userInput.equals("-1"))
			{
				break;
			}
			
			System.out.println("execute as who?");
			userInput = reader.readLine();
			if(userInput.equals("-1"))
			{
				break;
			}
			
			String username = userInput;
			System.out.println("Please enter your command(type list for a list of commands)");						
			userInput = reader.readLine();
			if(userInput.equals("-1"))
			{
				break;
			}
			String command = userInput;
			System.out.println(acm.executeCommand(username, command));
			
			
		}
		
		
	}

}
