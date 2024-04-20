package application;

import java.util.Date;
	//The is Martyr class to make object of type Martyr
	//Have Name,age,gender and date of death of Martyr
	//I used this class to make object for Martyr to put it in his martyrs list in his location
public class Martyr implements Comparable<Martyr> {

	private String Name;
	private byte Age;
	private Date DateOfDeath;
	private char Gender;
		
	public Martyr() {
		
	}
	public Martyr(String name, byte age, Date dateOfDeath, char gender) {
		super();
		Name = name;
		Age = age;
		DateOfDeath = dateOfDeath;
		Gender = gender;
	}
	//Getter and setter
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public byte getAge() {
		return Age;
	}
	public void setAge(byte age) {
		Age = age;
	}
	public Date getDateOfDeath() {
		return DateOfDeath;
	}
	public void setDateOfDeath(Date dateOfDeath) {
		DateOfDeath = dateOfDeath;
	}
	public char getGender() {
		return Gender;
	}
	public void setGender(char gender) {
		Gender = gender;
	}
	@Override
	public int compareTo(Martyr o) {
		return  DateOfDeath.compareTo(o.DateOfDeath);
	}	
}