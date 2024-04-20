package application;
	//The is Location class to make object of type location
	//Have Name of city and a his martyr Linked list
	//I used this class to make object by name location to put it his martyrs list
public class Location implements Comparable<Location> {

	private String name;//location name
	private MartyrList martyrList=new MartyrList();// The linked list For martyrs
	
	public Location( ) {
		
	}
	public Location(String name,MartyrList martyrList) {
		super();
		this.name = name;
		this.martyrList=martyrList;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public MartyrList getMartyrList() {
		return martyrList;
	}
	public void setMartyrList(MartyrList martyrList) {
		this.martyrList = martyrList;
	}
	@Override
	public int compareTo(Location o) {
		return name.compareTo(o.name);
	}
}
