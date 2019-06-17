
public class objects {
	private String type;
	private String name;
	private String contents;
	
	
	public objects(String type, String name, String contents)
	{
		this.type = type;
		this.name = name;
		this.contents = contents;
	}
	
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void printContents()
	{
		System.out.printf("the contents of %s %s are %s\n", type, name, contents);
	}
	

}