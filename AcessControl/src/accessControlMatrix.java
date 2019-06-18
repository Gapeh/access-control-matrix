import java.util.ArrayList;

public abstract class accessControlMatrix {
	

	
	
	public abstract void transfer();
	public abstract void grant();
	public abstract void delete();
	
	public abstract void read(String user, String object);
	
	public abstract void createObject(String objectName, String objectType, String objectContents, String username);
	public abstract void createSubject(users user);
	public abstract void destroyObject(users user, String object);
	public abstract void destroySubject(String user, String subject);
	
	
	
	
}
