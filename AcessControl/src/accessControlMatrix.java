
public class accessControlMatrix {

	public void transfer(String userFrom, String userTo, String permission)
	{
		//check if userFrom has permission to transfer
	}
	
	public void grant(String userFrom, String userTo, String permission)
	{
		//check if user can grant permissions
		//grant permissions
	}	
	
	public void delete(String user, String permission)
	{
		//check if user has control or owns it
		//delete
	}
	public void read(String user, String object)
	{
		//check if user has control or owns it
		//read the object
	}
	public void createObject(String user, String objectName)
	{
		//create an object
		//update the access control matrix
	}
	public void destroyObject(String User, String objectName)
	{
		//check if user has ownership of the object
		//delete the object
	}
	public void createSubject(String user)
	{
		//create a subject(execute create object S, store control in A)
	}
	public void destroySubject(String user, String subject)
	{
		//check if user has ownership
		//delete the subject.
	}
	
	
	
}
