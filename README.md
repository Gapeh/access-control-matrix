Created by: Jordan Jones & Anthony Pacini
Date: 6/18/2019
Assignment 3: Access Control Matrix for DBMS
Class: Computer Security


How to run(on eclipse):
	Unzip the file	
	Import project
		general->file system.
		select the ACM file
		select all.
	Select all the contents of the unzipped file
	run

How to run the program
	The program starts with a default user with the credentials
		username = jordan
		password = password
		permissions = Administratos(this means he can run all of the commands)
	The ACM will prompt the user to either enter new or return
		New: will run the user through the user creation
			will prompt the user for a username
				NOTE: there cannot be 2 users with the same username
			will prompt the user for a password
				NOTE: passwords are case sensative(stored in plain text)
			will prompt the user to enter a role
				NOTE:1 2 and 3 are the only valid inputs and they correspond to the roles
				if the user does not enter a valid role the user will be prompted to enter it again until 1 2 or 3 is entered. 
			if the user picks a username that is already taken the account creation will not be successfull
		return: will run the user through the login 
			will prompt the user for a username
				NOTE: if invalid username the user will be prompted again(QUIT to exit)
			will prompt the user for a password
				NOTE: 3 attempts for the password if wrong the user will be kicked out
			login unsuccessfull
				user is taken back to the new/return screen.
			login successfull: the user is now logged in
				The user can now type in the commands to run
					NOTE: if the user types in an invalid command(IE no permission to run it or its an invalid command the command will not run and the user will be notified they dont have access to that command)
				The ACM will check the users permission for every command:
				List - will list all of the available commands the user can run
				Create: 
    					ask you if you want to create a table or database
    					ask for the name of your object
   					ask for the contents of your object
				Read:
    					will let you see the contents of an object if you have access to view it and
    					it exists
				Drop:
    					will allow you to destroy an object if you have access to it and it exists
				Delete User:
   					ask to enter the username of the user that you want to delete
    					It will delete that user if you have permissions to delete users or you
    					are trying to delete yourself
				Quit:
    					logs you out
				Any other SQL commands:
					Will say permission granted/denied if the user has permission to run.
				DEBUG COMMANDS:
					Whoami:
    						will tell you the user you are logged in ask currently
					printall:
    						prints all users
					printallobjects:
    						prints all objects that have been created	
Computer information:
	OS = 	Windows 10 - version 1809(OS Build 17763.503)
	Compiler = ECJ
	JRE = "JDK-12.0.1")
	IDE = ECLIPSE Version: 2019-03 (4.11.0) Build id: 20190314-1200
	

	
