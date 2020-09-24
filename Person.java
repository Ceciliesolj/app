package app;

import java.io.Serializable;
import java.util.ArrayList;

public class Person implements Serializable{
	
	private int age;
	private String givenName;
	private String familyName;
	private String email;
	private String adress;
	private String phone;
	
	
	public Person(String givenName, String familyName, int age) {
		this.setGivenName(givenName);
		this.setFamilyName(familyName);
		this.setAge(age);
	}
	
	public int getAge() {
		return this.age;
	}
	public void setAge(int age) {
		if (age < 0) {
			throw new IllegalArgumentException("You are not born!");
		}
		this.age = age;
	}
	public String getGivenName() {
		return this.givenName;
	}
	public void setGivenName(String givenName) {
		if (givenName.length() < 2) {
			throw new IllegalArgumentException("Too short name!");
		}
		char[]tmpList = givenName.toCharArray();
		for (char character: tmpList) {
			if (! Character.isLetter(character))
				throw new IllegalArgumentException("A name can only contain letters!");		
			this.givenName = givenName;
		}
		this.givenName = givenName;
	}

	public String getFamilyName() {
		return this.familyName;
	}
	public void setFamilyName(String familyName) {
		if (familyName.length() < 2) {
			throw new IllegalArgumentException("Too short name!");
		}
		char[]tmpList = familyName.toCharArray();
		for (char character: tmpList) {
			if (! Character.isLetter(character))
				throw new IllegalArgumentException("A name can only contain letters!");		
			this.familyName = familyName;
		}
		this.familyName = familyName;
	}
	public String getFullName() {
		return this.givenName + " " + this.familyName;
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		if (! email.contains("@"))
			throw new IllegalArgumentException("Not a valid email.");
		if (! email.contains("."))
			throw new IllegalArgumentException("Not a valid email.");
		if (email.length() < 6)
			throw new IllegalStateException("Too short email.");
		this.email = email;
	}
	
	public String getAdress() {
		return this.adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	
	public void setPhone(String phone) {
		if (phone.length() < 8) {
			throw new IllegalArgumentException("Too short number!");
		}
		char[]tmpList = phone.toCharArray();
		for (char character: tmpList) {
			if (! Character.isDigit(character))
				throw new IllegalArgumentException("A phonenumber can only contain numbers!");	
			this.phone = phone;
		}
		this.phone = phone;
	}
	
	public String getPhone() {
		return this.phone;
	}
	

}
