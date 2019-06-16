import java.util.ArrayList;

public abstract class accessControlMatrix {
	

	
	
	public abstract void transfer();
	public abstract void grant();
	public abstract void delete();
	public abstract void createObject();
	public abstract void destroyObject();
	public abstract void createSubject();
	public abstract void destroySubject();
	
	public abstract void read(String user, String object);
	
	
	public abstract void createSubject(String username, String permissions, String owner, String password);
	
	public void transfer(String userFrom, String userTo, String permission)
	{
		
		//check if userFrom has permission to transfer
	}
	
	public void grant(String userFrom, String userTo, String permission)
	{
		//grant user permission
		//check if user can grant permissions
		//grant permissions
	}	
	
	public void delete(String user, String permission)
	{
		//delete the object(must have drop permissions)
		//check if user has control or owns it
		//delete
	}

	
	public void destroyObject(String User, String objectName)
	{
		//check if user has ownership of the object
		//delete the object
	}
	public void createSubject(String user)
	{
		//create user
		//create a subject(execute create object S, store control in A)
	}
	public void destroySubject(String user, String subject)
	{
		//check if user has ownership
		//delete the subject.
	}
	
	
	
}
